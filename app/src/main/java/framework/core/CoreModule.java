package framework.core;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;


/**
 * Module, to provide helper and utilty methods related to Android Core to provide
 * the sub-classes utilites.
 */

public class CoreModule extends RetrofitActivityModule {

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void writeToSharedPreferences(@NonNull String pKey, String value) {
        SharedPreferences.Editor editor = getSharedPreferencesModule().edit();
        editor.putString(pKey, value);
        editor.commit();
    }


    public String getFromSharedPreferences(@NonNull String pKey, String pDefaultValue) {
        return this.getSharedPreferencesModule().getString(pKey, pDefaultValue);
    }


    /**
     * Shows a Toast for a shorter Duration
     *
     * @param pMessage
     */
    public void showShortToast(@NonNull String pMessage) {
        Toast.makeText(this, pMessage, Toast.LENGTH_SHORT).show();
    }


    /**
     * Shows a Toast for a longer Duration
     *
     * @param pMessage
     */
    public void showLongToast(@NonNull String pMessage) {
        Toast.makeText(this, pMessage, Toast.LENGTH_LONG).show();
    }

    /**
     * Show the progress dialog
     *
     * @param message
     */
    protected void showProgessDialog(@NonNull String message) {
        if (this.progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
        }
        dismissProgressDialog();
        progressDialog.setMessage(message);
        progressDialog.show();
    }


    /**
     * Dismiss the progress dialog.
     */
    protected void dismissProgressDialog() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }
}
