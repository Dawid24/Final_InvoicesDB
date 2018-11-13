package pl.paciorek.dawid.finalinvoicesdb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "torder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int order_id;

    @NotNull(message = "Name can not be empty")
    @Column(name = "user_id")
    private String user_id;

    @NotNull(message = "Name can not be empty")
    @Column(name = "product_id")
    private String product_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date added = new Date();

    @NotNull(message = "Name can not be empty")
    @Column(name = "status")
    private String status;

    public Order(@NotNull(message = "Name can not be empty") String user_id, @NotNull(message = "Name can not be empty") String product_id, Date added, @NotNull(message = "Name can not be empty") String status) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.added = added;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


