package mate.academy.internetshop.model;

import java.math.BigDecimal;
import mate.academy.internetshop.db.Storage;

public class Item {
    private Long id;
    private String name;
    private BigDecimal price;
    private int count;

    public Item() {
        this.id = Storage.getId();
        this.name = null;
        this.price = null;
        this.count = 0;
    }

    public Item(String name, BigDecimal price, int count) {
        this.id = Storage.getId();
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
