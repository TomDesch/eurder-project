package org.descheemaeker.tom.eurderproject.api.items.dto;

import java.util.Objects;

public record ItemDto(String itemId, String name, String description, double price, int amount) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDto itemDto)) return false;
        return Double.compare(itemDto.price, price) == 0 && amount == itemDto.amount && Objects.equals(itemId, itemDto.itemId) && Objects.equals(name, itemDto.name) && Objects.equals(description, itemDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, description, price, amount);
    }
}
