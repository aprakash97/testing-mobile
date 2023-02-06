package com.example.docpatient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3, ed4;
    Button btn1, btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        ed1 = findViewById(R.id.id);
        ed2 = findViewById(R.id.name);
        ed3 = findViewById(R.id.course);
        ed4 = findViewById(R.id.fee);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        Intent i = getIntent();
        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("course").toString();
        String t4 = i.getStringExtra("fee").toString();

        ed1.setText(t1);
        ed2.setText(t2);
        ed3.setText(t3);
        ed4.setText(t4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });
    }

    public void update(){
        try{
            String id = ed1.getText().toString();
            String name = ed2.getText().toString();
            String course = ed3.getText().toString();
            String fee = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("docpatDB", Context.MODE_PRIVATE,null);

            String sql = "UPDATE records SET name=?, course=?, fee=? WHERE id=?";

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,course);
            statement.bindString(3,fee);
            statement.bindString(4,id);
            statement.execute();

//                  TABLE DELETING
//                db.execSQL("DELETE FROM records");
//                Toast.makeText(this,"Record TABLE DELETED",Toast.LENGTH_LONG).show();

            Toast.makeText(this,"Record Updated",Toast.LENGTH_LONG).show();
//            ed1.setText("");
//            ed2.setText("");
//            ed3.setText("");
//            ed1.requestFocus();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete(){
        try{
            String id = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("docpatDB", Context.MODE_PRIVATE,null);

            String sql = "DELETE FROM records WHERE id=?";

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,id);
//            statement.bindString(2,course);
//            statement.bindString(3,fee);
//            statement.bindString(4,id);
            statement.execute();

            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();


        }catch (Exception e){
            System.out.println(e);
        }
    }
}