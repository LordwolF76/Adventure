/*
 * This file is part of Adventure, licensed under the BSD 3-Clause License
 *
 * Copyright (c) SiboRoc <http://siboroc.net>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.sibomots.plugin.adventure.configuration.partitions;

import com.google.common.reflect.TypeToken;
import com.sibomots.plugin.adventure.Adventure;
import com.sibomots.plugin.adventure.core.SafeLogger;
import com.sibomots.plugin.adventure.message.PreparedLogMessages;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializers;
import org.spongepowered.api.util.Functional;
import org.spongepowered.common.SpongeImpl;
import org.spongepowered.common.util.IpSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class AdventureConfig<T extends BasisConfig> {

    public enum Type {
        GLOBAL(GlobalConfig.class);
        //   DIMENSION(DimensionConfig.class),
        //   WORLD(WorldConfig.class);

        private final Class<? extends BasisConfig> clazz;

        Type(Class<? extends BasisConfig> type) {
            clazz = type;
        }
    }

    public static final String HEADER = "header for the configuration";


    private Type configType;
    private HoconConfigurationLoader loader;
    private CommentedConfigurationNode root = SimpleCommentedConfigurationNode.root(ConfigurationOptions.defaults()
            .setHeader(HEADER));
    private ObjectMapper<T>.BoundInstance configMapper;
    private T configBase;

   // @SuppressWarnings({"unchecked", "rawtypes"})
    public AdventureConfig(Type typ, Path path) {

        configType = typ;

        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }

            loader = HoconConfigurationLoader.builder().setPath(path).build();
            configMapper = (ObjectMapper.BoundInstance) ObjectMapper.forClass(configType.clazz).bindToNew();

            reload();
            save();
        } catch (Exception e) {
            SafeLogger.Error(PreparedLogMessages.CONFIGURATION_INITIALIZATION_FAILED, e);
        }
    }

    public T getConfig() {
        return configBase;
    }

    public void save() {
        try {
            configMapper.serialize(root.getNode(Adventure.MOD_ID));
            loader.save(root);
        } catch (IOException | ObjectMappingException e) {
            SafeLogger.Error(PreparedLogMessages.CONFIGURATION_SAVE_FAILED, e);
        }
    }

    public void reload() {
        try {
            root = loader.load(ConfigurationOptions.defaults()
                    .setSerializers(
                            TypeSerializers.getDefaultSerializers().newChild().registerType(TypeToken.of(IpSet.class), new IpSet.IpSetSerializer()))
                    .setHeader(HEADER));
            configBase = configMapper.populate(root.getNode(Adventure.MOD_ID));
        } catch (Exception e) {
            SafeLogger.Error(PreparedLogMessages.CONFIGURATION_LOAD_FAILED, e);
        }
    }

    public CompletableFuture<CommentedConfigurationNode> updateSetting(String key, Object value) {
        return Functional.asyncFailableFuture(() -> {
            CommentedConfigurationNode upd = getSetting(key);
            upd.setValue(value);
            configBase = configMapper.populate(root.getNode(Adventure.MOD_ID));
            loader.save(root);
            return upd;
        }, ForkJoinPool.commonPool());
    }

    public CommentedConfigurationNode getRootNode() {
        return root.getNode(Adventure.MOD_ID);
    }

    private Boolean isValidKeyIndex(String key)
    {
        return (!key.contains(".") || key.indexOf('.') == key.length() - 1);
    }

    public CommentedConfigurationNode getSetting(String key) {

        CommentedConfigurationNode cfgNode = null;

        if (isValidKeyIndex(key))
        {
            String category = key.substring(0, key.indexOf('.'));
            String prop = key.substring(key.indexOf('.') + 1);
            cfgNode = getRootNode().getNode(category).getNode(prop);
        }

        return cfgNode;
    }

    public Type getType() {
        return configType;
    }


}
