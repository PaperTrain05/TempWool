package io.paper.tempwool.events;

import io.paper.tempwool.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class Events implements Listener {
    @EventHandler
    public void blockPlaced(final BlockPlaceEvent e) {
        if (e.isCancelled()) {
            return;
        }
        final Block block = e.getBlock();
        if (!BlockManager.builderList.contains(e.getPlayer()) && block.getLocation().getY() > 200.0) {
            e.setCancelled(true);
            return;
        }
        if (block.getType() != Material.WOOL) {
            if (!BlockManager.builderList.contains(e.getPlayer())) {
                e.setCancelled(true);
            }
        }
        else {
            final List<Block> blocks = new ArrayList<Block>();
            blocks.add(block);
            switch (block.getType()) {
                case WOOL: {
                    final Location l = block.getLocation();
                    e.getBlock().getWorld();
                    final int wool = Main.config.getInt("cooldown.wool");
                    this.scheduleTask(blocks, wool);
                    break;
                }
            }
        }
    }

    public void scheduleTask(final List<Block> blocks, final Integer cooldown) {
        Bukkit.getScheduler().runTaskLater(Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                for (final Block b : blocks) {
                    b.setType(Material.AIR);
                }
            }
        }, (long)(cooldown * 20));
    }

    @EventHandler
    public void blockBroken(final BlockBreakEvent e) {
        if (!BlockManager.builderList.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
