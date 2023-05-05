package de.oliver.fancylib.gui.customInventories;

import de.oliver.fancylib.FancyLib;
import org.bukkit.NamespacedKey;

public interface PageInventory {

    void loadPage(int page);

    NamespacedKey PAGE_KEY = new NamespacedKey(FancyLib.getPlugin(), "page");

}
