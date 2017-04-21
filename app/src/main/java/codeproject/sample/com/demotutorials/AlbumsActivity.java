package codeproject.sample.com.demotutorials;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import framework.core.BaseActivity;
import model.service.jsonplaceholder.photo.Photo;
import service.core.ServiceResponseListener;


public class AlbumsActivity extends BaseActivity {

    @BindView(R.id.rv_albums)
    RecyclerView recyclerViewAlbums;

    @Override
    public int getContentViewID() {
        return R.layout.activity_albums;
    }

    @Override
    public String getLogTag() {
        return "AlbumsActivity";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.pullAlbums();
    }

    private void pullAlbums() {
        getPhotosServiceWrapper().getPhotos(1, new ServiceResponseListener<List<Photo>>() {
            @Override
            public void onSuccess(List<Photo> response) {
                Log.i(TAG, "onSuccess: " + response.size());
            }

            @Override
            public void onError(String errMessage) {
                Log.e(TAG, "onError: " + errMessage);
            }
        });
    }


}
