package de.oliver.fancylib.itemClick;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        try{
            ItemStack item = event.getItem();
            if(event.getHand() == EquipmentSlot.HAND && item.getItemMeta().getPersistentDataContainer().has(ItemClick.ON_CLICK_KEY)){
                String id = item.getItemMeta().getPersistentDataContainer().get(ItemClick.ON_CLICK_KEY, PersistentDataType.STRING);

                ItemClickRegistry.getItemClick(id).onClick(event, event.getPlayer());
            }
        } catch (NullPointerException ignore){ }
    }

}
