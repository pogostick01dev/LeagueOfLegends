package me.pogostick01dev.summonersrift;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pogostick01dev.summonersrift.cmds.CreateArena;
import me.pogostick01dev.summonersrift.cmds.RemoveArena;

public class CommandManager implements CommandExecutor {

	private ArrayList<GameCommand> cmds;
	
	protected CommandManager() {
		cmds = new ArrayList<>();
		
		// Add all commands.
		cmds.add(new CreateArena());
		cmds.add(new RemoveArena());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use SummonersRift.");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("summonersrift")) {
			if (args.length == 0) {
				cmds.stream().forEach((gcmd) -> {
					CommandInfo info = gcmd.getClass().getAnnotation(CommandInfo.class);
					p.sendMessage(ChatColor.GOLD + "/summonersrift (" + StringUtils.join(info.aliases(), " ").trim() + ") " + info.usage() + " - " + info.description());
				
				});
				
				return true;
			}
			
			GameCommand wanted = null;
			
			for (GameCommand gcmd : cmds) {
				CommandInfo info = gcmd.getClass().getAnnotation(CommandInfo.class);
				for (String alias : info.aliases()) {
					if (alias.equals(args[0])) {
						wanted = gcmd;
						break;
					}
				}
			}
			
			if (wanted == null) {
				p.sendMessage(ChatColor.RED + "Could not find command.");
				return true;
			}
			
			ArrayList<String> newArgs = new ArrayList<String>();
			Collections.addAll(newArgs, args);
			newArgs.remove(0);
			args = newArgs.toArray(new String[newArgs.size()]);
			
			wanted.onCommand(p, args);
		}
		
		return true;
	}
}
