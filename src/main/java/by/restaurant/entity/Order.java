package by.restaurant.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Order {
    private int orderId;
    private Timestamp date;
    private long tableId;
    private long userId;
    private Collection<Bill> billsByOrderId;
    private Table tableByTableId;
    private User userByUserId;
    private Collection<OrderDish> orderDishesByOrderId;

    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "table_id", nullable = false)
    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (tableId != order.tableId) return false;
        if (userId != order.userId) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) (tableId ^ (tableId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<Bill> getBillsByOrderId() {
        return billsByOrderId;
    }

    public void setBillsByOrderId(Collection<Bill> billsByOrderId) {
        this.billsByOrderId = billsByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "table_id", nullable = false)
    public Table getTableByTableId() {
        return tableByTableId;
    }

    public void setTableByTableId(Table tableByTableId) {
        this.tableByTableId = tableByTableId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderDish> getOrderDishesByOrderId() {
        return orderDishesByOrderId;
    }

    public void setOrderDishesByOrderId(Collection<OrderDish> orderDishesByOrderId) {
        this.orderDishesByOrderId = orderDishesByOrderId;
    }
}
