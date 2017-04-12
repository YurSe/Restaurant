package by.restaurant.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Table {
    private int tableId;
    private long number;
    private long zoneId;
    private Collection<Order> ordersByTableId;
    private Zone zoneByZoneId;

    @Id
    @Column(name = "table_id", nullable = false)
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Basic
    @Column(name = "zone_id", nullable = false)
    public long getZoneId() {
        return zoneId;
    }

    public void setZoneId(long zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        if (tableId != table.tableId) return false;
        if (number != table.number) return false;
        if (zoneId != table.zoneId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tableId;
        result = 31 * result + (int) (number ^ (number >>> 32));
        result = 31 * result + (int) (zoneId ^ (zoneId >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "tableByTableId")
    public Collection<Order> getOrdersByTableId() {
        return ordersByTableId;
    }

    public void setOrdersByTableId(Collection<Order> ordersByTableId) {
        this.ordersByTableId = ordersByTableId;
    }

    @ManyToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id", nullable = false)
    public Zone getZoneByZoneId() {
        return zoneByZoneId;
    }

    public void setZoneByZoneId(Zone zoneByZoneId) {
        this.zoneByZoneId = zoneByZoneId;
    }
}
