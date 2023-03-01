package com.example.bluesystemwithroomdatabase.testImage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.example.bluesystemwithroomdatabase.databinding.ActivityTestImageBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.TestImage;
import Mydatabase.BlueTeachnology_Database;

public class TestImagePage extends AppCompatActivity {

    int SELECT_PICTURE= 200;
    Bitmap bitmap = null;


    ActivityTestImageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityTestImageBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageFromGallery();
            }
        });

        binding.saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });

        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();

        List<TestImage> testImages =  blueTeachnology_dao.getImage();
        Image_Adapter adapter = new Image_Adapter(testImages);

        binding.listTestImage.setLayoutManager(new LinearLayoutManager(this));
        binding.listTestImage.setAdapter(adapter);
    }


    private void loadImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == SELECT_PICTURE){
                Uri uri = data.getData();
                if(uri != null){
                    binding.imageData.setImageURI(uri);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

                        binding.imageData.setImageBitmap(bitmap);
                    }catch (IOException e){

                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void save(View view){
        TestImage testImage = new TestImage();
        testImage.setImage(ImageHelper.getStringFromBitmap(bitmap));
        BlueTeachnology_Dao blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        blueTeachnology_dao.insertImage(testImage);
    }






}