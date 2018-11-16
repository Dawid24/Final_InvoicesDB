package pl.paciorek.dawid.finalinvoicesdb.model;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_user_id")
    private int id;

    @NotNull(message = "Name can not be empty")
    @Column(name = "first_name")
    private String name;

    @NotNull(message = "Last name can not be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email can not be empty")
    @Email(message = "Email is invalid")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Password can not be empty")
    @Length(min = 5, message = "Password should be at least 5 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    List<Product> productList = new ArrayList<>();

    /*@OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    List<Invoice> invoiceList = new ArrayList<>();
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }
    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
    public void addInvoice(Invoice invoice) {
        invoiceList.add(invoice);
        invoice.setUser(this);
    }
    public void removeInvoice(Invoice invoice) {
        invoiceList.remove(invoice);
        invoice.setUser(null);
    }*/

    public void addProduct(Product product) {
        productList.add(product);
        product.setUser(this);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
        product.setUser(null);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", roles=" + roles +
                '}';
    }
}