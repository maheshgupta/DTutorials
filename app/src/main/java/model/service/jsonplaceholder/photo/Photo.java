package model.service.jsonplaceholder.photo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for Photo item
 * {
 * "albumId": 1,
 * "id": 1,
 * "title": "accusamus beatae ad facilis cum similique qui sunt",
 * "url": "http://placehold.it/600/92c952",
 * "thumbnailUrl": "http://placehold.it/150/92c952"
 * }
 */
public class Photo implements Parcelable {

    private int albumId;

    protected Photo(Parcel in) {
        albumId = in.readInt();
        id = in.readInt();
        title = in.readString();
        url = in.readString();
        thumbnailUrl = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(albumId);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(thumbnailUrl);
    }

    @Override
    public String toString() {
        return String.format("AlbumID : %d\nID : %d\nTitle : %s\nUrl : %s\n", getAlbumId(), getId(), getTitle(), getThumbnailUrl());
    }
}
