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

import com.sibomots.plugin.adventure.Adventure;
import com.sibomots.plugin.adventure.configuration.partitions.AdventureConfig;
import com.sibomots.plugin.adventure.configuration.partitions.GlobalConfig;
import com.sibomots.plugin.adventure.game.AttributeManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DataStore {
    private final static String GLOBAL_DATA_PATH = Adventure.MOD_ID + "Data";

    protected AttributeManager globalAttributeManager;
    public final static Path dataLayerFolderPath = Paths.get("configuration").resolve(Adventure.MOD_ID);

    public static AdventureConfig<GlobalConfig> globalConfig;
    public Path globalPlayerDataPath;
    // Need a global player data manager

    // Need a data manager for world data?

    public final static Path globalDataPath =
         Paths.get(GLOBAL_DATA_PATH).resolve("GlobalPlayerData");


    void initialize()
    {
       // if (Adventure.getGlobalConfig().getConfig().playerdata.useGlobalPlayerDataStorage) {
       //      this.globalAttributeManager = new AttributeManager();
       //  }
    }

}
