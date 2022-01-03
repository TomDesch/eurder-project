package org.descheemaeker.tom.eurderproject.api.items.dto;

public record CreateItemDto(String name, String description, double price, int amount) {
}
