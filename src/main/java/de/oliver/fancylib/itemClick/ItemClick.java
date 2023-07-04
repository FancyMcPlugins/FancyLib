package de.oliver.fancylib.itemClick;

import de.oliver.fancylib.FancyLib;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public interface ItemClick {
    NamespacedKey ON_CLICK_KEY = new NamespacedKey(FancyLib.getPlugin(), "oninteract");

    ItemClick EMPTY = new ItemClick() {
        @Override
        public String getId() {
            return null;
        }

        @Override
        public void onClick(PlayerInteractEvent event, Player player) {
        }
    };

    String getId();

    void onClick(PlayerInteractEvent event, Player player);

    default void register() {
        ItemClickRegistry.registerItemClick(this);
    }

}
