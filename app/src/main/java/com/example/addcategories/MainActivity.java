package com.example.addcategories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

        EditText catName;
        Button addCatBtn,imageBtn;
        ImageView imageView;

        final int REQUEST_CODE_GALLERY =999;

        public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        sqLiteHelper = new SQLiteHelper(this,"Category.db",null,1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS CATEGORY(ID INTEGER PRIMARY KEY AUTOINCREMENT,catName VARCHAR,image BLOB)");

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        ActivityCompat.requestPermissions(
                             MainActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_CODE_GALLERY
                                );


            }
        });
        addCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            catName.getText().toString().trim(),
                            imageViewToByte(imageView)
                    );
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    catName.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);

                }catch (Exception e){
                    e.printStackTrace();

                }



            }
        });

    }

    private byte[] imageViewToByte(ImageView imageView) {
         Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
         ByteArrayOutputStream stream =new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
         byte[] byteArray =stream.toByteArray();
         return  byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
             else{
                Toast.makeText(getApplicationContext(),"You don't have permission to access file location!",Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode== REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri =data.getData();

            try {
                InputStream inputStream =getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        catName=(EditText) findViewById(R.id.catName);
        addCatBtn=(Button) findViewById(R.id.addCatBtn);
        imageBtn=(Button) findViewById(R.id.imageBtn);
        imageView=(ImageView) findViewById(R.id.imageView);
    }

    }

