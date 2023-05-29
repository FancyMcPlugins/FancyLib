package de.oliver.fancylib.itemClick;

import java.util.HashMap;
import java.util.Map;

public class ItemClickRegistry {

    private static final Map<String, ItemClick> itemClickMap = new HashMap<>();

    public static ItemClick getItemClick(String id){
        return itemClickMap.getOrDefault(id, ItemClick.EMPTY);
    }

    public static void registerItemClick(ItemClick itemClick){
        itemClickMap.put(itemClick.getId(), itemClick);
    }

}
