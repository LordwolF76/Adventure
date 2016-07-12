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
 * In these permissions, there's the assumption that there are three tiers of permissions:
 *
 * Tier 1:  Tier 1 is the player rank (the non privledged rank).  Call it player, or member,
 *          or whatever in your Permission system, to this plugin it's simply referred to as
 *          the 'player' rank.
 *
 * Tier 2:  Tier 2 is the rank with privledges.  These privledges may be minor or as deep as
 *          the "owner" of the game.  It makes no difference what you call them in your
 *          permission system you've used.  For this plugin, the 'staff' rank in the permission
 *          node just implies a level of access that is priviledged but not the Odin-almighty owner.
 *
 * Tier 3:  Tier 3 is the rank with all and any priviledges.  This is equivalent to the rank
 *          that is given or assumed by the "owner" of the game, or at the least a rank where all the
 *          trust is placed.   If your server has important staff deemed Admins and you want to restrict
 *          just a portion of what this plugin would naturally recommend toving to Admins, then adjust
 *          the permissions via your permissions system - but not here.
 *
 * Thus,  all the permission nodes are named by the recommended rank that would be likely to have
 * that permision node.  If you wish to alter these recommendations, do so in your permissions system (plugin).
 *
 */
public class AdventurePermissions {

    // Commands for ALL

    // ANY AND ALL COMMANDS are acessed via  /adv COMMAND_SYNTAX

    // TODO Insert grammar (PDF) for all commands


    // The command to get the license of the plugin.
    // Usage:  /adv license, /adv lic
    public static final String COMMAND_LICENSE
            = "adventure.all.license";

    // The command to get help for the plugin.
    // Usage: /adv help
    public static final String COMMAND_HELP
            = "adventure.all.help";

    // The command to get information "about the plugin"
    // Usage: /adv about
    public static final String COMMAND_ABOUT
            = "adventure.all.about";

    // Concept command has a <target>
    // <target> is a text string that may exist in the Concept glossary
    // Usage:  /adv concept <target>
    public static final String COMMAND_CONCEPT
            = "adventure.all.concept";

    // List all targets in the Concept glossary
    // Usage:  /adv conceptlist

    public static final String COMMAND_CONCEPT_LIST
            = "adventure.all.concept.list";

    // COMMANDS FOR PLAYER+
    //  STAFF inherit all commands adventure.all.*
    //  Permission sysystem advice:  TO remove a specific command from Player
    //  then "subtract" that command from the Player rank.
    public static final String COMMAND_PLAYER_ALL
            = "adventure.player.all";

    // PlayerCharacter generation commands:
    //  Command for Player+ to make one self a character in the Adventure system
    //
    //  If a player of the game has not made a player character, then they would issue:
    //
    //   /adv make player
    //
    //  If the player already has a player character, then they need to use:
    //
    //  /adv cancel character
    //
    // There are limits:  A player can only make a new character once per day.
    // After a new character is made, they may only cancel it within 2 weeks.
    // (these limits are configurable)
    //
    // A new character can only be made three times.  After cancling the third character
    // the player may not make new characters.
    // (these limits are configurable)

    public static final String COMMAND_MAKE_NEW_CHARCTER
            = "adventure.player.character.make.new";

    public static final String COMMAND_CANCEL_CHARCTER
            = "adventure.player.character.cancel";


    //  Score commands:
    public static final String COMMAND_SCORE_SELF
            = "adventure.player.score.self";


    // COMMANDS FOR STAFF
    //  STAFF inherit all commands adventure.all.*,  adventure.player.*
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
    //  ADMIN inherit all commands adventure.all.*,  adventure.player.*
    //  and adventure.staff.*
    //  Permission sysystem advice:  TO remove a specific command from Admin
    //  then "subtract" that command from the Admin rank.
    public static final String COMMAND_ADMIN_ALL
            = "adventure.admin.all";

}
