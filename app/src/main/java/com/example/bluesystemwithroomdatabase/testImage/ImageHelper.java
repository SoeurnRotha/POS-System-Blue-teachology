package com.example.bluesystemwithroomdatabase.testImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class ImageHelper {

    @TypeConverter
    public static byte [] getStringFromBitmap(Bitmap bitmapPicture){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG,0,byteArrayOutputStream);
        byte [] b = byteArrayOutputStream.toByteArray();
        return  b;
    }

    @TypeConverter
    public static Bitmap getBitmapFromStr(byte [] arr){
        return BitmapFactory.decodeByteArray(arr, 0, arr.length);
    }


}
