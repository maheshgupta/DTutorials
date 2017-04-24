package codeproject.sample.com.demotutorials;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import framework.core.BaseActivity;
import model.service.jsonplaceholder.photo.Photo;

public class PhotoDetailViewActivity extends BaseActivity {

    private static final String SELECTED_PHOTO = "SELECTED_PHOTO";

    private Photo selectedPhoto;

    public static Intent makeIntent(@NonNull BaseActivity activity, @NonNull Photo photo) {
        Intent intent = new Intent(activity, PhotoDetailViewActivity.class);
        intent.putExtra(SELECTED_PHOTO, photo);
        return intent;
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_photo_detail_view;
    }

    @Override
    public String getLogTag() {
        return "PhotoDetailViewActivity";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        selectedPhoto = getIntent().getParcelableExtra(SELECTED_PHOTO);
        this.initUi();
    }

    private void initUi() {
        ImageView imageView = (ImageView) findViewById(R.id.imageview_profilephoto);
        Glide.with(this).load(this.selectedPhoto.getThumbnailUrl()).into(imageView);
    }

}
