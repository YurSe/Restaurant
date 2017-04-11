package by.restaurant.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Album {
    private int albumId;
    private String title;
    private String description;
    private Collection<AlbumImage> albumImagesByAlbumId;

    @Id
    @Column(name = "album_id", nullable = false)
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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

        Album album = (Album) o;

        if (albumId != album.albumId) return false;
        if (title != null ? !title.equals(album.title) : album.title != null) return false;
        if (description != null ? !description.equals(album.description) : album.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = albumId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "albumByAlbumId")
    public Collection<AlbumImage> getAlbumImagesByAlbumId() {
        return albumImagesByAlbumId;
    }

    public void setAlbumImagesByAlbumId(Collection<AlbumImage> albumImagesByAlbumId) {
        this.albumImagesByAlbumId = albumImagesByAlbumId;
    }
}
