//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.clutchmasterftw.physicaltokens;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public final class PhysicalTokens extends JavaPlugin {
    public PhysicalTokens() {
    }

    public void onEnable() {
        Logger logger = this.getLogger();
        logger.info("PhysicalTokens has successfully loaded!");
        logger.info("Created by ClutchMasterFTW | 2024");
        this.getCommand("tw").setExecutor(new CommandTW(this));
        this.getServer().getPluginManager().registerEvents(new Redeem(this), this);
        this.getServer().getPluginManager().registerEvents(new TokenBlockBreak(this), this);
    }

    public void onDisable() {
        Logger logger = this.getLogger();
        logger.info("PhysicalTokens has been disabled!");
    }
}
