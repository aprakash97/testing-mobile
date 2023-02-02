package com.example.docpatient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("docpatDB", Context.MODE_PRIVATE,null);
        list = findViewById(R.id.list1);

        final Cursor c = db.rawQuery("SELECT * FROM records",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int course = c.getColumnIndex("course");
        int fee = c.getColumnIndex("fee");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        list.setAdapter(arrayAdapter);

        final ArrayList<Student> stud = new ArrayList<Student>();
        if(c.moveToFirst()){
            do{
                Student stu = new Student();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.course = c.getString(course);
                stu.fee = c.getString(fee);
                stud.add(stu);

                titles.add(c.getString(id)+ " \t " + c.getString(name)+ " \t " + c.getString(course)+ " \t " + c.getString(fee));

            }while(c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            list.invalidateViews();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String aa = titles.get(position).toString();
                Toast.makeText(getApplicationContext(), aa, Toast.LENGTH_LONG).show();
            }
        });
    }
}