package dev.slickcollections.kiwizin.skywars.menus.profile;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.database.data.DataContainer;
import dev.slickcollections.kiwizin.libraries.menu.PlayerMenu;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class MenuSocialNetworks extends PlayerMenu {
  
  private final Profile target;
  
  public MenuSocialNetworks(Profile profile, Profile target) {
    super(profile.getPlayer(), "Redes Sociais", 4);
    this.target = target;
    
    DataContainer data = target.getDataContainer("kCoreProfile", "twitter");
    String name = data == null ? "" : data.getAsString();
    this.setItem(10, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aTwitter : desc>&fAtual: &b"
        + (name.isEmpty() ? "Nenhum" : name) + " : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzY4NWEwYmU3NDNlOTA2N2RlOTVjZDhjNmQxYmEyMWFiMjFkMzczNzFiM2Q1OTcyMTFiYjc1ZTQzMjc5In19fQ=="));
    
    data = target.getDataContainer("kCoreProfile", "youtube");
    name = data == null ? "" : data.getAsString();
    this.setItem(11, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aYouTube : desc>&fAtual: &c"
        + (name.isEmpty() ? "Nenhum" : name) + " : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzNTNmZDBmODYzMTQzNTM4NzY1ODYwNzViOWJkZjBjNDg0YWFiMDMzMWI4NzJkZjExYmQ1NjRmY2IwMjllZCJ9fX0="));
    
    data = target.getDataContainer("kCoreProfile", "instagram");
    name = data == null ? "" : data.getAsString();
    this.setItem(13, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aInstagram : desc>&fAtual: &7"
        + (name.isEmpty() ? "Nenhum" : name) + " : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjViM2YyY2ZhMDczOWM0ZTgyODMxNmYzOWY5MGIwNWJjMWY0ZWQyN2IxZTM1ODg4NTExZjU1OGQ0Njc1In19fQ=="));
    
    data = target.getDataContainer("kCoreProfile", "discord");
    name = data == null ? "" : data.getAsString();
    this.setItem(15, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aDiscord : desc>&fAtual: &9"
        + (name.isEmpty() ? "Nenhum" : name) + " : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg3M2MxMmJmZmI1MjUxYTBiODhkNWFlNzVjNzI0N2NiMzlhNzVmZjFhODFjYmU0YzhhMzliMzExZGRlZGEifX19"));
    
    data = target.getDataContainer("kCoreProfile", "twitch");
    name = data == null ? "" : data.getAsString();
    this.setItem(16, BukkitUtils.deserializeItemStack("SKULL_ITEM:3 : 1 : nome>&aTwitch : desc>&fAtual: &5"
        + (name.isEmpty() ? "Nenhum" : name) + " : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDZiZTY1ZjQ0Y2QyMTAxNGM4Y2RkZDAxNThiZjc1MjI3YWRjYjFmZDE3OWY0YzFhY2QxNThjODg4NzFhMTNmIn19fQ=="));
    
    this.setItem(31, BukkitUtils.deserializeItemStack("INK_SACK:1 : 1 : nome>&cVoltar"));
    
    this.register(Core.getInstance());
    this.open();
  }
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent evt) {
    if (evt.getInventory().equals(this.getInventory())) {
      evt.setCancelled(true);
      
      if (evt.getWhoClicked().equals(this.player)) {
        Profile profile = Profile.getProfile(this.player.getName());
        if (profile == null) {
          this.player.closeInventory();
          return;
        }
        
        if (evt.getClickedInventory() != null && evt.getClickedInventory().equals(this.getInventory())) {
          ItemStack item = evt.getCurrentItem();
          
          if (item != null && item.getType() != Material.AIR) {
            if (evt.getSlot() == 10 || evt.getSlot() == 11 || evt.getSlot() == 13 || evt.getSlot() == 15 || evt.getSlot() == 16) {
              EnumSound.ITEM_PICKUP.play(this.player, 0.5f, 2.0f);
            } else if (evt.getSlot() == 31) {
              EnumSound.CLICK.play(this.player, 0.5f, 2.0f);
              new MenuProfile(profile, this.target);
            }
          }
        }
      }
    }
  }
  
  public void cancel() {
    HandlerList.unregisterAll(this);
  }
  
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent evt) {
    if (evt.getPlayer().equals(this.player)) {
      this.cancel();
    }
  }
  
  @EventHandler
  public void onInventoryClose(InventoryCloseEvent evt) {
    if (evt.getPlayer().equals(this.player) && evt.getInventory().equals(this.getInventory())) {
      this.cancel();
    }
  }
}