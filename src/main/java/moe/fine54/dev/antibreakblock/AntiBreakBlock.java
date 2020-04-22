package moe.fine54.dev.antibreakblock;

import moe.fine54.dev.antibreakblock.command.MainCommand;
import moe.fine54.dev.antibreakblock.listener.BreakBlock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AntiBreakBlock extends JavaPlugin {
    private File worldSettingFile = null;
    private YamlConfiguration worldSetting = null;
    private HashMap<String, ArrayList<Material>> blockList = new HashMap<String, ArrayList<Material>>();

    AntiBreakBlock INSTANCE;
    @Override
    public void onEnable(){
        INSTANCE = this;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BreakBlock(), this);
        Bukkit.getPluginCommand("antibreakblock").setExecutor(new MainCommand());
    }

    public void releaseSetting(){
        this.saveDefaultConfig();
        this.worldSettingFile = new File(this.getDataFolder(), "world.yml");
        if(!this.worldSettingFile.exists()){
            this.saveResource("world.yml", false);
        }
    }

    public void loadSetting(){
        this.worldSettingFile = new File(this.getDataFolder(), "world.yml");
        try {
            worldSetting.load(worldSettingFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        Set<String> keys = worldSetting.getConfigurationSection("worlds").getKeys(false);
        keys.forEach(key->System.out.println(key));
    }

    public AntiBreakBlock getINSTANCE(){
        return INSTANCE;
    }

    public boolean isLastVersion(){
        if(this.getConfig().getDouble("version") == 2.0){
            return true;
        }
        return false;
    }
}
