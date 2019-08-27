# PrisonCore

*I forgot, but a lot of the commit titles are useless because I never thought anyone else would see the repo*

This project was a modification to the way you play Minecraft.  It was made to be run on Minecraft Servers as the foundation of the modifications.

The core feature of it was the Prison gamemode.

## Prison ##

The Prison gamemode was a server type that was popular for a while in the pvp (Player Vs Player combat) community.

Each player was assigned the `A` rank when they joined for the first time.

Each rank `(A-Z)` would contain a mine and a shop, allowing you to access more and more content as you ranked up.

It featured an economy system where players would sell ores from the mine to get to the next rank, with increasing difficulty in price for ranking up.

Once you got to the `Z` rank, you had the option of prestiging, which would set you back at rank `A`, but you would get certain benefits like an increased selling price.

This of course by itself is pretty boring, but in addition to this, there is the aspect of land claiming and pvp.

### Land Claiming

Each player could get one plot of land to build and store things on, but donators to the server (via the website & Paypal ***(seperate code for the purchases)***) could get more plots with crates or a purchasable rank.

### PvP

While people can slowly increase what they have to mine with, there is also the option of getting pickaxes from someone else.  There was a designated area for pvp with indestructable terrain, however most people used the designated 'PvP mine', which had a destructable enviroment.

In addition to the normal type of combat in Minecraft, players also had the choice of using custom "drugs".  These were essentially just stuff like Sugar which players could use by holding and Shift+Right Clicking the item.

Some of the custom "drugs":

* Sugar - Provides the speed potion effect so that players can run faster
* Gun Powder - Provides the player with a short buff in strength and therefor damage output
* Ghast Tear - Provides the player with jump boost, mining fatigue, regeneration, and a faster speed than sugar

### Voting

In order to promote the server, we signed up for several sites for listings, which would be voted for to increase our ranking.

Players could vote once every 24h on each website, and via a common API, a rewards system was introduced for players that voted.
Players who voted would recieve a key for a vote crate, which had a chance of containing a paid rank, in-game currency, kits, or items.

### Kits

The kits system allows for donators and voters to gain access to items redeemable every 24h (depending on the kit).

Upon using the /kit command, a GUI is presented to the player to select one of the kits they have access to.  Once used, the date timestamp as a long is saved to their data file, which is later used to check how long it has been since they last used it.

Players new to the server would not have any timestamp saved, so it was assumed if the timestamp is null that the player is new or had their data cleared.

### Custom Enchants

One of the largest parts of the codebase, the custom enchants system is a modified version of one of my earlier projects.  At one point I was trying to clean up the code, but currently I'm not sure if that is in this version or not.  The custom enchants system is very messy, and I'd like to sort it out, however that's a lot of time I don't want to spend right now on a project I don't think I'll come back to making.

### Misc

Among these things, there are a lot of minor features like holograms, cosmetic tags, events (like a pvp tournament semi-automated), and more.
