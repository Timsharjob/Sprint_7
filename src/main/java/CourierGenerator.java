public class CourierGenerator {
    public static Courier getDefault() {
        return new Courier("sdfsdf", "sdfsdf", "sdfsdf");
    }

    public static CourierLogin getDefaultLogin() {
        return new CourierLogin("sdfsdf", "sdfsdf");
    }

    public static Courier getCourierWithoutLogin() {
        Courier courier = getDefault();
        courier.setLogin(null);
        return courier;
    }

    public static Courier getCourierWithoutPassword() {
        Courier courier = getDefault();
        courier.setPassword(null);
        return courier;
    }

    public static Courier getCourierWithoutFirstName() {
        Courier courier = getDefault();
        courier.setFirstName(null);
        return courier;
    }

    public static CourierLogin getLoginWithoutLogin() {
        CourierLogin courierLogin = getDefaultLogin();
        courierLogin.setLogin(null);
        return courierLogin;
    }

    public static CourierLogin getLoginWithoutPassword() {
        CourierLogin courierLogin = getDefaultLogin();
        courierLogin.setPassword(null);
        return courierLogin;
    }

    public static CourierLogin getLoginWrongPassword() {
        CourierLogin courierLogin = getDefaultLogin();
        courierLogin.setPassword("1111");
        return courierLogin;
    }

    public static CourierLogin getLoginWrongLogin() {
        CourierLogin courierLogin = getDefaultLogin();
        courierLogin.setLogin("1111");
        return courierLogin;
    }
}
