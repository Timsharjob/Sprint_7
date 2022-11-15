public class OrdersGenerator {
    public static Orders getDefaultOrder() {
        return new Orders("Naruto", "Uchiha", "Konoha, 142 apt.", "4",
                "7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha");
    }

    public static Orders getOrderBlackColor() {
        Orders order = getDefaultOrder();
        order.setColor(new String[]{"BLACK"});
        return order;
    }

    public static Orders getOrderGreyColor() {
        Orders order = getDefaultOrder();
        order.setColor(new String[]{"GREY"});
        return order;
    }

    public static Orders getOrderGreyAndBlackColor() {
        Orders order = getDefaultOrder();
        order.setColor(new String[]{"GREY", "BLACK"});
        return order;
    }
}
