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
package com.sibomots.plugin.adventure.commands;


import com.sibomots.plugin.adventure.core.UserPlayerIdentityUtilities;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

public class CommandScore implements CommandExecutor {

    /**
     * Internal handler for the command.  Retreives the data internally
     * and provides the functionality of the command.  Called by the
     * interface defined Executor.
     * @param player
     * @param arg
     * @throws CommandException
     */
    private void handleCommand(CommandSource player, String arg) throws CommandException
    {
        if ( player instanceof Player)
        {
            // DO SOMEHTING (TODO)
            User usr = UserPlayerIdentityUtilities.resolvePlayerByName(arg).orElse(null);
          //  Score data = Adventure.getDataStore().getPlayerScore(usr).orElse(null);
        }
        else
        {
            throw new CommandException(Text.of("The command source to execute Player Score was not a player object"));
        }
    }


    /**
     * The executor of the command per the Interface.
     * May call upon internal functions to this class to help fulfill the
     * completion of the command action.
     * @param src
     * @param cmdContext
     * @return CommandResult
     */
    @Override
    public CommandResult execute(CommandSource src, CommandContext cmdContext) {
        try {
            String arg = cmdContext.<String>getOne("subject").get();
            handleCommand(src, arg);
        } catch (CommandException e) {
            src.sendMessage(e.getText());
        }
        return CommandResult.success();
    }
}
