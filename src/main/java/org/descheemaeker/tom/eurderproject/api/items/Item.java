package org.descheemaeker.tom.eurderproject.api.items;

import java.util.UUID;

public class Item {
    private String itemId;
    private String name;
    private String description;
    private double price;
    private int amount;

    public Item(ItemBuilder itemBuilder) {
        this.itemId = UUID.randomUUID().toString();
        this.name = itemBuilder.name;
        this.description = itemBuilder.description;
        this.price = itemBuilder.price;
        this.amount = itemBuilder.amount;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public static final class ItemBuilder {
        private String itemId;
        private String name;
        private String description;
        private double price;
        private int amount;

        private ItemBuilder() {
        }

        public static ItemBuilder anItem() {
            return new ItemBuilder();
        }

        public ItemBuilder withItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public ItemBuilder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
