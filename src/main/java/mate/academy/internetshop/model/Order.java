package mate.academy.internetshop.model;

import java.util.List;
import mate.academy.internetshop.db.Storage;

public class Order {
    private User user;
    private List<Item> items;
    private Long id;

    public Order() {
        this.user = null;
        this.items = null;
        this.id = Storage.getId();
    }

    public Order(User user, List<Item> items) {
        this.user = user;
        this.items = items;
        this.id = Storage.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
