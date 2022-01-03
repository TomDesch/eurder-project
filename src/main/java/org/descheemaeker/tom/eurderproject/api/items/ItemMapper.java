package org.descheemaeker.tom.eurderproject.api.items;

import org.descheemaeker.tom.eurderproject.api.items.dto.CreateItemDto;
import org.descheemaeker.tom.eurderproject.api.items.dto.ItemDto;
import org.descheemaeker.tom.eurderproject.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    @Autowired
    public ItemMapper() {
    }

    public ItemDto itemToDto(Item item) {
        return new ItemDto(
                item.getItemId()
                , item.getName()
                , item.getDescription()
                , item.getPrice()
                , item.getAmount());
    }

    public Item dtoToItem(CreateItemDto createItemDto) {
        return Item.ItemBuilder.anItem()
                .withName(createItemDto.name())
                .withDescription(createItemDto.description())
                .withPrice(createItemDto.price())
                .withAmount(createItemDto.amount())
                .build();
    }
}