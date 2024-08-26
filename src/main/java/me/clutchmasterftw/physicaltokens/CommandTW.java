//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.clutchmasterftw.physicaltokens;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import me.clutchmasterftw.physicaltokens.utilities.utilities;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandTW implements CommandExecutor {
    PhysicalTokens plugin;
    public CommandTW(PhysicalTokens plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logger logger = plugin.getLogger();
        Player player = (Player)sender;
        TokenManager tokenManager = (TokenManager)Bukkit.getPluginManager().getPlugin("TokenManager");
        if (args.length == 1 && utilities.isInteger(args[0])) {
            long balance;
            try {
                balance = tokenManager.getTokens(player).getAsLong();
            } catch (NoSuchElementException var10) {
                sender.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r &cThere was a problem attempting to withdraw. Please contact an Administrator if this issue persists."));
                return true;
            }

            if (balance >= Long.parseLong(args[0]) && Integer.parseInt(args[0]) > 0) {
                if (utilities.inventoryCheck(player, Integer.parseInt(args[0]))) {
                    logger.info(player.getName() + " has withdrawn " + args[0] + " Tokens from their balance of " + tokenManager.getTokens(player).toString() + " Token(s).");
                    if(Integer.parseInt(args[0]) == 1) {
                        sender.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r You have withdrawn #005459&l" + args[0] + "&r#005459 Token&r."));
                    } else {
                        sender.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r You have withdrawn #005459&l" + args[0] + "&r#005459 Tokens&r."));
                    }
                    tokenManager.removeTokens(player, Long.parseLong(args[0]));
                    ((Player)sender).getInventory().addItem(utilities.tokenItem(Integer.parseInt(args[0])));
                    return true;
                } else {
                    sender.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r &cYou don't have enough inventory space to withdraw this quantity of tokens."));
                    return true;
                }
            } else {
                sender.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r &cInvalid amount, or you don't have that many tokens."));
                return true;
            }
        } else {
            sender.sendMessage(utilities.color("&9Wreck&1MC&r &7»&r &cCorrect usage: /tw (amount)"));
            return true;
        }
    }
}