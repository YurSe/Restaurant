package by.restaurant.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Image {
    private int imageId;
    private String image;
    private Collection<AlbumImage> albumImagesByImageId;
    private Collection<DishImage> dishImagesByImageId;

    @Id
    @Column(name = "image_id", nullable = false)
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "image", nullable = false, length = -1)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        if (imageId != image1.imageId) return false;
        if (image != null ? !image.equals(image1.image) : image1.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageId;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "imageByImageId")
    public Collection<AlbumImage> getAlbumImagesByImageId() {
        return albumImagesByImageId;
    }

    public void setAlbumImagesByImageId(Collection<AlbumImage> albumImagesByImageId) {
        this.albumImagesByImageId = albumImagesByImageId;
    }

    @OneToMany(mappedBy = "imageByImageId")
    public Collection<DishImage> getDishImagesByImageId() {
        return dishImagesByImageId;
    }

    public void setDishImagesByImageId(Collection<DishImage> dishImagesByImageId) {
        this.dishImagesByImageId = dishImagesByImageId;
    }
}
