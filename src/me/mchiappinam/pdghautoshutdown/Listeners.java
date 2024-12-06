package me.mchiappinam.pdghautoshutdown;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Listeners implements Listener {
	private Main plugin;
	
	public Listeners(Main main) {
		plugin=main;
	}

    @EventHandler(priority=EventPriority.MONITOR)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
    	if(plugin.contagemIniciada) {
			if((e.getMessage().toLowerCase().startsWith("/bau"))||(e.getMessage().toLowerCase().startsWith("/chest"))) {
	            e.getPlayer().sendMessage("§3§lAlerta PDGH:");
	            e.getPlayer().sendMessage("§cO servidor vai se reiniciar em breve! Você não pode abrir o baú virtual!");
	            e.setCancelled(true);
	            //e.setMessage(null);
			}
    	}else if(plugin.reiniciando) {
            e.getPlayer().sendMessage("§3§lAlerta PDGH:");
            e.getPlayer().sendMessage("§cO servidor vai se reiniciar em breve! Você não pode digitar nenhum comando!");
            e.getPlayer().sendMessage("§cOBS: Caso esteja em combate, você não correrá riscos de morte (ao desconectar automaticamente)");
            e.setCancelled(true);
            //e.setMessage(null);
    	}/**else{
        if (e.getMessage().toLowerCase().startsWith("/horas")) {
  			e.getPlayer().sendMessage("§3§lPDGH Auto Shutdown - Info:");
  			e.getPlayer().sendMessage("§200:00h -§a- Reinicialização do kit VIP.");
  			e.getPlayer().sendMessage("§2§l06:00h -§a§l- Reinicialização do servidor.");
  			e.getPlayer().sendMessage("§208:00h -§a- Reinicialização do kit VIP.");
  			e.getPlayer().sendMessage("§210:00h -§a- Reinicialização do kit VIP.");
  			e.getPlayer().sendMessage("§212:00h -§a- Reinicialização do kit VIP.");
  			e.getPlayer().sendMessage("§214:00h -§a- Reinicialização do kit VIP.");
  			e.getPlayer().sendMessage("§216:00h -§a- Reinicialização do kit VIP.");
  			e.getPlayer().sendMessage("§2§l18:00h -§a§l- Reinicialização do servidor.");
  			e.getPlayer().sendMessage("§220:00h -§a- Reinicialização do kit VIP.");
  			e.getPlayer().sendMessage("§222:00h -§a- Reinicialização do kit VIP.");
  			e.getPlayer().sendMessage("§3Horário atual: §e"+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+"h");
            e.setCancelled(true);
          }
    	}*/
	}
    
    @EventHandler(priority=EventPriority.MONITOR)
    public void onInventoryOpenEvent(InventoryOpenEvent e) {
    	if(plugin.contagemIniciada) {
    		Player p = (Player)e.getPlayer();
    		if(p != null) {
    			//if(e.getInventory().getType()!=InventoryType.PLAYER) {
	    		e.setCancelled(true);
	    		e.getPlayer().closeInventory();
	    		p.sendMessage("§3§lAlerta PDGH:");
	    		p.sendMessage("§cO servidor vai se reiniciar em breve!");// Você consegue abrir apenas seu inventário!
    		}
    	}
    }
}