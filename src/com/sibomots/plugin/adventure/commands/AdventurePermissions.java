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

/**
 * Adding a command permission:
 *
 * public static final String COMMAND_NAME_OF_PERMISSION
 *          = "string representation of permission node";
 *
 */
public class AdventurePermissions {

    // Commands for EVERYONE
    public static final String COMMAND_LICENSE
            = "adventure.everyone.license";
    public static final String COMMAND_HELP
            = "adventure.everyone.help";
    public static final String COMMAND_ABOUT
            = "adventure.everyone.about";

    // Concept command has a <target>
    // <target> is a text string that may exist in the Concept glossary
    public static final String COMMAND_CONCEPT
            = "adventure.everyone.concept";

    // List all targets in the Concept glossary
    public static final String COMMAND_CONCEPT_LIST
            = "adventure.everyone.concept.list";

    // COMMANDS FOR PLAYER+
    //  STAFF inherit all commands adventure.everyone.*
    //  Permission sysystem advice:  TO remove a specific command from Player
    //  then "subtract" that command from the Player rank.
    public static final String COMMAND_PLAYER_ALL
            = "adventure.player.all";

    // Character generation commands:
    //  Command for Player+ to make one self a character in the Adventure system
    public static final String COMMAND_MAKE_NEW_CHARCTER
            = "adventure.player.character.make.new";

    //  Score commands:
    public static final String COMMAND_SCORE_SELF
            = "adventure.player.score.self";


    // COMMANDS FOR STAFF
    //  STAFF inherit all commands adventure.everyone.*,  adventure.player.*
    //  Permission sysystem advice:  TO remove a specific command from Staff
    //  then "subtract" that command from the Staff rank.
    public static final String COMMAND_STAFF_ALL
            = "adventure.staff.all";

    // Command for Staff to make a character for player <target>
    public static final String COMMAND_MAKE_CHARACTER
            = "adventure.staff.character.make";
    // Command for Staff to change a character for player <target>
    public static final String COMMAND_CHANGE_CHARACTER
            = "adventure.staff.character.change";

    // Score commands:
    public static final String COMMAND_SCORE_OTHER
            = "adventure.staff.score.other";
    public static final String COMMAND_SCORE_ALL
            = "adventure.staff.score.all";

    // COMMANDS FOR ADMIN+
    //  ADMIN inherit all commands adventure.everyone.*,  adventure.player.*
    //  and adventure.staff.*
    //  Permission sysystem advice:  TO remove a specific command from Admin
    //  then "subtract" that command from the Admin rank.
    public static final String COMMAND_ADMIN_ALL
            = "adventure.admin.all";

}
