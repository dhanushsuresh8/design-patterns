package main.java.com.pattern.builder;

import java.math.BigDecimal;

public class ItemRegistry {
    public static void main(String[] args) {
        StoreItem item1 = new StoreItem.StoreItemBuilder("Maggie", new BigDecimal(12)).stockAvailable(Integer.valueOf(30)).build();
        System.out.println(item1.toString());
    }
}
