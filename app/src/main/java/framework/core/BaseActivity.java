package framework.core;


import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseActivity extends CoreModule {

    protected final String TAG = getLogTag();

    public abstract int getContentViewID();

    public abstract String getLogTag();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewID());
    }


}
