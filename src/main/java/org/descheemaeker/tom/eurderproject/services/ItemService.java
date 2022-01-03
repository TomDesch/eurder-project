package org.descheemaeker.tom.eurderproject.services;

import org.descheemaeker.tom.eurderproject.api.items.ItemMapper;
import org.descheemaeker.tom.eurderproject.api.items.dto.CreateItemDto;
import org.descheemaeker.tom.eurderproject.api.items.dto.ItemDto;
import org.descheemaeker.tom.eurderproject.domain.Item;
import org.descheemaeker.tom.eurderproject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.descheemaeker.tom.eurderproject.domain.Features.ADD_NEW_ITEM;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final SecurityService securityService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, SecurityService securityService, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.securityService = securityService;
        this.itemMapper = itemMapper;
    }


    public List<ItemDto> getAllItems() {
        return itemRepository.getAllItems().stream()
                .map(itemMapper::itemToDto)
                .collect(Collectors.toList());
    }

    public ItemDto addItem(Item item, String authentication) {
        securityService.validate(authentication, ADD_NEW_ITEM);

        itemRepository.addItem(item);
        return itemMapper.itemToDto(item);
    }

    public ItemDto addItem(CreateItemDto createItemDto, String authentication) {
        return addItem(itemMapper.dtoToItem(createItemDto), authentication);
    }
}
