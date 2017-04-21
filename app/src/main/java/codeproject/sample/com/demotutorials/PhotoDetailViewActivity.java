package codeproject.sample.com.demotutorials;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import framework.core.BaseActivity;

public class PhotoDetailViewActivity extends BaseActivity {


    @Override
    public int getContentViewID() {
        return R.layout.activity_photo_detail_view;
    }

    @Override
    public String getLogTag() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
