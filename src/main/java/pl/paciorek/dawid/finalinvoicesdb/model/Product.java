package pl.paciorek.dawid.finalinvoicesdb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @NotNull(message = "Name can not be empty")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Price can not be empty")
    @Column(name = "price")
    private int price;

    @NotNull(message = "Quantity can not be empty")
    @Column(name = "quantity")
    private int quantity;

    public Product(@NotNull(message = "Name can not be empty") String name, @NotNull(message = "Price can not be empty") int price, @NotNull(message = "Quantity can not be empty") int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
