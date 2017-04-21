package codeproject.sample.com.demotutorials;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import codeproject.sample.com.demotutorials.adapter.PhotosAdapter;
import framework.core.BaseActivity;
import model.service.jsonplaceholder.photo.Photo;
import service.core.ServiceResponseListener;
import viewhelpers.RecyclerViewItemClickListener;


public class AlbumsActivity extends BaseActivity {

    @BindView(R.id.rv_albums)
    RecyclerView recyclerViewAlbums;

    private PhotosAdapter photosAdapter;
    private GridLayoutManager layoutManager;
    private List<Photo> photosList;

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
        showProgessDialog(getString(R.string.pleaseWait));
        this.pullPhotos();
        this.recyclerViewAlbums.addOnItemTouchListener(getRecyclerViewItemClickListener());
    }

    private void populateRecylerView() {
        dismissProgressDialog();
        /*Skip, if the items for the list is either null or 0 size*/
        if (this.photosList == null || this.photosList.size() == 0) return;

        this.recyclerViewAlbums.setHasFixedSize(true);
        if (this.layoutManager == null) {
            layoutManager = new GridLayoutManager(AlbumsActivity.this, calculateNoOfColumns(this));
        }
        this.recyclerViewAlbums.setLayoutManager(layoutManager);
        PhotosAdapter adapter = new PhotosAdapter(this, this.photosList);
        this.recyclerViewAlbums.setAdapter(adapter);
    }

    private void openDetailOfPhoto(@NonNull Photo photo) {
        Intent intent = new Intent(AlbumsActivity.this, PhotoDetailViewActivity.class);
        startActivity(intent);
    }


    private void pullPhotos() {
        getPhotosServiceWrapper().getPhotos(1, new ServiceResponseListener<List<Photo>>() {
            @Override
            public void onSuccess(List<Photo> response) {
                dismissProgressDialog();
                Log.i(TAG, "onSuccess: " + response.size());
                photosList = response;
                populateRecylerView();
            }

            @Override
            public void onError(String errMessage) {
                Log.e(TAG, "onError: " + errMessage);
                dismissProgressDialog();
                showShortToast("Unable to get photos, Please try later");
            }
        });
    }

    public int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 164);
        return noOfColumns;
    }

    private RecyclerViewItemClickListener getRecyclerViewItemClickListener() {
        return new RecyclerViewItemClickListener(this, new RecyclerViewItemClickListener.Callback() {
            @Override
            public void onItemClick(View view, int position) {
                if (photosList != null && photosList.get(position) != null) {
                    openDetailOfPhoto(photosList.get(position));
                }
            }
        });
    }

}
