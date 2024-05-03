package main.java.com.pattern.builder;

import java.math.BigDecimal;

public class StoreItem{
    private final String name;
    private final String shortDescription;
    private final String longDescription;
    private final BigDecimal price;
    private final Integer stockAvailable;
    private final String packagingType;

    private StoreItem(StoreItemBuilder builder) {
        this.name = builder.name;
        this.shortDescription = builder.shortDescription;
        this.longDescription = builder.longDescription;
        this.price = builder.price;
        this.stockAvailable = builder.stockAvailable;
        this.packagingType = builder.packagingType;

        if (name==null && price == null) {
            throw new IllegalArgumentException("Name and price must not be null");
        }
    }

    @Override
    public String toString(){
        return "{ StoreItem: name: "+ name +
        " shortDescription:"+ shortDescription +
        " longDescription:"+ longDescription +
        " price:"+ price +
        " stockAvailable:"+ stockAvailable +
        " packagingType:"+ packagingType +
        " }";
    }

    public static class StoreItemBuilder{
        private final String name;
        private String shortDescription;
        private String longDescription;
        private final BigDecimal price;
        private Integer stockAvailable;
        private String packagingType;

        public StoreItemBuilder(String name, BigDecimal price){
            this.name = name;
            this.price = price;  

            if (name==null && price == null) {
                throw new IllegalArgumentException("Name and price must not be null");
            }  
        }

        public StoreItemBuilder shortDescription(String shortDescription){
            this.shortDescription = shortDescription;
            return this;
        }

        public StoreItemBuilder longDescription(String longDescription){
            this.longDescription = longDescription;
            return this;
        }

        public StoreItemBuilder stockAvailable(Integer stockAvailable){
            this.stockAvailable = stockAvailable;
            return this;
        }

        public StoreItemBuilder packagingType(String packagingType){
            this.packagingType = packagingType;
            return this;
        }

        public StoreItem build(){
            return new StoreItem(this);
        }
    }
}