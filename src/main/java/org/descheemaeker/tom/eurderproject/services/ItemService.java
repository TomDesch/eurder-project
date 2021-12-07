package org.descheemaeker.tom.eurderproject.services;

import org.descheemaeker.tom.eurderproject.domain.Item;
import org.descheemaeker.tom.eurderproject.api.items.dto.CreateItemDto;
import org.descheemaeker.tom.eurderproject.api.items.dto.ItemDto;
import org.descheemaeker.tom.eurderproject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.descheemaeker.tom.eurderproject.api.items.ItemMapper.ITEM_MAPPER;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    public ItemDto addItem(Item item) {
        itemRepository.addItem(item);
        return ITEM_MAPPER.itemToDto(item);
    }

    public ItemDto addItem(CreateItemDto createItemDto) {
        return addItem(ITEM_MAPPER.dtoToItem(createItemDto));
    }
}
