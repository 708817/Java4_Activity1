package com.example.firstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    DBController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent inputIn = getIntent();
        Bundle inputB = inputIn.getExtras();

        dbController = new DBController(this, "", null, 1);

        final TextView name = (TextView) findViewById(R.id.tvName);
        final TextView email = (TextView) findViewById(R.id.tvEmail);
        final TextView birth = (TextView) findViewById(R.id.tvBirth);
        final TextView sexgender = (TextView) findViewById(R.id.tvSexGender);
        final TextView degree = (TextView) findViewById(R.id.tvDegree);
        final TextView level = (TextView) findViewById(R.id.tvLevel);
        final TextView hobbies = (TextView) findViewById(R.id.tvHobbies);
        final TextView pass = (TextView) findViewById(R.id.tvPass);

        String inputEmail = inputB.getString("email").toString();


        try {
            dbController.dbSelect(inputEmail, name, email, birth, sexgender, degree, level, hobbies, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this, R.style.CustomAlertDialog);
        builder.setMessage("\nRegistration Successful");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(true);
        builder.show();

    }
}
