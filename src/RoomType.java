public enum RoomType {

    STANDARD(50,"стандартный однокомнатный номер"),
    STUDIO(60,"однокомнатный номер с мини-кухней"),
    JUNIOR(100, "номер улучшенной планировки"),
    SUITE(200,"улучшенной планировки, из двух комнат"),
    DELUXE(300,"номер повышенного комфорта"),
    PRESIDENT(1000,"самые роскошные номера");

    private final double price;
    private final String description;

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    RoomType(double price, String description) {
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoomType{");
        sb.append("price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
