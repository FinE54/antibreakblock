package moe.fine54.dev.antibreakblock.command;

import moe.fine54.dev.antibreakblock.AntiBreakBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand
    implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings[0].equalsIgnoreCase("reload") && strings.length == 1){
            if(commandSender.hasPermission("antibreakblock.reload")) {
                AntiBreakBlock.getINSTANCE().reloadPlugin();
                if(!AntiBreakBlock.getINSTANCE().getErrorMaterials().isEmpty()){
                    commandSender.sendMessage("以下方块未能成功添加, 请检查名字是否拼写错误");
                    commandSender.sendMessage(AntiBreakBlock.getINSTANCE().getErrorMaterials().toString());
                }
                commandSender.sendMessage("插件重载完成");
            }
            return true;
        }
        if(strings[0].equalsIgnoreCase("info") && strings.length == 1){
            if(commandSender instanceof Player){
                commandSender.sendMessage("你手持物品的ID为: "+((Player) commandSender).getInventory().getItemInMainHand().getType().toString());
                return true;
            }
        }
        commandSender.sendMessage("指令错误, 请检查使用的指令");
        return true;
    }
}
