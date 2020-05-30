package com.example.shoesyourself.managers;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;


public class Asset {

    /**
     * Set image from assets in ImageView
     *
     * @param context  context courant
     * @param urlImage url complet de l'image dans le dossier assets
     * @param urlImage l'ImageView a setter
     */
    public static Drawable setImageInImageView(Context context, String urlImage) {
        AssetManager assetManager = context.getAssets();
        Drawable bitmap = null;
        try {
            InputStream is = assetManager.open(urlImage);
            bitmap = Drawable.createFromStream(is, null);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public static String[] getImageList(Context context, String folder) {
        AssetManager assetManager = context.getAssets();
        String[] images = {};
        try {
            images = assetManager.list(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }
}
