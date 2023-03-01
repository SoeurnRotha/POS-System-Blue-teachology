package com.example.bluesystemwithroomdatabase.testImage;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Model.TestImage;

public class Image_Adapter extends RecyclerView.Adapter<Image_Adapter.ViewImage>{

    List<TestImage> imageList;

    public Image_Adapter(List<TestImage> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewImage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_image, parent, false);

        return new ViewImage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewImage holder, int position) {
        TestImage testImage = (TestImage) imageList.get(position);
        holder.imageView.setImageBitmap(ImageHelper.getBitmapFromStr(testImage.getImage()));
        testImage.setImage(testImage.getImage());
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewImage extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ViewImage(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.show_image);
        }
    }
}
