package me.pogostick01dev.leagueoflegends.cmds;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import me.pogostick01dev.leagueoflegends.Arena;
import me.pogostick01dev.leagueoflegends.ArenaManager;
import me.pogostick01dev.leagueoflegends.CommandInfo;
import me.pogostick01dev.leagueoflegends.GameCommand;
import me.pogostick01dev.leagueoflegends.LobbySign;
import me.pogostick01dev.leagueoflegends.SettingsManager;

@CommandInfo(description = "Add a lobby sign.", usage = "<arenaName>", aliases = { "addlobbysign", "addsign", "als" })
public class AddLobbySign extends GameCommand {

	@Override
	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			p.sendMessage(ChatColor.RED + "You must specify the arena to whichyou want to bind this sign..");
			return;
		}

		Arena arena = ArenaManager.getInstance().getArena(args[0]);

		if (arena == null) {
			p.sendMessage(ChatColor.RED + "An arena with that name does not exist.");
			return;
		}

		Block target = p.getTargetBlock((Set<Material>) null, 10);

		if (target == null) {
			p.sendMessage(ChatColor.RED + "You are not looking at a block.");
			return;
		}

		if (!(target.getState() instanceof Sign)) {
			p.sendMessage(ChatColor.RED + "You are not looking at a sign.");
			return;
		}

		SettingsManager.getSigns().set(String.valueOf(SettingsManager.getArenas().getKeys().size()),
				new LobbySign(target.getLocation(), arena));
	
		// TODO: New sign would not be in SignManager.
	}
}
