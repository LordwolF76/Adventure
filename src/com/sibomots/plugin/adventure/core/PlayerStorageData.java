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
package com.sibomots.plugin.adventure.core;

import com.google.common.reflect.TypeToken;
import com.sibomots.plugin.adventure.Adventure;
import com.sibomots.plugin.adventure.configuration.configurations.PlayerDataConfig;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializers;
import org.spongepowered.api.util.Functional;
import org.spongepowered.common.util.IpSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class PlayerStorageData {

    public static final String HEADER = "someheader";

    private HoconConfigurationLoader loader;
    private CommentedConfigurationNode root = SimpleCommentedConfigurationNode.root(ConfigurationOptions.defaults()
    .setHeader(HEADER));
    private ObjectMapper<PlayerDataConfig>.BoundInstance configMapper;
    private PlayerDataConfig configBase;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public PlayerStorageData(Path path, UUID playerUniqueId, boolean isFooBar) {

        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }

            this.loader = HoconConfigurationLoader.builder().setPath(path).build();
            this.configMapper = (ObjectMapper.BoundInstance) ObjectMapper.forClass(PlayerDataConfig.class).bindToNew();
            this.configMapper.getInstance().setPlayerInAdventure(true);
            reload();
            save();
        } catch (Exception e) {
            SafeLogger.Error("Failed to initialize configuration", e);
        }
    }

    public PlayerDataConfig getConfig() {
        return this.configBase;
    }

    public void save() {
        try {
            if (this.configBase.requiresSave()) {
                this.configMapper.serialize(this.root.getNode(Adventure.MOD_ID));
                this.loader.save(this.root);
                this.configBase.setRequiresSave(false);
            }
        } catch (IOException | ObjectMappingException e) {
            SafeLogger.Error("Failed to save configuration", e);
        }
    }

    public void reload() {
        try {
            this.root = this.loader.load(ConfigurationOptions.defaults()
                    .setSerializers(
                            TypeSerializers.getDefaultSerializers().newChild().registerType(TypeToken.of(IpSet.class), new IpSet.IpSetSerializer()))
                    .setHeader(HEADER));
            this.configBase = this.configMapper.populate(this.root.getNode(Adventure.MOD_ID));
        } catch (Exception e) {
            SafeLogger.Error("Failed to load configuration", e);
        }
    }

    public CompletableFuture<CommentedConfigurationNode> updateSetting(String key, Object value) {
        return Functional.asyncFailableFuture(() -> {
            CommentedConfigurationNode upd = getSetting(key);
            upd.setValue(value);
            this.configBase = this.configMapper.populate(this.root.getNode(Adventure.MOD_ID));
            this.loader.save(this.root);
            return upd;
        }, ForkJoinPool.commonPool());
    }

    public CommentedConfigurationNode getRootNode() {
        return this.root.getNode(Adventure.MOD_ID);
    }

    public CommentedConfigurationNode getSetting(String key) {
        if (!key.contains(".") || key.indexOf('.') == key.length() - 1) {
            return null;
        } else {
            String category = key.substring(0, key.indexOf('.'));
            String prop = key.substring(key.indexOf('.') + 1);
            return getRootNode().getNode(category).getNode(prop);
        }
    }
}
