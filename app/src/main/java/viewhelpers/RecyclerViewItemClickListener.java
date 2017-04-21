package viewhelpers;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewItemClickListener extends GestureDetector.SimpleOnGestureListener implements RecyclerView.OnItemTouchListener {

    public interface Callback {
        public void onItemClick(View view, int position);
    }

    private Callback mCallback;
    private Context mContext;
    private GestureDetector mGestureDetector;


    public RecyclerViewItemClickListener(@NonNull Context context, @NonNull Callback callback) {
        this.mContext = context;
        this.mGestureDetector = new GestureDetector(this.mContext, this);
        this.setCallback(callback);
    }

    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mGestureDetector.onTouchEvent(e)) {
            getCallback().onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    public Callback getCallback() {
        if (this.mCallback == null) {
            this.mCallback = new Callback() {
                @Override
                public void onItemClick(View view, int position) {

                }
            };
        }
        return mCallback;
    }

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }
}
