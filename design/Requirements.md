# Software Requirements for Adventure Plugin

The implementation of the Plugin is defined by these requirements.

Requirements are uniquely identified by a `RID` (Requirement ID)

A requirement retains it's `RID`.  If a Requirement is changed to the point
where the intent of the requirement is different, the `RID` must change.  
If a requirement is deleted, the `RID` is not re-used.

## Race System

--- | ---
RID-1 | The Plugin shall provide the capability for a player to be a race from the list
 | Human
 | Elf
 | Dwarf
 | Gnome
 | TBD

## Class System

--- | ---
RID-2 | The Plugin shall provide the capability for a player to be a base class.
RID-3 | THe Plugin shall provide the capability for a player to be a sub-class.
RID-4 | The Plugin shall provide the capability to permit defined sub-classes within a base class.
RID-5 | The Plugin shall provide the capability to permit a player to be a base class based on the  race of the player.
RID-6 | THe Plugin shall provide the capaibility to permit a defined race to be compatible
with a defined base class.

## Base Classes and Sub Classes

RID-7 | The Plugin shall provide the capability for a player to be a base class from the list
 | Fighter
 | Magic User
 | Rogue
 | TBD
RID-8 | The Plugin shall provide the capability for a player to be a sub class of Fighter from the list
 | Paladin
 | TBD
RID-9 | The Plugin shall provide the capability for player to be a sub class of Magic User from the list
 | Cleric
 | Illusionist
 |  Mage
 | Wizard
RID-10 | The Plugin shall provide the capability for a player to be a sub class of Rogue from the list
 | Bard
 | Assassin
 | Thief
 | Rogue


## Capabilities of Races

--- | ---
RID-11 | The Plugin shall provide the capability to define capabilities which are defined by:
 | Name
 | Description
 | Allowed Class(es)
 | Allowed Race(s)
 | Allowed World(s)
 | An Action event
 | A Re-action event

### Capabilities

--- | ---
RID-12 | The Plugin shall provide the capability to define a Capability.
RID-13 | THe Plugin shall provide the capability to persist a capability in a datastore.
RID-14 | The Plugin shall provide the capability to query a player for his/her capabilities.

#### Language Capabilities

--- | ---
RID-15 | THe Plugin shall provide the capability to cause players to speak in a language defined by their Race.
RID-16 | The Plugin shall provide the capability to cause players to decyper (interpret) a language.
RID-17 | The Plugin shall provide the capability to allow player to learn a new Language subject to criteria.
RID-18 |THe Plugin shall provide the capability to override the language barrier between two different Races.

####  Spell Capabilities

--- | ---
RID-19 | THe Plugin shall provide the capability to have magic spells with parameters:
 | Name
 | Description
 | Proficiency
 | Random Outcome
 | Designed Outcome
 | Quantity
 | Impact on self-Player Parameters (eg., Health)
 | Impact on World (TBD)
RID-20 | The Plugin shall provide the capability to define a magic spell.
RID-21 | The Plugin shall provide the capability to cause player to learn a magic spell.
RID-22 | The Plugin shall provide the capability to allow a player to apply a magic spell that is known by the player.
RID-23 | The Plugin shall provide the capability to cause player to forget (lose) a spell.
RID-24 | THe Plugin shall provide the capability to cause the duration of time before a spell
can be re-learned.
RID-25 | The Plugin shall provide the capability to allow application of a spell based on the parameters of the spell.


#### Weapon Capabilities

--- | ---
RID-26 | The Plugin shall provide the capability to have Weapon Capabilities with parameters:
 | Name
 | Description
 | Effect on weapon usage
 | Effect on Damage inflicted
 | Impact on self-Player Parameters (eg., Health, Stamina)
 | Impact on World (TBD - Block breaking, or TBD)
 | Curse effect  (negative Enchantment)
 | Enchantment (positive Enchantment)
RID-27 | The Plugin shall provide the capability to cause player to learn a weapon capability.
RID-28 | The Plugin shall provide the capability to cause player to apply a weapon capability known by the player.
RID-29 | The Plugin shall provide the capability to cause player to forget (un-learn) a weapon capability.
RID-30 | THe Plugin shall provide the capability to allow a player to modify parameters of a weapon capability.
RID-31 | The PLugin shall provide the capability to allow application of a weapon capability based on the parameters of
 | The world in which the capability is used.
 | The Race of the target of the capability.
 | The Class of the target of the capability.

#### Talent Proficiency Capabilities

--- | ---
RID-32 | The Plugin shall provide the capability to have Talent Capabilities with parameters:
 | Name
 | Description
 | Effect on movement
 | Effect on Language use
 | Effect on modifying the world (eg., breaking blocks, placing blocks, etc.. TBD)
 | Effect on Player Class parameters
 | Effect on Player Capabilities (Weapon, Spell, or Language, TBD)


## Play Dynamics

(There are requirements that involve the dynamics of the game, TBD)


## World Dynamics

(THere are requirements that involve the dynamics of how worlds are made, visited,
departed, portals to reach, etc.. TBD)

This section covers:
* the creation of worlds.
* visiting of worlds
* modifying worlds.
* destruction of worlds.

Requirements TBD.

## Items

(THere are requirements that involve the creation, management and placement
of items in the game.  These objects are termporarily defined as in-game objects
of the given Minecraft server.  Details for Items are TBD.)

THis section covers:

* The creation of items
* THe disposition of items in the world, on the player
* The connection between an object and a goal of the player

Requirements TBD.

## Player Goals

(THere are requirements that involve the creation, management, and
discovery of Goals a.k.a. "Quests'.  These goals are determined by
the Plugin either by defined goals related to certain Items, or 
goals related to certain actions taken by players.)

This section covers:

* The creation of a Goal
* The management of Goals (creation, reading, updating, deletion)
* The association of a Goal to a World, Player, Item

Requirements TBD.





