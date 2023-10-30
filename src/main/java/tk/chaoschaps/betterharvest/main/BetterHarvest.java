package tk.chaoschaps.betterharvest.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import tk.chaoschaps.betterharvest.util.Sickle;

public final class BetterHarvest extends JavaPlugin {
    void registerWhen(boolean when, Class<? extends Listener> listener) {
        try { if(when) Bukkit.getPluginManager().registerEvents(listener.getDeclaredConstructor().newInstance(), this); } catch (Exception e) {}
    }
    @Override
    public void onEnable() {

        Sickle.registerSickle(this);

        //register Event Listener
        registerWhen(true,	Harvest.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
