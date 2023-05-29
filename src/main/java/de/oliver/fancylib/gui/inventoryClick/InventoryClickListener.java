package de.oliver.fancylib.gui.inventoryClick;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        try{
            ItemStack item = event.getCurrentItem();

            if(item.getItemMeta().getPersistentDataContainer().has(InventoryItemClick.ON_CLICK_KEY)){
                String id = item.getItemMeta().getPersistentDataContainer().get(InventoryItemClick.ON_CLICK_KEY, PersistentDataType.STRING);
                InventoryClickRegistry.getInventoryItemClick(id).onClick(event, (Player) event.getWhoClicked());
            }

        }catch (NullPointerException ignore){ }

    }

}
