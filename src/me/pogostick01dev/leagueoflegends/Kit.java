package me.pogostick01dev.leagueoflegends;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a kit.
 */
public abstract class Kit {

	private String name;
	private ArrayList<ItemStack> items;

	public Kit(String name) {
		this.name = name;
		this.items = new ArrayList<>();
	}

	public final String getName() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public final ArrayList<ItemStack> getItems() {
		return (ArrayList<ItemStack>) items.clone();
	}

	public void addItem(Material material, int amount, String name, String[] lore,
			SimpleEntry<Enchantment, Integer>... enchs) {
		ItemStack item = new ItemBuilder(material, amount).setName(name).setLore(lore).addEnchants(enchs).toItemStack();
		items.add(item);
	}

	/**
	 * Can be overridden to change the player to whom the kit is being applied.
	 * @param p The player.
	 */
	public void apply(Player p) {

	}
}
