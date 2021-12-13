package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CharityEditActivity extends AppCompatActivity {

    AppDatabase db;
    Charity charity;
    EditText titleET,aboutET;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_edit);
        titleET=findViewById(R.id.charityTitle);
        aboutET=findViewById(R.id.charityAbout);
        editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCharity();

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AppDatabase").enableMultiInstanceInvalidation().allowMainThreadQueries().build();
        CharityDao charityDao = db.getCharityDao();

        charity = charityDao.findById(intent.getIntExtra("charity_id",-2));//-2 will mean no charity(error)
        titleET.setText(charity.getTitle());
        aboutET.setText(charity.getAbout());

    }

    private void editCharity() {
        if (titleET.getText().toString() != null && aboutET.getText().toString() != null)
        {
            charity.setTitle(titleET.getText().toString());
            charity.setAbout(aboutET.getText().toString());
        }


        CharityDao charityDao = db.getCharityDao();
        charityDao.updateCharities(charity);
    }
}