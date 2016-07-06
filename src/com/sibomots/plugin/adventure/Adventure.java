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
package com.sibomots.plugin.adventure;


import com.google.inject.Inject;
import com.sibomots.plugin.adventure.configuration.partitions.AdventureConfig;
import com.sibomots.plugin.adventure.configuration.partitions.GlobalConfig;
import com.sibomots.plugin.adventure.core.DataStore;
import com.sibomots.plugin.adventure.core.SafeLogger;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;

@Plugin(id = "adventure", name = "Adventure", version = "1.0.0", description = "Adventure Time!")

public class Adventure {

    public static Adventure instance;
    public static final String MOD_ID = "Adventure";
    @Inject
    public PluginContainer pluginContainer;


    @Inject private Game game;
    @Inject private Logger logger;

    public static AdventureConfig<GlobalConfig> getGlobalConfig()
    {
        return DataStore.globalConfig;
    }

    @Listener
    public void onPreInitialization(GamePreInitializationEvent event) {
        SafeLogger.Info("OnPreInitialzation");
    }

    @Listener
    public void onInitialization(GameInitializationEvent event) {
        SafeLogger.Info("OnInitialization");
    }

    @Listener
    public void onAboutToStart(GameAboutToStartServerEvent event) {
        SafeLogger.Info("OnAboutToStart");
        instance = this;
        SafeLogger.Info("Assigned instance to Plugin core");
    }

    @Listener
    public void onServerStarted(GameStartedServerEvent event) {
        registerBaseCommands();
        SafeLogger.Info("OnServerStartedHandler :: All commands Loaded successfully.");
    }

    public static void LogMessage(String msg)
    {
        Adventure.instance.logger.info(msg);
    }

    public boolean registerBaseCommands() {
        boolean isSuccess = false;

        try {
            // Sample command -- just to see how this all works
            Sponge.getCommandManager().register(this, CommandSpec.builder()
                    .description(Text.of("Prints the Plugin License message"))
                    .permission(AdventurePermissions.COMMAND_LICENSE)
                    .executor(new CommandLicense())
                    .build(), "license", "lic");

            // no issues?
            isSuccess = true;
        }
        catch(Exception e)
        {
            isSuccess = false;
        }

        return isSuccess;
    }

}
