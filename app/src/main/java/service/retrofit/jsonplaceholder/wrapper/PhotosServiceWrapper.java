package service.retrofit.jsonplaceholder.wrapper;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import model.service.jsonplaceholder.photo.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.core.AppApiService;
import service.core.ServiceResponseListener;
import service.retrofit.jsonplaceholder.services.PhotosService;

public class PhotosServiceWrapper extends AppApiService {


    public PhotosServiceWrapper(Context context) {
        super(context);
    }


    public void getPhotos(int albumId, @NonNull final ServiceResponseListener<List<Photo>> listener) {
        PhotosService photoService = getRetrofit().create(PhotosService.class);
        Call<List<Photo>> photosCall = photoService.getPhotosByAlbumId(albumId);
        photosCall.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

}
