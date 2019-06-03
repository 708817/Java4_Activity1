package com.example.firstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent inputIn = getIntent();
        Bundle inputB = inputIn.getExtras();

        final TextView name = (TextView) findViewById(R.id.tvName);
        final TextView email = (TextView) findViewById(R.id.tvEmail);
        final TextView birth = (TextView) findViewById(R.id.tvBirth);
        final TextView sexgender = (TextView) findViewById(R.id.tvSexGender);
        final TextView degree = (TextView) findViewById(R.id.tvDegree);
        final TextView level = (TextView) findViewById(R.id.tvLevel);
        final TextView hobbies = (TextView) findViewById(R.id.tvHobbies);
        final TextView pass = (TextView) findViewById(R.id.tvPass);

        String inputName = inputB.getString("name").toString();
        String inputEmail = inputB.getString("email").toString();
        String inputBirth = inputB.getString("birth").toString();
        String inputSexGender = inputB.getString("sexgender").toString();
        String inputDegree = inputB.getString("degree").toString();
        String inputLevel = inputB.getString("level").toString();
        String inputHobbies = inputB.getString("hobbies").toString();
        String inputPass = inputB.getString("pass").toString();

        if (inputHobbies.isEmpty()) {
            inputHobbies = "No Hobbies Sad";
        }

        name.setText(inputName);
        email.setText(inputEmail);
        birth.setText(inputBirth);
        sexgender.setText(inputSexGender);
        degree.setText(inputDegree);
        level.setText(inputLevel);
        hobbies.setText(inputHobbies);
        pass.setText(inputPass);

        int counter = 0;

        if (counter <= 0) {
            counter++;
            AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this, R.style.CustomAlertDialog);
            /*TextView myMsg = new TextView(this);
            myMsg.setText("Registration Successful");
            myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
            builder.setView(myMsg);*/
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
}
