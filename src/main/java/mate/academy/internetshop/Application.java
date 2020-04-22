package mate.academy.internetshop;

import java.math.BigDecimal;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Item;

public class Application {
    private static Injector injector = Injector.getInstance("internet.shop");

    public static void main(String[] args) {
        Item i = new Item("apple", new BigDecimal(100), 1);
        System.out.println(i.getCount());
        System.out.println(i.getName());
    }
}
