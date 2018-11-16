package pl.paciorek.dawid.finalinvoicesdb.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
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

    public Product(@NotNull(message = "Name can not be empty") String name, @NotNull(message = "Price can not be empty") int price, User user) {
        this.name = name;
        this.price = price;
        this.user = user;
    }

    public Product(@NotNull(message = "Name can not be empty") String name, @NotNull(message = "Price can not be empty") int price) {
        this.name = name;
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", user=" + user +
                '}';
    }
}
