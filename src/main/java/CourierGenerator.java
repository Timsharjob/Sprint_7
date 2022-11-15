public class CourierGenerator {
    public static Courier getDefault() {
        return new Courier("sdfsdf","sdfsdf","sdfsdf");
    }
    public static CourierLogin getDefaultUser() {
        return new CourierLogin("sdfsdf","sdfsdf");
    }
    public static Courier getWithoutLogin() {
        Courier courier = getDefault();
        courier.setLogin(null);
        return  courier;
    }
}
