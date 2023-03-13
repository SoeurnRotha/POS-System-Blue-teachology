package com.example.bluesystemwithroomdatabase.CreatePDF_for_invoice;

import androidx.appcompat.app.AppCompatActivity;

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

public class CreatePDF extends AppCompatActivity {
    ActivityCreatePdfBinding binding;
    Bitmap bitmap , selectBitmp;
    int pageWidth = 500;

    Date date;
    DateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        bitmap = BitmapFactory.decodeResource(getResources() , R.drawable.blue);
        selectBitmp = Bitmap.createScaledBitmap(bitmap , 250,200 ,false);


    }


    public void createPDF(){
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint titlePaint = new Paint();

        date = new Date();
        dateFormat = new SimpleDateFormat("dd/MM/yy");



        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(500,1000,1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();


        canvas.drawBitmap(selectBitmp , pageWidth/2 -100,20,paint);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(20f);
        canvas.drawText("Company Name",pageWidth/2 , 300, titlePaint );

        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(15f);
        canvas.drawText("Tel : 0123456789", 20,320 , titlePaint );

        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(15f);
        canvas.drawText("Address : #320D, street 150 , Sengkat Boeng keng kang 1", 20,340 , titlePaint );

        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(15f);
        canvas.drawText("Phnom penh , Cambodia", 20,360 , titlePaint );

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(20f);
        canvas.drawText("បង្កាន់ដៃ / RECEIPT",pageWidth/2 , 400, titlePaint );



        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(15f);
        canvas.drawText("Order number", 20,420 , titlePaint );


        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(15f);
        canvas.drawText("Date: \t" + dateFormat.format(date) , 20,440 , titlePaint );
        canvas.drawText( "/"+ time.format(date) , 130,440 , titlePaint );


        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(15f);
        canvas.drawText("Cashier : Soeurn Rotha", 20,460 , titlePaint );



        titlePaint.setTextAlign(Paint.Align.LEFT);
        titlePaint.setStyle(Paint.Style.FILL);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(18f);
        canvas.drawText("No", 20,500 , titlePaint );
        canvas.drawText("Description", 100,500 , titlePaint );
        canvas.drawText("QTY", 250,500 , titlePaint );
        canvas.drawText("Price", 330,500 , titlePaint );
        canvas.drawText("Amount", 400,500 , titlePaint );




        pdfDocument.finishPage(page);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/text.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(CreatePDF.this, ""+file, Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }

        pdfDocument.close();
    }
}