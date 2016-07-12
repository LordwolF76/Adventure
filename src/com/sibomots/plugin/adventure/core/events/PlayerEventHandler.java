package com.sibomots.plugin.adventure.core.events;

import com.sibomots.plugin.adventure.core.DataStore;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.command.SendCommandEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.MessageChannelEvent;


public class PlayerEventHandler {

    private DataStore dataStore;


    @Listener(order = Order.LATE)
    public void onPlayerChat(MessageChannelEvent.Chat event,  Player player) {

        boolean isPlayerOnLine = player.isOnline();

        event.setCancelled(true);

    }

    @Listener(order = Order.LATE)
    public void onPlayerCommand(SendCommandEvent event, @First Player player)
    {
        String command = event.getCommand();
        String[] args = event.getArguments().split(" ");
        String[] modCommand = command.split(":");

    }

}
