package me.mchiappinam.pdghautoshutdown;

import java.util.Calendar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Comando implements CommandExecutor, Listener {
	private Main plugin;
	public Comando(Main main) {
		plugin=main;
	}
	
  	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("autos")) {
			if((args.length<1)||(args.length>1)) {
				if(sender.hasPermission("pdgh.admin")) {
					sender.sendMessage("§3§lPDGH Auto Shutdown - Comandos:");
					sender.sendMessage("§2/autos -§a- Info. do plugin.");
					sender.sendMessage("§2/autos fshutdown -§a- Força o desligamento do servidor.");
					sender.sendMessage("§2/autos cshutdown -§a- Inicia contagem de desligamento do servidor (2 min).");
					sender.sendMessage("§2/autos fcancelshutdown -§a- Cancela o desligamento do servidor.");
					sender.sendMessage("§cDeveloped by mchiappinam");
				}else{
					sender.sendMessage("§3§lPDGH Auto Shutdown - Info:");
					if(plugin.contagemIniciada) {
						sender.sendMessage("§2Contagem -§a- §l"+plugin.timer+"§a segundos para reiniciar.");
					}else{
						sender.sendMessage("§2Contagem -§a- O servidor ainda não iniciou a contagem de reinicialização.");
					}
					sender.sendMessage("§cDeveloped by mchiappinam");
				}
				return true;
        	}
			if(args[0].equalsIgnoreCase("fshutdown")) {
				if(sender==plugin.getServer().getConsoleSender()) {
					sender.sendMessage("§3PDGH Auto Shutdown - Iniciando o desligamento forçado...");
					plugin.ctcheck();
					plugin.ctcontagem();
					plugin.restart();
					return true;
				}
				if(!sender.hasPermission("pdgh.admin")) {
					sender.sendMessage("§cSem permissões");
					return true;
				}
				sender.sendMessage("§3§lPDGH Auto Shutdown - Iniciando o desligamento forçado...");
				plugin.ctcheck();
				plugin.ctcontagem();
				plugin.restart();
				return true;
			}else if(args[0].equalsIgnoreCase("cshutdown")) {
				if(sender==plugin.getServer().getConsoleSender()) {
					if(plugin.contagemIniciada) {
						sender.sendMessage("§cA contagem já foi iniciada!");
						return true;
					}
					sender.sendMessage("§3PDGH Auto Shutdown - Iniciando a contagem do desligamento forçcado...");
					plugin.contagem();
					return true;
				}
				if(!sender.hasPermission("pdgh.admin")) {
					sender.sendMessage("§cSem permissões");
					return true;
				}
				if(plugin.contagemIniciada) {
					sender.sendMessage("§cA contagem já foi iniciada!");
					return true;
				}
				sender.sendMessage("§3§lPDGH Auto Shutdown - Iniciando a contagem do desligamento forçado...");
				plugin.contagem();
				return true;
			}else if(args[0].equalsIgnoreCase("fcancelshutdown")) {
				if(sender==plugin.getServer().getConsoleSender()) {
					if(!plugin.contagemIniciada) {
						sender.sendMessage("§cA contagem ainda não foi iniciada!");
						return true;
					}
					if(Calendar.getInstance().get(Calendar.MINUTE)==58) {
						sender.sendMessage("§cAguarde 1 minuto e tente novamente.");
						return true;
					}
					sender.sendMessage("§3PDGH Auto Shutdown - Desligamento cancelado...");
					plugin.ctcheck();
					plugin.ctcontagem();
					plugin.contagemIniciada=false;
					plugin.check();
					return true;
				}
				if(!sender.hasPermission("pdgh.admin")) {
					sender.sendMessage("§cSem permissões");
					return true;
				}
				if(!plugin.contagemIniciada) {
					sender.sendMessage("§cA contagem ainda não foi iniciada!");
					return true;
				}
				if(Calendar.getInstance().get(Calendar.MINUTE)==58) {
					sender.sendMessage("§cAguarde 1 minuto e tente novamente.");
					return true;
				}
				sender.sendMessage("§3§lPDGH Auto Shutdown - Desligamento cancelado...");
				plugin.ctcheck();
				plugin.ctcontagem();
				plugin.check();
				return true;
			}
			if(args.length>=0) {
				if(sender.hasPermission("pdgh.admin")) {
					sender.sendMessage("§3§lPDGH Auto Shutdown - Comandos:");
					sender.sendMessage("§2/autos -§a- Info. do plugin.");
					sender.sendMessage("§2/autos fshutdown -§a- Força o desligamento do servidor.");
					sender.sendMessage("§2/autos cshutdown -§a- Inicia contagem de desligamento do servidor (2 min).");
					sender.sendMessage("§2/autos fcshutdown -§a- Cancela o desligamento do servidor.");
					sender.sendMessage("§cDeveloped by mchiappinam");
				}else{
					sender.sendMessage("§3§lPDGH Auto Shutdown - Info:");
					if(plugin.contagemIniciada) {
						sender.sendMessage("§2Contagem -§a- §l"+plugin.timer+"§a segundos para reiniciar.");
					}else{
						sender.sendMessage("§2Contagem -§a- O servidor ainda não iniciou a contagem de reinicialização.");
					}
					sender.sendMessage("§cDeveloped by mchiappinam");
				}
				return true;
        	}
        }else if(cmd.getName().equalsIgnoreCase("tempo")) {
        	if(args.length >0) {
				sender.sendMessage("§cUse /tempo");
				return true;
        	}
			sender.sendMessage("§3§lPDGH Auto Shutdown - Info:");
			sender.sendMessage("§200:00h -§a- Reinicialização do kit VIP.");
			sender.sendMessage("§2§l06:00h -§a§l- Reinicialização do servidor.");
			sender.sendMessage("§208:00h -§a- Reinicialização do kit VIP.");
			sender.sendMessage("§210:00h -§a- Reinicialização do kit VIP.");
			sender.sendMessage("§212:00h -§a- Reinicialização do kit VIP.");
			sender.sendMessage("§214:00h -§a- Reinicialização do kit VIP.");
			sender.sendMessage("§216:00h -§a- Reinicialização do kit VIP.");
			sender.sendMessage("§2§l18:00h -§a§l- Reinicialização do servidor.");
			sender.sendMessage("§220:00h -§a- Reinicialização do kit VIP.");
			sender.sendMessage("§222:00h -§a- Reinicialização do kit VIP.");
			sender.sendMessage("§3Horário atual: §e"+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+"h");
			return true;
        }
		return false;
    }
	
  	
  	
  	
  	
  	
}