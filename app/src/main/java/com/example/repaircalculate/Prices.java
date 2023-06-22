package com.example.repaircalculate;

public class Prices {
    // Цены за квадратный метр
    private static double FLAT_COSMETIC_PRICE = 2500;
    private static double FLAT_CAPITAL_PRICE = 4000;
    private static double HOUSE_COSMETIC_PRICE = 3500;
    private static double HOUSE_CAPITAL_PRICE = 5000;

    // Цены за комнату
    private static double FLAT_ROOM_PRICE = 10000;
    private static double HOUSE_ROOM_PRICE = 15000;

    public static double getFlatCosmeticPrice() {
        return FLAT_COSMETIC_PRICE;
    }

    public static void setFlatCosmeticPrice(double price) {
        FLAT_COSMETIC_PRICE = price;
    }

    public static double getFlatCapitalPrice() {
        return FLAT_CAPITAL_PRICE;
    }

    public static void setFlatCapitalPrice(double price) {
        FLAT_CAPITAL_PRICE = price;
    }

    public static double getHouseCosmeticPrice() {
        return HOUSE_COSMETIC_PRICE;
    }

    public static void setHouseCosmeticPrice(double price) {
        HOUSE_COSMETIC_PRICE = price;
    }

    public static double getHouseCapitalPrice() {
        return HOUSE_CAPITAL_PRICE;
    }

    public static void setHouseCapitalPrice(double price) {
        HOUSE_CAPITAL_PRICE = price;
    }

    public static double getFlatRoomPrice() {
        return FLAT_ROOM_PRICE;
    }

    public static void setFlatRoomPrice(double price) {
        FLAT_ROOM_PRICE = price;
    }

    public static double getHouseRoomPrice() {
        return HOUSE_ROOM_PRICE;
    }

    public static void setHouseRoomPrice(double price) {
        HOUSE_ROOM_PRICE = price;
    }
}