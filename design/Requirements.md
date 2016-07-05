# Software Requirements for Adventure Plugin

The implementation of the Plugin is defined by these requirements.

Requirements are uniquely identified by a `RID` (Requirement ID)

A requirement retains it's `RID`.  If a Requirement is changed, the `RID` must change.
If a requirement is deleted, the `RID` is not re-used.

## Race System

RID_1.  The Plugin shall provide the capability for a player to be a race from the list

..1 Human
..2 Elf
..3 Dwarf
..4 Gnome
..5 TBD

## Class System

RID_2. The Plugin shall provide the capability for a player to be a base class.

RID_3. THe Plugin shall provide the capability for a player to be a sub-class.

RID_4. The Plugin shall provide the capability to permit defined sub-classes within a base class.

RID_5. The Plugin shall provide the capability to permit a player to be a base class based on the 

race of the player.
RID_6. THe Plugin shall provide the capaibility to permit a defined race to be compatible

with a defined base class.

## Base Classes and Sub Classes

RID_7. The Plugin shall provide the capability for a player to be a base class from the list

..1 Fighter
..2 Magic User
..3 Rogue
..4 TBD

RID_8. The Plugin shall provide the capability for a player to be a sub class of Fighter from the

list
..1 Paladin
..2 TBD

RID_9. The Plugin shall provide the capability for player to be a sub class of Magic User from 

the list
..1 Cleric
..2 Illusionist
..3 Mage
..4 Wizard

RID_10. The Plugin shall provide the capability for a player to be a sub class of Rogue from

the list
..1 Bard
..2 Assassin
..3 Thief
..4 Rogue

## Capabilities of Races

RID_11. The Plugin shall provide the capability to define capabilities which are defined

by:
..* Name
..* Description
..* Allowed Class(es)
..* Allowed Race(s)
..* Allowed World(s)
..* An Action event
..* A Re-action event


### Capabilities

RID_12.  The Plugin shall provide the capability to define a Capability.

RID_13.  THe Plugin shall provide the capability to persist a capability in a datastore.

RID_14.  The Plugin shall provide the capability to query a player for his/her capabilities.



#### Language Capabilities

RID_15.  THe Plugin shall provide the capability to cause players to speak in a language defined

by their Race.
RID_16.  The Plugin shall provide the capability to cause players to decyper (interpret)

a language.
RID_17.  The Plugin shall provide the capability to allow player to learn a new Language 

subject to criteria.
RID_18. THe Plugin shall provide the capability to override the language barrier between

two different Races.

####  Spell Capabilities

RID_19.  THe Plugin shall provide the capability to have magic spells with parameters:

..* Name
..* Description
..* Proficiency
..* Random Outcome
..* Designed Outcome
..* Quantity
..* Impact on self-Player Parameters (eg., Health)
..* Impact on World (TBD)

RID_20.  The Plugin shall provide the capability to define a magic spell.

RID_21.  The Plugin shall provide the capability to cause player to learn a magic spell.

RID_22.  The Plugin shall provide the capability to allow a player to apply a magic spell that is 

known by the player.
RID_23.  The Plugin shall provide the capability to cause player to forget (lose) a spell.

RID_24.  THe Plugin shall provide the capability to cause the duration of time before a spell

can be re-learned.
RID_25.  The Plugin shall provide the capability to allow application of a spell based on the 

parameters of the spell.


#### Weapon Capabilities

RID_26. The Plugin shall provide the capability to have Weapon Capabilities with

parameters:
..* Name
..* Description
..* Effect on weapon usage
..* Effect on Damage inflicted
..* Impact on self-Player Parameters (eg., Health, Stamina)
..* Impact on World (TBD - Block breaking, or TBD)
..* Curse effect  (negative Enchantment)
..* Enchantment (positive Enchantment)

RID_27. The Plugin shall provide the capability to cause player to learn

a weapon capability.
RID_28. The Plugin shall provide the capability to cause player to apply

a weapon capability known by the player.
RID_29.  The Plugin shall provide the capability to cause player to forget (un-learn)

a weapon capability.
RID_30.  THe Plugin shall provide the capability to allow a player to modify parameters

of a weapon capability.
RID_31.  The PLugin shall provide the capability to allow application of a weapon

capability based on the parameters of
..* The world in which the capability is used.
..* The Race of the target of the capability.
..* THe Class of the target of the capability.

#### Talent Proficiency Capabilities

RID_32. The Plugin shall provide the capability to have Talent Capabilities with

parameters:
..* Name
..* Description
..* Effect on movement
..* Effect on Language use
..* Effect on modifying the world (eg., breaking blocks, placing blocks, etc.. TBD)
..* Effect on Player Class parameters
..* Effect on Player Capabilities (Weapon, Spell, or Language, TBD)


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





