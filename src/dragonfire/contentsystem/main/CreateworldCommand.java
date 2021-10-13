package dragonfire.contentsystem.main;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateworldCommand implements CommandExecutor {
	public static Location loc;
	public static Inventory inv;
	public static ItemStack createitem;
	static boolean Commandtrue;
	@Override
	public boolean onCommand(CommandSender cs, org.bukkit.command.Command arg1, String arg2, String[] args) {
		 
		 if (!(cs instanceof Player)) return true; 
		   Player p = (Player)cs;
		   UUID UUIDplayer = p.getUniqueId();
		     if (args.length == 0) {
				p.sendMessage("/erstellewelt <Name>");
				return true;
		     }
		     if(args.length == 1)  {
		    	 
				ContentFile manager = new ContentFile(UUIDplayer);
				 
                 WorldCreator c = new WorldCreator(args[0]);
                 
                 if(!(manager.getWorlds().size() < 5  )) {    
     				p.sendMessage("Du kannst nur maximal 3 Homes setzen!");
     				return true;
     			}
                 p.sendMessage("Welt "+args[0]+" wird erstellt...");
                 
				 c.environment(Environment.NORMAL);
				 c.type(WorldType.FLAT);
				 c.createWorld();
			     
			     manager.addWorld(args[0]);
			     createitem = createGuiItem(Material.ENDER_PEARL, "§bTeleportiere Dich zur Welt.", args[0]); 
			        
			     p.sendMessage("Die Welt " + args[0] + " wurde erstellt.");
			     //exist = manager.exist(args[0]);
			     Commandtrue = true;
			     return true;
			   }
		return false;
	}
	   public void CreateGui() {
	       // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
	       inv = Bukkit.createInventory(null, 18, "Gui");

	       // Put the items into the inventory
	       initializeItems();
	   }
	   // You can call this whenever you want to put the items in
	   public void initializeItems() {
	       inv.addItem(createGuiItem(Material.FLINT, "§bErstelle eine Welt", "§a/erstellewelt + <Name>"));
	       inv.addItem(createGuiItem(Material.BARRIER, "§bLösche eine Welt", "§a/entfernewelt + <Name>"));
	       inv.addItem(createGuiItem(Material.SLIME_BALL, "§bTeleportiere Dich zum Spawn"));
	       if(Commandtrue == true) {
		    inv.addItem(createitem);
	       }
	       
	   }
	   // Nice little method to create a gui item with a custom name, and description
	   protected static ItemStack createGuiItem(final Material material, final String name, final String... lore) {
	       final ItemStack item = new ItemStack(material, 1);
	       final ItemMeta meta = item.getItemMeta();

	       // Set the name of the item
	       meta.setDisplayName(name);

	       // Set the lore of the item
	       meta.setLore(Arrays.asList(lore));

	       item.setItemMeta(meta);

	       return item;
	   }
	   // You can open the inventory with this
	   public void openInventory(final HumanEntity ent) {
	       ent.openInventory(inv);
	   }

}
