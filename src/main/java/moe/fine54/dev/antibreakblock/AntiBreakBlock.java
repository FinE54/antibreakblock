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
    private YamlConfiguration worldSetting = new YamlConfiguration();
    private HashMap<String, ArrayList<Material>> blockList = new HashMap<String, ArrayList<Material>>();
    private ArrayList<String> errorMaterials = new ArrayList<>();
    private Set<String> worldList = null;

    static AntiBreakBlock INSTANCE;

    @Override
    public void onEnable(){
        INSTANCE = this;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BreakBlock(), this);
        Bukkit.getPluginCommand("antibreakblock").setExecutor(new MainCommand());
        releaseSetting();
        loadSetting();
    }

    // 初始化配置文件数值
    public void releaseSetting(){
        this.saveDefaultConfig();
        this.reloadConfig();
        this.worldSettingFile = new File(this.getDataFolder(), "world.yml");
        if(!this.worldSettingFile.exists()){
            this.saveResource("world.yml", false);
        }
    }

    // 载入配置文件
    public void loadSetting(){
        this.worldSettingFile = new File(this.getDataFolder(), "world.yml");
        try {
            worldSetting.load(this.worldSettingFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        worldList = worldSetting.getConfigurationSection("worlds").getKeys(false);
        errorMaterials.clear();
        blockList.clear();
        if(worldList != null) worldList.forEach(key->addList(key));
    }

    // 将配置文件中的数据写入数组
    public void addList(String world){
        ArrayList<Material> blocks = new ArrayList<>();
        worldSetting.getStringList("worlds."+world+".list").forEach(key-> {
            try{
                blocks.add(Material.valueOf(key));
            } catch(IllegalArgumentException e){
                errorMaterials.add(key);
            }
        });
        blockList.put(world, blocks);
    }

    // 重载插件配置
    public void reloadPlugin(){
        releaseSetting();
        loadSetting();
    }

    // 检查插件是否是最新版本
    public boolean isLastVersion(){
        return this.getConfig().getDouble("version") == 2.0;
    }

    public static AntiBreakBlock getINSTANCE(){
        return INSTANCE;
    }
    public YamlConfiguration getWorldSetting() {
        return worldSetting;
    }
    public Set<String> getWorldList() {
        return worldList;
    }
    public HashMap<String, ArrayList<Material>> getBlockList() {
        return blockList;
    }
    public ArrayList<String> getErrorMaterials() {
        return errorMaterials;
    }
}
