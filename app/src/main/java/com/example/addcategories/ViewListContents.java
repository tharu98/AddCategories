package com.example.addcategories;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_content_layout);

        ListView listView=(ListView)findViewById(R.id.listView);
        sqLiteHelper=new SQLiteHelper(this,"CATEGORY",null,1);

        ArrayList theList=new ArrayList<>();
        Cursor res=sqLiteHelper.getData();

        if(res.getCount()==0 ){
            Toast.makeText(ViewListContents.this,"Database is empty",Toast.LENGTH_SHORT).show();
        }
        else{
           while (res.moveToNext()){
              String catName=res.getString(1);
              byte[] image=res.getBlob(2);
               ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
               listView.setAdapter(listAdapter);


           }
        }
    }
}
