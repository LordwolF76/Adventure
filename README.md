# Adventure - the plugin


This is a plugin for Sponge.  At the current time it's scoped to provide support in the server context for 
allowing players to have an Adventure-Time like experience.

It's inspired from my play with:

  * Dungeons and Dragons
  * Traveller
  * Gamma World
  * Burning Wheel



## Design Goals

The design goals of the plugin are as follows:

### Player Races

* Human
* Elf
* Gnome
* Dwarf
* Creatures (TBD)

### Player Classes

* Fighter
* Magic User
* Cleric
* Assassin
* Bard
* Paladin
* Thief
* Rogue


### Attributes

* Languages
* Spells
* Weapon Proficiency
* Talent Proficiency (stalking, creeping, stealing, mining, etc..)

### Items

* Magical Items (wands, orbs, gems, etc...)
* Weapon Items (enchanted weapons)

### World Generation

The other aspect of the plugin is to provide some interesting new worlds to transport to.  This will require some 
engineering to manage the properties of the new world and why/what these new attributes mean in the new portals.

For example, if a player (typically magic user or other magic aware player class) can arrange secret objects in 
some pattern, then this act produces a portal.  Or the acquisition of a certain set of magic objects elevates a
player to a new awareness of another plane of existance (a world so to speak).

All of this is still being formulated, but the underlying requirement is for there to be new worlds that can
be reached by serendipity.


## Technical Concerns

So far much of this can be implemented as a Sponge Plugin.  In the future when (if) special capabilities that extend
to the client allow for the implementation of custom objects (based on vertex meshes, textures, and the like -- think
Totem mod) then we'll have reached code-complete.

For the near term, stage one will be the capabilities that can be reached through the use of Sponge API 4.X

## Alternative Goals

This plugin can (and should) be used as a template for those who wish to try their hand at making Sponge Plugins.
A lot of the technical setup (gradle, source organization, etc..) can be taken from here to adapt to a new Plugin
project. I'm using (trying to at least) the best practices that are promulgated by well known Sponge developers.  If
there is a better way to do something, I'm all ears.

