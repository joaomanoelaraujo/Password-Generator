package dev.slickcollections.kiwizin.skywars.menus.profile;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.libraries.menu.PlayerMenu;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import dev.slickcollections.kiwizin.utils.StringUtils;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MenuProfile extends PlayerMenu {
  
  private static final SimpleDateFormat SDF = new SimpleDateFormat("d 'de' MMMM 'de' yyyy 'às' HH:mm",
      Locale.forLanguageTag("pt-BR"));
  
  private final Profile target;
  
  public MenuProfile(Profile profile, Profile target) {
    super(profile.getPlayer(), target.getName(), 3);
    this.target = target;
    
    this.setItem(11, BukkitUtils.putProfileOnSkull(target.getPlayer(), BukkitUtils.deserializeItemStack(
        "SKULL_ITEM:3 : 1 : nome>&a" + target.getName() + " : desc>&fRank: " + Role.getRoleByName(target.getDataContainer("kCoreProfile", "role").getAsString())
            .getName() + "\n&fCash: &b" + StringUtils.formatNumber(target.getStats("kCoreProfile", "cash")) + "\n \n&fCadastrado em: &7" + SDF.format(target.getDataContainer("kCoreProfile", "created").getAsLong()) + "\n&fÚltimo acesso: &7" + SDF
            .format(target.getDataContainer("kCoreProfile", "lastlogin").getAsLong()))));
    
    this.setItem(13, BukkitUtils.deserializeItemStack(
        "PAPER : 1 : nome>&aEstatísticas : desc>&eClique para ver as estatísticas!"));
  
    this.setItem(15, BukkitUtils.deserializeItemStack(
        "SKULL_ITEM:3 : 1 : nome>&aRedes Sociais : desc>&eClique para ver as redes sociais! : skin>eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzY4NWEwYmU3NDNlOTA2N2RlOTVjZDhjNmQxYmEyMWFiMjFkMzczNzFiM2Q1OTcyMTFiYjc1ZTQzMjc5In19fQ=="));
  
  
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
            if (evt.getSlot() == 11) {
              EnumSound.ITEM_PICKUP.play(this.player, 0.5F, 2.0F);
            } else if (evt.getSlot() == 13) {
              EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
              new MenuStatistics(profile, this.target);
            } else if (evt.getSlot() == 15) {
              EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
              new MenuSocialNetworks(profile, this.target);
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