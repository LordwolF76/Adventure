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
package com.sibomots.plugin.adventure.core.dss;

import com.google.common.collect.Maps;
import com.sibomots.plugin.adventure.core.CharacterStorageData;
import com.sibomots.plugin.adventure.core.DataStore;
import com.sibomots.plugin.adventure.core.SafeLogger;
import com.sibomots.plugin.adventure.core.game.character.PlayerCharacter;
import com.sibomots.plugin.adventure.core.game.character.PlayableCharacter;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.util.Tristate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

//manages data stored in the file system
public class FlatFileDataStore extends DataStore {

    public final static Map<UUID, Task> cleanupClaimTasks = Maps.newHashMap();
    private final static Path schemaVersionFilePath = dataLayerFolderPath.resolve("_schemaVersion");
    public final static Path playerDataPath = Sponge.getGame().getSavesDirectory().resolve("config").resolve("Adventure").resolve("PlayerData");
    private final Path rootConfigPath = Sponge.getGame().getSavesDirectory().resolve("config").resolve("Adventure");
    public static Path rootCharacterSafePath;

    public FlatFileDataStore() {
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    public void validateFlagDefaults(Set<Context> contexts, Map<String, Tristate> defaultFlags) {
    }

    void createPlayerData(UUID characterId) {
        // TODO
    }

    void loadPlayerData( File[] files) throws Exception {
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) // avoids folders
            {
                UUID playerUUID;
                try {
                    playerUUID = UUID.fromString(files[i].getName());
                } catch (Exception e) {
                    SafeLogger.Error("ERROR!! could not read player file " + files[i].getAbsolutePath(), e);
                    continue;
                }

                if (!Sponge.getServer().getPlayer(playerUUID).isPresent()) {
                    return;
                }

                try {
                    this.createPlayerData(playerUUID);
                }

                // if there's any problem with the file's content, log an error message and skip it
                catch (Exception e) {
                    if (e.getMessage() != null) {
                        files[i].delete();
                    } else {
                        StringWriter errors = new StringWriter();
                        e.printStackTrace(new PrintWriter(errors));
                    }
                }
            }
        }
    }

    @Override
    public void writeCharacterToStorage(PlayerCharacter character) {
        try {
            Path characterDataFolderPath = null;
            characterDataFolderPath = rootCharacterSafePath.resolve(character.getName()).resolve(playerDataPath);

            UUID characterId = character.id;
            File characterFile = new File(characterDataFolderPath + File.separator + characterId);
            if (!characterFile.exists()) {
                characterFile.createNewFile();
            }

            if (character.id == null) {
                character.id = UUID.randomUUID();
            }

            CharacterStorageData characterStorage = ((PlayableCharacter)character).getStorageData();
            if (characterStorage == null) {
                characterStorage = new CharacterStorageData( characterFile.toPath(), character.id);
                ((PlayableCharacter)character).setCharacterStorage(characterStorage);
                ((PlayableCharacter)character).setCharacterData(characterStorage.getConfig());
            }

            character.updateCharacterStorageData();
            characterStorage.save();
        }

        // if any problem, log it
        catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            SafeLogger.Error(character.id + " " + errors.toString(), e);
        }
    }

    // deletes a character from the file system
    @Override
    public void deleteCharacterFromSecondaryStorage(PlayerCharacter character) {
        try {
            Files.delete(character.getStorageData().filePath);
        } catch (IOException e) {
            e.printStackTrace();
            SafeLogger.Error("Error: Unable to delete character file \"" +
                    ((PlayableCharacter)character).getStorageData().filePath + "\".", e);
        }
    }

    @Override
    public int getSchemaVersionFromStorage() {
        File schemaVersionFile = schemaVersionFilePath.toFile();
        if (schemaVersionFile.exists()) {
            BufferedReader inStream = null;
            int schemaVersion = 0;
            try {
                inStream = new BufferedReader(new FileReader(schemaVersionFile.getAbsolutePath()));

                // read the version number
                String line = inStream.readLine();

                // try to parse into an int value
                schemaVersion = Integer.parseInt(line);
            } catch (Exception e) {
            }

            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException exception) {
            }

            return schemaVersion;
        } else {
            this.updateSchemaVersionInStorage(0);
            return 0;
        }
    }

    @Override
    public void updateSchemaVersionInStorage(int versionToSet) {
        BufferedWriter outStream = null;

        try {
            // open the file and write the new value
            File schemaVersionFile = schemaVersionFilePath.toFile();
            schemaVersionFile.createNewFile();
            outStream = new BufferedWriter(new FileWriter(schemaVersionFile));

            outStream.write(String.valueOf(versionToSet));
        }

        // if any problem, log it
        catch (Exception e) {
            SafeLogger.Error("Unexpected exception saving schema version: ",  e);
        }

        // close the file
        try {
            if (outStream != null) {
                outStream.close();
            }
        } catch (IOException exception) {
        }

    }

    @Override
    public PlayerCharacter getPlayerDataFromStorage(UUID id) {
        return null;
    }

    @Override
    public void overrideSavePlayerData(UUID id, PlayerCharacter playerData) {
    }
}

