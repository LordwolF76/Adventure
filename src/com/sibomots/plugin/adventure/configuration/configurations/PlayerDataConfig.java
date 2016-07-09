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
package com.sibomots.plugin.adventure.configuration.configurations;

import com.sibomots.plugin.adventure.configuration.category.PlayerDataCategory;
import ninja.leaping.configurate.objectmapping.Setting;

import java.util.UUID;

public class PlayerDataConfig extends PlayerDataCategory {

    private boolean requiresSave = true;

    @Setting(value = "uuid", comment = "The player's uuid.")
    private String playerUniqueId;

    @Setting(value = "player-character-established", comment = "Is the player in Adventure")
    private boolean isCharacterEstablished;


    public String getPlayerUniqueId() {
        return this.playerUniqueId;
    }

    public void setPlayerInAdventure(boolean isInAdventure) {
         this.isCharacterEstablished = isInAdventure;
    }

    public boolean getPlayerInAdventure() {
        return this.isCharacterEstablished;
    }

    public void setPlayerUniqueId(UUID uuid) {
        this.requiresSave = true;
        this.playerUniqueId = uuid.toString();
    }

    public boolean requiresSave() {
        return this.requiresSave;
    }

    public void setRequiresSave(boolean flag) {
        this.requiresSave = flag;
    }


}
