package dragonfire.contentsystem.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelworldCommand implements CommandExecutor{
	@Override
	public boolean onCommand( CommandSender cs, Command arg1, String arg2, String[] args) {
		if (!(cs instanceof Player)) return true;
		
		Player p = (Player)cs;
		
		ContentFile manager = new ContentFile(p.getUniqueId());
		
		if (args.length == 0) {
			p.sendMessage("/entfernewelt <Name>");
	        return true;
		}
		if(args.length == 1) {
			
			if (!manager.exist(args[0])) {
				p.sendMessage("Diese Welt existiert nicht");
				return true;
			}
			manager.delWorld(args[0]);
			File challenge = new File(args[0]);
            if (challenge.exists()) {
                
                Bukkit.unloadWorld(args[0], true);

                File data = new File(args[0]+"\\data");
                if (data.exists()) {
                for (File files1 : data.listFiles()) {
                    files1.delete();
                }
                data.delete();
                }
                
                File playerdata = new File(args[0]+"\\playerdata");
                if (playerdata.exists()) {
                for (File files2 : playerdata.listFiles()) {
                    files2.delete();
                }
                playerdata.delete();
                }
                
                File poi = new File(args[0]+"\\poi");
                if (poi.exists()) {
                for (File files3 : poi.listFiles()) {
                    files3.delete();
                }
                poi.delete();
                }
                
                File region = new File(args[0]+"\\region");
                if (region.exists()) {
                for (File files4 : region.listFiles()) {
                    files4.delete();
                }
                region.delete();
                }
                
                for (File files5 : challenge.listFiles()) {
                    files5.delete();
                }
                challenge.delete();
            }


			
			p.sendMessage("Die Welt " + args[0] + " wurde gelöscht");
			return true;
		}
		return false;
	}

}
