package top.config.config.enums;

public enum DrinkType {
    COFFEE("咖啡",12),
    TEA("奶茶",15),
    JUICE("果汁",10);
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
