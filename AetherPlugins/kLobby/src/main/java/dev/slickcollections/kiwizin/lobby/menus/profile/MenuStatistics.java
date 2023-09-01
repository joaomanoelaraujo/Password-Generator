package dev.slickcollections.kiwizin.skywars.menus.profile;

import dev.slickcollections.kiwizin.Core;
import dev.slickcollections.kiwizin.libraries.menu.PlayerMenu;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.utils.BukkitUtils;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class MenuStatistics extends PlayerMenu {
  
  private final Profile target;
  
  public MenuStatistics(Profile profile, Profile target) {
    super(profile.getPlayer(), "Estatísticas", 4);
    this.target = target;
    
    this.setItem(12, BukkitUtils.deserializeItemStack(PlaceholderAPI.setPlaceholders(target.getPlayer(),
        "GRASS : 1 : nome>&aSky Wars : desc>&eSolo:\n&8➟ &fAbates: &7%kCore_SkyWars_1v1kills%\n&8➟ &fMortes: &7%kCore_SkyWars_1v1deaths%\n&8➟ &fVitórias: &7%kCore_SkyWars_1v1wins%\n&8➟ &fPartidas: &7%kCore_SkyWars_1v1games%\n&8➟ &fAssistências: &7%kCore_SkyWars_1v1assists%\n " + /*"\n&eDupla:\n &8▪ &fAbates: &7%kCore_SkyWars_2v2kills%\n &8▪ &fMortes: &7%kCore_SkyWars_2v2deaths%\n &8▪ &fVitórias: &7%kCore_SkyWars_2v2wins%\n &8▪ &fPartidas: &7%kCore_SkyWars_2v2games%\n &8▪ &fAssistências: &7%kCore_SkyWars_2v2assists%\n*/ "\n&eRanked:\n&8➟ &fAbates: &7%kCore_SkyWars_rankedkills%\n&8➟ &fMortes: &7%kCore_SkyWars_rankeddeaths%\n&8➟ &fVitórias: &7%kCore_SkyWars_rankedwins%\n&8➟ &fPartidas: &7%kCore_SkyWars_rankedgames%\n&8➟ &fPontos: &7%kCore_SkyWars_rankedpoints%\n \n&fCoins: &6%kCore_SkyWars_coins%")));
    
    this.setItem(14, BukkitUtils.deserializeItemStack(PlaceholderAPI.setPlaceholders(target.getPlayer(),
        "BED : 1 : nome>&aBed Wars : desc>&eGeral:\n&8➟ &fPartidas: &7%kCore_BedWars_games%\n&8➟ &fAbates: &7%kCore_BedWars_kills%\n&8➟ &fMortes: &7%kCore_BedWars_deaths%\n&8➟ &fAbates Finais: &7%kCore_BedWars_finalkills%\n&8➟ &fMortes Finais: &7%kCore_BedWars_finaldeaths%\n&8➟ &fVitórias: &7%kCore_BedWars_wins%\n&8➟ &fCamas destruídas: &7%kCore_BedWars_bedsdestroyeds%\n&8➟ &fCamas perdidas: &7%kCore_BedWars_bedslosteds%\n \n&fCoins: &6%kCore_BedWars_coins%")));
    
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
            if (evt.getSlot() == 12 || evt.getSlot() == 14) {
              EnumSound.ITEM_PICKUP.play(this.player, 0.5F, 2.0F);
            } else if (evt.getSlot() == 31) {
              EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
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