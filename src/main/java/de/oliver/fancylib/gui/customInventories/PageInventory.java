package de.oliver.fancylib.gui.customInventories;

import de.oliver.fancylib.FancyLib;
import de.oliver.fancylib.MessageHelper;
import de.oliver.fancylib.gui.inventoryClick.InventoryItemClick;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public interface PageInventory {

    NamespacedKey PAGE_KEY = new NamespacedKey(FancyLib.getPlugin(), "page");

    static ItemStack previousPage(int currentPage){
        ItemStack previousPage = new ItemStack(Material.ARROW);
        previousPage.editMeta(itemMeta -> {
            itemMeta.displayName(MessageHelper.removeDecoration(MiniMessage.miniMessage().deserialize("<gradient:gold:yellow:gold>Previous page</gradient>"), TextDecoration.ITALIC));

            itemMeta.getPersistentDataContainer().set(PageInventory.PAGE_KEY, PersistentDataType.INTEGER, currentPage-1);
            itemMeta.getPersistentDataContainer().set(InventoryItemClick.ON_CLICK_KEY, PersistentDataType.STRING, "changePage");
        });

        return previousPage;
    }

    static ItemStack nextPage(int currentPage){
        ItemStack nextPage = new ItemStack(Material.ARROW);
        nextPage.editMeta(itemMeta -> {
            itemMeta.displayName(MessageHelper.removeDecoration(MiniMessage.miniMessage().deserialize("<gradient:gold:yellow:gold>Next page</gradient>"), TextDecoration.ITALIC));

            itemMeta.getPersistentDataContainer().set(PageInventory.PAGE_KEY, PersistentDataType.INTEGER, currentPage+1);
            itemMeta.getPersistentDataContainer().set(InventoryItemClick.ON_CLICK_KEY, PersistentDataType.STRING, "changePage");
        });

        return nextPage;
    }

    void loadPage(int page);

}
