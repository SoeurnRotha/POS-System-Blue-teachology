package com.example.bluesystemwithroomdatabase.CreatePDF_for_invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.PaintDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.databinding.ActivityCreatePdfBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.Cheackout;
import MyAdapter.Pdf_Adapter.Pdf_Adapter;
import Mydatabase.BlueTeachnology_Database;

public class CreatePDF extends AppCompatActivity {
    ActivityCreatePdfBinding binding;
    Bitmap bitmap;
    Date date;
    DateFormat dateFormat;

    Pdf_Adapter adapter;
    BlueTeachnology_Dao blueTeachnology_dao;
    List<Cheackout> cheackoutList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        blueTeachnology_dao = BlueTeachnology_Database.getInstance(getApplicationContext()).blueTeachnology_dao();
        cheackoutList = blueTeachnology_dao.getAllCheackout();
        adapter = new Pdf_Adapter(cheackoutList, getApplicationContext());
        binding.listReceipt.setLayoutManager(new LinearLayoutManager(this));
        binding.listReceipt.setAdapter(adapter);




        binding.printReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = viewToBitmap(binding.pdfLayout, binding.pdfLayout.getWidth(), binding.pdfLayout.getHeight());
                createPDF(bitmap);
            }
        });



    }

    public static Bitmap viewToBitmap (View view, int width,int height){
        Bitmap viewMap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(viewMap);
        view.draw(canvas);
        return viewMap;
    }

    public void createPDF(Bitmap bitmap){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;

        int mheight = (int) height, mwidth = (int) width;

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(mwidth,mheight,1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap,mwidth , mheight,true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap,0,0,null);


        pdfDocument.finishPage(page);

        try {
            pdfDocument.writeTo(getFileName());

        }catch (IOException e){
            e.printStackTrace();
        }



    }

    public FileOutputStream getFileName(){
        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/PDF" );
        if(!pdfDir.exists()){
            pdfDir.mkdir();
        }
        File myPath = new File(pdfDir, "ok" + ".pdf");
        FileOutputStream fas = null;

        try {
            fas = new FileOutputStream(myPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  fas;

    }
    public void OpenCratePDF(){
        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/PDF" );
        if(!pdfDir.exists()){
            pdfDir.mkdir();
        }
        File myPath = new File(pdfDir, "cmlPdf" + ".pdf");

        if(myPath.exists()){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.putExtra(Intent.EXTRA_TEXT, "OpenPDF");
            intent.setType("application.pdf");

            Intent shareIntent = Intent.createChooser(intent, null);
            startActivity(shareIntent);
        }

    }
}