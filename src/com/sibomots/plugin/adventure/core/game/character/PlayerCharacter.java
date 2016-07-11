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
package com.sibomots.plugin.adventure.core.game.character;

import com.sibomots.plugin.adventure.configuration.configurations.AdventureConfig;

import java.util.UUID;

import com.sibomots.plugin.adventure.configuration.configurations.PlayerDataConfig;
import com.sibomots.plugin.adventure.core.CharacterStorageData;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.storage.WorldProperties;

public class PlayerCharacter implements PlayableCharacter {

    public UUID id;
    private AdventureConfig<?> activeConfig;
    private CharacterStorageData playerStorage;
    private boolean inAdventure;

    // TODO what is saved?  the "sheet" ?
    private Object sheet;


    @Override
    public String getName()
    {
        // TODO
        return null;
    }
    public PlayerCharacter(UUID playerUniqueId,
                           CharacterStorageData playerStorage,
                           AdventureConfig<?> activeConfig, Object sheet ) {
        this.id = playerUniqueId;
        this.playerStorage = playerStorage;
        this.activeConfig = activeConfig;
        // TODO what is the sheet?
        this.sheet = sheet;
        this.inAdventure = false;
    }



    @Override
    public void saveAllData() {

        this.playerStorage.save();
    }
    @Override
    public CharacterStorageData getStorageData() {
        return this.playerStorage;
    }

    @Override
    public void setPlayerInAdventure(boolean inAdventure)
    {
        this.inAdventure = inAdventure;
    }

    @Override
    public void setCharacterStorage(CharacterStorageData characterStorage) {

    }

    @Override
    public void setCharacterData(PlayerDataConfig playerDataConfig) {

    }

    @Override
    public void updateCharacterStorageData() {

    }


}
