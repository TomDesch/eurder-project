package org.descheemaeker.tom.eurderproject.services;

import org.descheemaeker.tom.eurderproject.api.items.ItemMapper;
import org.descheemaeker.tom.eurderproject.domain.Features;
import org.descheemaeker.tom.eurderproject.domain.Item;
import org.descheemaeker.tom.eurderproject.api.items.dto.CreateItemDto;
import org.descheemaeker.tom.eurderproject.api.items.dto.ItemDto;
import org.descheemaeker.tom.eurderproject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.descheemaeker.tom.eurderproject.api.items.ItemMapper.ITEM_MAPPER;
import static org.descheemaeker.tom.eurderproject.domain.Features.ADD_NEW_ITEM;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final SecurityService securityService;

    @Autowired
    public ItemService(ItemRepository itemRepository, SecurityService securityService) {
        this.itemRepository = itemRepository;
        this.securityService = securityService;
    }

    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    public ItemDto addItem(Item item, String authentication) {
        securityService.validate(authentication, ADD_NEW_ITEM);

        itemRepository.addItem(item);
        return ITEM_MAPPER.itemToDto(item);
    }

    public ItemDto addItem(CreateItemDto createItemDto, String authentication) {
        return addItem(ITEM_MAPPER.dtoToItem(createItemDto), authentication);
    }
}
