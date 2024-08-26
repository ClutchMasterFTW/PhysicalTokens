package me.clutchmasterftw.physicaltokens;

import me.clutchmasterftw.physicaltokens.utilities.utilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Logger;

public class TokenBlockBreak implements Listener {
    PhysicalTokens plugin;

    public TokenBlockBreak(PhysicalTokens plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.CYAN_CONCRETE) {
            Logger logger = plugin.getLogger();
            Player p = e.getPlayer();
            p.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r You mined a Token Block!"));
            e.setDropItems(false);
            p.getWorld().dropItemNaturally(e.getBlock().getLocation(), utilities.tokenItem(1));
            logger.info(p.getName() + " has broken a Token Block @ " + e.getBlock().getX() + ", " + e.getBlock().getY() + ", " + e.getBlock().getZ() + ".");
        }
    }
}