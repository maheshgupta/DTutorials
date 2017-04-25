package codeproject.sample.com.demotutorials;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import codeproject.sample.com.demotutorials.adapter.PhotosAdapter;
import codeproject.sample.com.demotutorials.presenters.albums.AlbumsView;
import codeproject.sample.com.demotutorials.presenters.albums.AlbumsViewPresenter;
import framework.core.BaseActivity;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import model.service.jsonplaceholder.photo.Photo;
import retrofit2.Retrofit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import service.core.ServiceResponseListener;
import service.retrofit.jsonplaceholder.services.PhotosService;
import viewhelpers.RecyclerViewItemClickListener;


public class AlbumsActivity extends BaseActivity implements AlbumsView {


    @BindView(R.id.rv_albums)
    RecyclerView recyclerViewAlbums;

    @Inject
    Retrofit retrofit;

    private PhotosAdapter photosAdapter;
    private GridLayoutManager layoutManager;
    private List<Photo> photosList;

    //    @Inject
    AlbumsViewPresenter albumsViewPresenter;
    private DisposableObserver<List<Photo>> disposable;

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

        albumsViewPresenter = new AlbumsViewPresenter(this);

        /*Dagger2 Injection*/
        getApplicationComponent().inject(this);

        showProgessDialog(getString(R.string.pleaseWait));
//        this.pullPhotos();
        getBooksFromService();
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

    private void openDetailOfPhoto(@NonNull View view, @NonNull Photo photo) {
        Intent intent = PhotoDetailViewActivity.makeIntent(this, photo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView photoImageView = (ImageView) view.findViewById(R.id.image_photo);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(AlbumsActivity.this, photoImageView, getString(R.string.tr_profile_photo));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
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
                    openDetailOfPhoto(view, photosList.get(position));
                }
            }
        });
    }


    private void getBooksFromService() {
        if (retrofit != null) {
            PhotosService call = retrofit.create(PhotosService.class);
            Observable<List<Photo>> subscriber = call.getPhotosListByAlbumId(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            disposable = subscriber.subscribeWith(new DisposableObserver<List<Photo>>() {
                @Override
                public void onNext(List<Photo> value) {
                    for (Photo eachPhoto : value) {
                        Log.i(TAG, "onNext: Value is " + eachPhoto.toString());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "onError: " + e.getMessage(), e);
                    dismissProgressDialog();
                }

                @Override
                public void onComplete() {
                    Log.i(TAG, "onComplete: ");
                    dismissProgressDialog();
                }
            });
        } else {
            Log.e(TAG, "getBooksFromService: " + "Retrofit service is not available");
        }
    }

}
