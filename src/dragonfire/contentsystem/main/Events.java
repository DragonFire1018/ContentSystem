package dragonfire.contentsystem.main;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Events extends CreateworldCommand implements Listener{
	public static ItemStack item;
	static Location worldsloc;
	//spawning of chest in inv
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
    	Player p = event.getPlayer();
    	PlayerInventory PlayerInventory = p.getInventory();
    		boolean InventoryIsChest = PlayerInventory.contains(Material.CHEST);
	
		if(InventoryIsChest != true) {
			 PlayerInventory.addItem(createGuiItem(Material.CHEST,"ContentSystem", "§aErstelle deine Eigene Welt.", "§bTeleportiere dich zu deiner Welt."));
		}
    }
    //Place Chest 
    @EventHandler 
    public void onClickMainInv(BlockPlaceEvent  event) {
    		Player player = (Player) event.getPlayer();
    		Server s = event.getPlayer().getServer();

             Block Block = event.getBlock();
             
            if (Block.getType() == Material.CHEST) {
            	CreateGui();
            	openInventory(player);
            	event.setCancelled(true);
            	
            	UUID UUid = player.getUniqueId();
            	
            	ContentFile manager = new ContentFile(UUid);
        		
        		Set<String> worlds = manager.getWorlds();
        		Object[] worldslist = worlds.toArray();
        		int size = worldslist.length;
        		//System.out.println(worldslist);
        		for(int i=0; i<size;i++) {
        			String WorldName = worldslist[i].toString();
        		    //System.out.println(WorldName);
        			item = createGuiItem(Material.ENDER_PEARL, "Teleportiere Dich zur Welt.", WorldName);
        			inv.addItem(item);
        			worldsloc = new Location(s.getWorld(WorldName), 
          		            s.getWorld(WorldName).getSpawnLocation().getX(),
          		            s.getWorld(WorldName).getSpawnLocation().getY(),
          		            s.getWorld(WorldName).getSpawnLocation().getZ());
        		}
            }

    }
   //Stopping player from dropping the chest
    @EventHandler
    public void noDrop(PlayerDropItemEvent event) {
    	event.setCancelled(true);
    	Item item = event.getItemDrop();
    	ItemStack isStack = item.getItemStack();
        if(isStack.getType() == Material.CHEST) {
        	event.setCancelled(true);
        }
    }
    //Chancel moving the chest  
    @EventHandler
    public void onClickSlot(InventoryClickEvent e) {
    	e.setCancelled(true);
    	Inventory inv = e.getInventory();
    	ItemStack stackItem = e.getCurrentItem();
        if(e.getView().getTopInventory().getHolder() == inv) {
           if(stackItem.getType() == Material.CHEST) {
        	   e.setCancelled(true);
           }
        }
    }
    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
    	Inventory inv = e.getInventory();
        if (e.getInventory().equals(inv)) {
          e.setCancelled(true);
        }
    }
    // Clicking on the flint
   @EventHandler
    public void onClick(InventoryClickEvent event) {
	   Server s = event.getWhoClicked().getServer();
	   
	   Location Spawn = new Location(s.getWorld("world"),
	                                 s.getWorld("world").getSpawnLocation().getX(),
                                     s.getWorld("world").getSpawnLocation().getY(),
                                     s.getWorld("world").getSpawnLocation().getZ());
	   
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                ItemStack itemStack = event.getCurrentItem();
                if(itemStack.getType() == Material.FLINT) {
                	player.sendMessage("§aDu kannst mit /erstellewelt + <Name> der Welt , eine Welt erstellen.");
                }
                if(itemStack.getType() == Material.BARRIER) {
                	player.sendMessage("§aDu kannst mit /entfernewelt + <Name> der Welt , eine erstellte Welt löschen.");
                }
                if(itemStack.getType() == Material.ENDER_PEARL) {
                	player.teleport(worldsloc);
                }        
                if(itemStack.getType() == Material.SLIME_BALL) {
                	player.teleport(Spawn);
                }
                
                if (itemStack == null || itemStack.getType() == Material.AIR) {
                    return;
                }

            }
     }

}
