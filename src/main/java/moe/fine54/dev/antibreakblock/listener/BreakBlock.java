package moe.fine54.dev.antibreakblock.listener;

import moe.fine54.dev.antibreakblock.AntiBreakBlock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        String worldName = p.getWorld().getName().toLowerCase();
        Material m = e.getBlock().getType();
        if (AntiBreakBlock.getINSTANCE().getConfig().getBoolean("enable")) {
            if (AntiBreakBlock.getINSTANCE().getWorldList().contains(worldName.toLowerCase())) {
                p.sendMessage("inlist");
                if ("cantbreak".equalsIgnoreCase(AntiBreakBlock.getINSTANCE().getWorldSetting().getString("worlds." + worldName + ".type"))) {
                    p.sendMessage("cantbreak");
                    if (!p.hasPermission("antibreakblock.allow")) {
                        if (AntiBreakBlock.getINSTANCE().getBlockList().get(worldName).contains(m)) {
                            System.out.println("check");
                            doSomething(e);
                        }
                    }
                }
                if ("breakonly".equalsIgnoreCase(AntiBreakBlock.getINSTANCE().getWorldSetting().getString("worlds." + worldName + ".type"))) {
                    p.sendMessage("breakonly");
                    if (!p.hasPermission("antibreakblock.allow")) {
                        if (!AntiBreakBlock.getINSTANCE().getBlockList().get(worldName).contains(m)) {
                            System.out.println("check");
                            doSomething(e);
                        }
                    }
                }
            }
        }
    }

    public void doSomething(BlockBreakEvent e){
        e.setCancelled(!AntiBreakBlock.getINSTANCE().getConfig().getBoolean("break"));
        e.setDropItems(!AntiBreakBlock.getINSTANCE().getConfig().getBoolean("cancelDropITEM"));
        if (AntiBreakBlock.getINSTANCE().getConfig().getBoolean("cancelDropEXP")) {
            e.setExpToDrop(0);
        }
        if (AntiBreakBlock.getINSTANCE().getConfig().getBoolean("sendMessage")) {
            e.getPlayer().sendMessage(AntiBreakBlock.getINSTANCE().getConfig().getString("Message").replace("&", "§"));
        }
    }
}
