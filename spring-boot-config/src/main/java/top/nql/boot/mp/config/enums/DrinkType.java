package top.nql.boot.mp.config.enums;

public enum DrinkType {
    COFFEE("咖啡",20),
    TEA("奶茶",18),
    JUICE("果汁",30);
    private final String label;
    private final int price;
    DrinkType(String label, int price) {
        this.label = label;
        this.price = price;
    }
    public String getLabel() {
        return label;
    }
    public int getPrice() {
        return price;
    }

}
