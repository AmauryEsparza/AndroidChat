package com.example.amauryesparza.androidchat.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by AmauryEsparza on 9/13/16.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager requestManager;

    public GlideImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
        requestManager.load(url).into(imageView);
    }
}
