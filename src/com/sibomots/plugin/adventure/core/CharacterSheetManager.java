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

import com.google.common.collect.Maps;
import com.sibomots.plugin.adventure.Adventure;
import com.sibomots.plugin.adventure.core.game.character.PlayerCharacter;
import com.sibomots.plugin.adventure.configuration.configurations.AdventureConfig;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.storage.WorldProperties;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

public class CharacterSheetManager {

    public final static Path playerDataPath = Paths.get(Adventure.MOD_ID, "PlayerData");
    private boolean useGlobalStorage = false;
    private AdventureConfig<?> activeConfig;
    private WorldProperties worldProperties;
    private Map<UUID, PlayerCharacter> playerDataList = Maps.newHashMap();
    private Map<UUID, PlayerStorageData> playerStorageList = Maps.newHashMap();

    public CharacterSheetManager()
    {
        this.worldProperties = null;
        this.activeConfig = Adventure.getGlobalConfig();
        this.useGlobalStorage = true;
    }

    public CharacterSheetManager(WorldProperties worldProperties) {
        this.worldProperties = worldProperties;
        this.activeConfig = Adventure.getActiveConfig(this.worldProperties);
    }


    public PlayerCharacter getPlayerData(UUID playerUniqueId) {
        PlayerCharacter playerData = this.playerDataList.get(playerUniqueId);
        if (playerData == null) {
            return createPlayerCharacterSheet(playerUniqueId);
        } else {
            return playerData;
        }
    }

    public PlayerCharacter createPlayerCharacterSheet(UUID playerUniqueId) {
        PlayerCharacter playerData = this.playerDataList.get(playerUniqueId);
        if (playerData != null) {
            return playerData;
        }

        Path rootPath = Sponge.getGame()
                .getSavesDirectory()
                .resolve(Sponge.getGame().getServer().getDefaultWorld().get().getWorldName());

        Path playerFilePath = null;

        if (this.useGlobalStorage ||
                this.worldProperties.getUniqueId() ==
                        Sponge.getGame().getServer().getDefaultWorld().get().getUniqueId()) {
            playerFilePath = rootPath.resolve(playerDataPath).resolve(playerUniqueId.toString());
        } else {
            playerFilePath = rootPath.resolve(this.worldProperties.getWorldName()).resolve(playerDataPath).resolve(playerUniqueId.toString());
        }

        PlayerStorageData playerStorage =
                new PlayerStorageData(playerFilePath,
                        playerUniqueId, this.activeConfig.getConfig().general.isFoobar);

        // TODO
        Object sampleInitialPlayerCharacterSheetInstance = null;

        playerData = new PlayerCharacter(this.worldProperties,
                        playerUniqueId, playerStorage, this.activeConfig,
                        sampleInitialPlayerCharacterSheetInstance);
        this.playerStorageList.put(playerUniqueId, playerStorage);
        this.playerDataList.put(playerUniqueId, playerData);
        return playerData;
    }

    public void removePlayer(UUID playerUniqueId) {
        this.playerStorageList.remove(playerUniqueId);
        this.playerDataList.remove(playerUniqueId);
    }

    public void save() {
        for (PlayerStorageData storageData : this.playerStorageList.values()) {
            if (storageData != null) {
                storageData.save();
            }
        }
    }

    public void unload() {
        this.playerDataList.clear();
        this.playerStorageList.clear();
        this.worldProperties = null;
    }


}
