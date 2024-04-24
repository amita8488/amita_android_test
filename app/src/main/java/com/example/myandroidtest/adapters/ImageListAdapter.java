package com.example.myandroidtest.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myandroidtest.R;
import com.example.myandroidtest.model.ImageModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;


public class ImageListAdapter extends BaseAdapter {

    private ArrayList<ImageModel> mData;
    Activity activity;

    public ImageListAdapter(Activity context, ArrayList<ImageModel> data) {
        this.mData = data;
        this.activity = context;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        ImageModel imageModel = mData.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.raw_image_list, null);

            holder = new ViewHolder();
            holder.mImage = (ImageView) convertView.findViewById(R.id.imgUserPic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageModel.Thumbnail thumbnail = imageModel.getThumbnail();
        String image = thumbnail.getDomain() + "/" + thumbnail.getBasePath() + "/0/" + thumbnail.getKey();

        try {
            URL url = new URL(image);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 80, out);
            Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
            
            holder.mImage.setImageBitmap(decoded);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return convertView;
    }

    public class ViewHolder {
        private ImageView mImage;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}