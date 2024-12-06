package me.mchiappinam.pdghautoshutdown;

import java.util.Calendar;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Main extends JavaPlugin implements Listener {
	public boolean reiniciando=false;
	public boolean contagemIniciada=false;
	public int timer=120;
	static int tcontagem;
	static int tcheck;
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHAutoShutdown] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHAutoShutdown] §2Acesse: http://pdgh.com.br/");
	}
	
	@Override
    public void onEnable() {
	    getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		getServer().getPluginCommand("autos").setExecutor(new Comando(this));
		getServer().getPluginCommand("tempo").setExecutor(new Comando(this));
		check();
		getServer().setWhitelist(false);
		getServer().getConsoleSender().sendMessage("§3[PDGHAutoShutdown] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHAutoShutdown] §2Acesse: http://pdgh.com.br/");
	}
	
    public void check() {
		tcheck=getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	  		public void run() {
				if((Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==05)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==07)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==9)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==11)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==13)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==15)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==17)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==19)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==21)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==23))
					if(Calendar.getInstance().get(Calendar.MINUTE)==58) {
				    	if((Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==05) || (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==17)) {
				    		contagem();
				    	}else{
				    		contagemm();
				    	}
						ctcheck();
					}
	  		}
	  	}, 0, 5*20);
	}
	
    public void checkInvOpen() {
		tcheck=getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	  		public void run() {
	  			if(contagemIniciada)
		  			for(Player p : getServer().getOnlinePlayers())
		  				if(p.getOpenInventory() instanceof PlayerInventory) {
		  					p.closeInventory();
		  		    		p.sendMessage("§3§lAlerta PDGH:");
		  		    		p.sendMessage("§cO servidor vai se reiniciar em breve!");
		  				}
	  		}
	  	}, 0, 1);
	}
	
    public void contagemm() {
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
		        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin reload PDGHKit");
		        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin reload PDGHBauVIP");
		        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin reload PDGHAirDrop");
		        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin reload PDGHBolao");
				getServer().broadcastMessage("§6§l[PDGHAutoShutdown] §eKit reiniciado! VIPs conseguem pegar outro kit!");
				getServer().broadcastMessage("§6§l[PDGHAutoShutdown] §eDigite §f/tempo §epara mais informações.");
				check();
			}
		}, 120*20L);
    }
	
    public void contagem() {
    	timer=120;
    	contagemIniciada=true;
    	for(Player todos : getServer().getOnlinePlayers())
    		todos.closeInventory();
		tcontagem=getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	  		public void run() {
	  			if(timer==120) {
					getServer().broadcastMessage(" ");
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6REINICIANDO O SERVIDOR EM §c§l2 §6MINUTOS!");
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6Veja o tempo restante - /autos");
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6REINICIANDO O SERVIDOR EM §c§l2 §6MINUTOS!");
					getServer().broadcastMessage(" ");
	  			}else if(timer==60) {
					getServer().broadcastMessage(" ");
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6REINICIANDO O SERVIDOR EM §c§l1 §6MINUTO!");
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6Veja o tempo restante - /autos");
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6REINICIANDO O SERVIDOR EM §c§l1 §6MINUTO!");
					getServer().broadcastMessage(" ");
	  			}else if(timer==3) {
	  				reiniciando=true;
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6REINICIANDO O SERVIDOR EM §c§l3 §6SEGUNDOS!");
	  			}else if(timer==2)
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6REINICIANDO O SERVIDOR EM §c§l2 §6SEGUNDOS!");
	  			else if(timer==1)
					getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6REINICIANDO O SERVIDOR EM §c§l1 §6SEGUNDO!");
	  			else if(timer==0) {
					restart();
					ctcontagem();
	  			}
	  			timer--;
	  		}
	  	}, 0, 20);
	}

	public void ctcontagem() {
		getServer().getScheduler().cancelTask(tcontagem);
	}

	public void ctcheck() {
		getServer().getScheduler().cancelTask(tcheck);
	}
	
	public void restart() {
		getServer().setWhitelist(true);
        getServer().dispatchCommand(getServer().getConsoleSender(), "save-all");
        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin unload SimpleNoRelog");
        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin unload PDGHX1");
        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin unload PDGHX1C");
        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin unload PDGHCreativoArenas");
        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin unload PDGHFullPvPArenas");
        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin unload PDGHEventos");
        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin unload PDGHClanFronto");
        getServer().dispatchCommand(getServer().getConsoleSender(), "plugin unload PDGHDeathmatch");
		getServer().broadcastMessage(" ");
		getServer().broadcastMessage("§c[ⒶⓋⒾⓈⓄ] §2»» §6REINICIANDO O SERVIDOR §c§lAGORA§6!");
		getServer().broadcastMessage(" ");
		connectLobby();
	}
	
	public void connectLobby() {
		try {
			for (Player player : getServer().getOnlinePlayers()) {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF("lobby");
				player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
			}
		timeKick();
		}catch(Exception e) {
			timeKick();
		}
	}
	
	public void timeKick() {
		try {
	    getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
		      public void run() {
		  		for (Player p : getServer().getOnlinePlayers()) {
				    p.kickPlayer("§3§lCalma! Não se suicide! Não pegue a faca!\n§3§l--§6§lREINICIANDO O SERVIDOR§3§l--\n\n' '\nO");
		        }
		        //getServer().dispatchCommand(getServer().getConsoleSender(), "virtualpack ad save");
		        getServer().dispatchCommand(getServer().getConsoleSender(), "save-all");
				getServer().shutdown();
		  		}
		    }, 100L);
		}catch(Exception e) {}
	}

	//public void sendMessage(String name, String string) {
	//	getServer().getPlayerExact(name).sendMessage(string);
	//}
	
	
	
	
	
	
	
	
	
	
	
	
}