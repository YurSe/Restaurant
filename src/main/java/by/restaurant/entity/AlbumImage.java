package by.restaurant.entity;

import javax.persistence.*;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
@javax.persistence.Table(name = "album_image", schema = "public", catalog = "restaurant")
public class AlbumImage {
    private long albumId;
    private long imageId;
    private Album albumByAlbumId;
    private Image imageByImageId;

    @Basic
    @Column(name = "album_id", nullable = false)
    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "image_id", nullable = false)
    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumImage that = (AlbumImage) o;

        if (albumId != that.albumId) return false;
        if (imageId != that.imageId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (albumId ^ (albumId >>> 32));
        result = 31 * result + (int) (imageId ^ (imageId >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "album_id", nullable = false)
    public Album getAlbumByAlbumId() {
        return albumByAlbumId;
    }

    public void setAlbumByAlbumId(Album albumByAlbumId) {
        this.albumByAlbumId = albumByAlbumId;
    }

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "image_id", nullable = false)
    public Image getImageByImageId() {
        return imageByImageId;
    }

    public void setImageByImageId(Image imageByImageId) {
        this.imageByImageId = imageByImageId;
    }
}
