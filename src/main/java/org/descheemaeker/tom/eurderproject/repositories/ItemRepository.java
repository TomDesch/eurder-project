package org.descheemaeker.tom.eurderproject.repositories;

import org.descheemaeker.tom.eurderproject.api.items.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {
    private final Map<String, Item> items = new ConcurrentHashMap<>();

    public Item addItem(Item item) {
        items.put(item.getItemId(), item);
        return item;
    }

    public Item getItem(String itemId) {
        return items.get(itemId);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }


}
