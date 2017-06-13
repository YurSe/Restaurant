package by.restaurant.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "zones")
public class Zone extends SuperClass {

    @Column(unique = true, nullable = false)
    private String title;

    @Type(type = "text")
    @Column(nullable = false)
    private String image;
    
    @Column(nullable = false)
    private String description;

    public Zone() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String describe) {
        this.description = describe;
    }
}
