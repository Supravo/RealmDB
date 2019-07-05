package com.example.realmassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText dept;
    private EditText roll;
    private EditText phone;
    private Switch genderSwitch;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        name = findViewById(R.id.name);
        dept = findViewById(R.id.dept);
        roll = findViewById(R.id.roll);
        phone = findViewById(R.id.phone);
        genderSwitch = findViewById(R.id.gender);
    }

    public void onSave(View view){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        try {
            Student student = realm.createObject(Student.class, System.currentTimeMillis() / 1000);
            student.setName(name.getText().toString());
            student.setDept(dept.getText().toString());
            student.setRoll(Integer.parseInt(roll.getText().toString()));
            student.setPhone(phone.getText().toString());
            if(genderSwitch.isChecked())
                student.setGender(false);
            else
                student.setGender(true);
            realm.commitTransaction();
            Toast.makeText(mContext, "Your Data is Successfully Saved", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            realm.cancelTransaction();
            Toast.makeText(mContext, "Failed to Save Data" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void display(View view){
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
}
