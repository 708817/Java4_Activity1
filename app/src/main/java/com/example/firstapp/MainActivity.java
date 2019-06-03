package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runist();
    }

    public void runist() {
        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);
        final EditText et3 = (EditText) findViewById(R.id.et3);
        final EditText et4 = (EditText) findViewById(R.id.et4);
        final TextView tv2 = (TextView) findViewById(R.id.tv2);
        final RadioGroup rgSexGender = (RadioGroup) findViewById(R.id.rgsexgender);

        Button button = (Button) findViewById(R.id.b1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    tv2.setText("");

                    int shaded = rgSexGender.getCheckedRadioButtonId();
                    RadioButton rbSexGender = (RadioButton) findViewById(shaded);
                    boolean trial1 = false, trial2 = false, trial3 = false;
                    String name = "", email = "", pass = "", cpass = "", sexgender = "";
                    String checkemail;

                    name = et1.getText().toString();
                    email = et2.getText().toString();
                    pass = et3.getText().toString();
                    cpass = et4.getText().toString();
                    sexgender = rbSexGender.getText().toString();

                    if (!confirmPassword(pass, cpass)) {
                        tv2.setText("Passwords do not match!");
                    } else {
                        trial2 = true;
                    }

                    checkemail = checkEmail(email);
                    if (!checkemail.equals("success")) {
                        tv2.setText(checkemail);
                    } else {
                        trial3 = true;
                    }

                    if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || cpass.isEmpty()) {
                        tv2.setText("Fill all required fields!");
                    } else {
                        trial1 = true;
                    }

                    if (trial1 && trial2 && trial3) {
                        Bundle b = new Bundle();
                        b.putString("inputName", name);
                        b.putString("inputEmail", email);
                        b.putString("inputPassword", pass);
                        b.putString("inputSexGender", sexgender);

                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                        i.putExtras(b);

                        startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean confirmPassword(String pass, String confirm) {

        if (!pass.equals(confirm)) {
            return false;
        }

        return true;
    }

    private String checkEmail(String email) {
        String error1 = "Invalid E-mail!",
                error2 = "E-mail does not exist!";

        if(email.indexOf('@') < 0) {
            return error1;
        }

        String[] dotCheck = email.split("@");
        if(dotCheck[1].indexOf('.') < 0) {
            return error1;
        }

        //SQLite e-mail check

        return "success";
    }
}
