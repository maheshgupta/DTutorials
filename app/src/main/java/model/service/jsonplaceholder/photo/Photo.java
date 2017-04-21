package model.service.jsonplaceholder.photo;

/**
 * Model for Photo item
 * {
 *  "albumId": 1,
 *  "id": 1,
 *  "title": "accusamus beatae ad facilis cum similique qui sunt",
 *  "url": "http://placehold.it/600/92c952",
 *  "thumbnailUrl": "http://placehold.it/150/92c952"
 * }
 */
public class Photo {

    private int albumId;

    public int getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String thumbnailUrl;

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
