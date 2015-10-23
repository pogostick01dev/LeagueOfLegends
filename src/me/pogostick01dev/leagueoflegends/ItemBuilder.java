package me.pogostick01dev.leagueoflegends;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

	private ItemStack item;
	
	public ItemBuilder(Material mat) {
		this(mat, 1);
	}
	
	public ItemBuilder(ItemStack is) {
		this.item = is;
	}
	
	public ItemBuilder(Material mat, int amount) {
		item = new ItemStack(mat, amount);
	}
	
	public ItemBuilder clone() {
		return new ItemBuilder(item);
	}
	
	public ItemBuilder setDurability(short dur) {
		item.setDurability(dur);
		return this;
	}
	
	public ItemBuilder setName(String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
		item.addUnsafeEnchantment(ench, level);
		return this;
	}
	
	public ItemBuilder removeEnchantment(Enchantment ench) {
		item.removeEnchantment(ench);
		return this;
	}
	
	public ItemBuilder setSkullOwner(String owner) {
		try {
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			meta.setOwner(owner);
			item.setItemMeta(meta);
		} catch (ClassCastException e) {}
		return this;
	}
	
	public ItemBuilder addEnchant(Enchantment ench, int level) {
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(ench, level, true);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder addEnchants(SimpleEntry<Enchantment, Integer>... enchs) {
		ItemMeta meta = item.getItemMeta();
		for (SimpleEntry<Enchantment, Integer> entry : enchs) {
			meta.addEnchant(entry.getKey(), entry.getValue(), true);
		}
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setInfinityDurability() {
		item.setDurability(Short.MAX_VALUE);
		return this;
	}
	
	public ItemBuilder setLore(String... lore) {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return this;
	}
	
	@SuppressWarnings("deprecation")
	public ItemBuilder setWoolColor(DyeColor color) {
		if (!item.getType().equals(Material.WOOL)) {
			return this;
		}
		this.item.setDurability(color.getData());
		return this;
	}
	
	public ItemBuilder setLeatherArmorColor(Color color) {
		try {
			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(color);
			item.setItemMeta(meta);
		} catch (ClassCastException e) {}
		return this;
	}
	
	public ItemStack toItemStack() {
		return item;
	}
}
