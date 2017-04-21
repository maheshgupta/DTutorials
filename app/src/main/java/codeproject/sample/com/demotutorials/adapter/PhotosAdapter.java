package codeproject.sample.com.demotutorials.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import codeproject.sample.com.demotutorials.R;
import model.service.jsonplaceholder.photo.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private List<Photo> photosData;
    private Context mContext;

    public PhotosAdapter(@NonNull Context context, @NonNull List<Photo> photos) {
        this.mContext = context;
        this.photosData = photos;
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_photo_item, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotosViewHolder holder, int position) {
        Photo photo = this.photosData.get(position);
        Glide.with(this.mContext).load(photo.getThumbnailUrl()).into(holder.imageViewPhoto);
    }

    @Override
    public int getItemCount() {
        if (this.photosData != null) {
            return this.photosData.size();
        }
        return 0;
    }

    public static class PhotosViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPhoto;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            imageViewPhoto = (ImageView) itemView.findViewById(R.id.image_photo);
        }
    }


}
