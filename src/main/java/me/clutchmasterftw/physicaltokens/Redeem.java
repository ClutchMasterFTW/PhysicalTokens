package me.clutchmasterftw.physicaltokens;

import me.clutchmasterftw.physicaltokens.utilities.utilities;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.OptionalLong;
import java.util.logging.Logger;

import static me.clutchmasterftw.physicaltokens.PhysicalTokens.tokenManager;

public class Redeem implements Listener {
    PhysicalTokens plugin;

    public Redeem(PhysicalTokens plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void tokenRedeem(PlayerInteractEvent e) {
        Logger logger = plugin.getLogger();
        Player p = e.getPlayer();
        Action a = e.getAction();
        // Check if right-clicked or shift right-clicked
        if(p.getInventory().getItemInMainHand().isSimilar(utilities.tokenItem(1))) {
            if (!p.isSneaking() && a.isRightClick()) {
                // Right Click only
                int amount = (p.getInventory().getItemInMainHand().getAmount()) - 1;
//                int currentActiveSlot = p.getInventory().getHeldItemSlot();
                if(amount == 0) {
                    ItemStack air = new ItemStack(Material.AIR);
                    p.getInventory().setItemInMainHand(air);
                } else {
                    ItemStack updatedStack = utilities.tokenItem(amount);
                    p.getInventory().setItemInMainHand(updatedStack);
                }
                tokenManager.addTokens(p, 1L);
                p.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r &fYou deposited #005459&l1 &r#005459Token &rinto your account."));
                logger.info(p.getName() + " has deposited 1 Token into their (new) balance of " + tokenManager.getTokens(p) + " Token(s).");
            } else if (p.isSneaking() && a.isRightClick()) {
                // Shift Right Click
                long amount = 0L;
                for(int i = 0; i < 36; i++) {
                    if(p.getInventory().getItem(i) != null) {
                        // Slot is not Air
                        if(p.getInventory().getItem(i).isSimilar(utilities.tokenItem(1))) {
                            // Slot contains Token(s)
                            amount += p.getInventory().getItem(i).getAmount();
                            p.getInventory().setItem(i, new ItemStack(Material.AIR));
                        }
                    }
                }
                tokenManager.addTokens(p, amount);
                if(amount == 1) {
                    p.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r &fYou deposited #005459&l" + amount + " &r#005459Token &rinto your account."));
                } else {
                    p.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r &fYou deposited #005459&l" + amount + " &r#005459Tokens &rinto your account."));
                }
                logger.info(p.getName() + " has deposited " + amount + " Token(s) into their (new) balance of " + tokenManager.getTokens(p).toString() + " Token(s).");
            }
        }
    }
}