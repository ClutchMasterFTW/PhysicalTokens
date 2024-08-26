//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.clutchmasterftw.physicaltokens.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class utilities {
    public utilities() {
    }

    public static String color(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

        for(Matcher matcher = pattern.matcher(message); matcher.find(); matcher = pattern.matcher(message)) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');
            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            char[] var7 = ch;
            int var8 = ch.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                char c = var7[var9];
                builder.append("&" + c);
            }

            message = message.replace(hexCode, builder.toString());
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static ItemStack tokenItem(Integer amount) {
        ItemStack stack = new ItemStack(Material.ECHO_SHARD, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(color("#005459Token"));
        List<String> loreList = new ArrayList();
        loreList.add(color("&fTokens are a currency, used for"));
        loreList.add(color("&fenchanting tools, armor, and more."));
        meta.setLore(loreList);
        stack.setItemMeta(meta);
        return stack;
    }

    public static boolean inventoryCheck(Player p, Integer amount) {
        int spaceAvailable = 0;

        for(int i = 0; i < 36; i++) {
            if (p.getInventory().getItem(i) == null) {
                // Slot is empty
                spaceAvailable += 64;
            } else {
                // Slot contains tokens
                int slotQuantity = p.getInventory().getItem(i).getAmount();

                if (slotQuantity < 64 && p.getInventory().getItem(i).isSimilar(tokenItem(1))) {
                    spaceAvailable += 64 - slotQuantity;
                }
            }
        }

//        p.sendMessage("You have " + spaceAvailable + " space available.");

        if(spaceAvailable >= amount) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        } else {
            try {
                int var1 = Integer.parseInt(strNum);
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        }
    }
}
