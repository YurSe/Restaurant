package by.restaurant.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Zone {
    private int zoneId;
    private String title;
    private String description;
    private Collection<Table> tablesByZoneId;

    @Id
    @Column(name = "zone_id", nullable = false)
    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zone zone = (Zone) o;

        if (zoneId != zone.zoneId) return false;
        if (title != null ? !title.equals(zone.title) : zone.title != null) return false;
        if (description != null ? !description.equals(zone.description) : zone.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = zoneId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "zoneByZoneId")
    public Collection<Table> getTablesByZoneId() {
        return tablesByZoneId;
    }

    public void setTablesByZoneId(Collection<Table> tablesByZoneId) {
        this.tablesByZoneId = tablesByZoneId;
    }
}
