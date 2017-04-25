package service.retrofit.jsonplaceholder.services;


import java.util.List;

import io.reactivex.Observable;
import model.service.jsonplaceholder.photo.Photo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotosService {

    @GET("photos")
    Call<List<Photo>> getPhotosByAlbumId(@Query("albumId") int albumId);

    @GET("photos")
    Observable<List<Photo>> getPhotosListByAlbumId(@Query("albumId") int albumId);


}
