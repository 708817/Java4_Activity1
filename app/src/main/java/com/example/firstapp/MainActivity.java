package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DBController dbController;

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
                        et3.setText("");
                        et4.setText("");
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
                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                        Bundle b = new Bundle();

                        View imageView = (ImageView) findViewById(R.id.imageView);
                        View tv1 = (TextView) findViewById(R.id.tv1);

                        b.putString("inputName", name);
                        b.putString("inputEmail", email);
                        b.putString("inputPassword", pass);
                        b.putString("inputSexGender", sexgender);

                        String transitionName = getString(R.string.transition_string);
                        String transitionName2 = getString(R.string.transition_string2);
                        // TRANSITION TEST START
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                                Pair.create(imageView, transitionName),
                                Pair.create(tv1, transitionName2));
                        // TRANSITION TEST END

                        i.putExtras(b);

//                        startActivity(i);
                        startActivity(i, options.toBundle());
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

        dbController = new DBController(this, "", null, 1);
        String error1 = "Invalid E-mail!",
                error2 = "E-mail already exists!";

        if(email.indexOf('@') < 0) {
            return error1;
        }

        String[] contentCheck1 = email.split("@");
        if(contentCheck1[0].length() < 1 || contentCheck1[1].indexOf('.') < 0) {
            return error1;
        }

        String[] contentCheck2 = contentCheck1[1].split("\\.");
        if(contentCheck2.length < 2) {
            return error1;
        }
        if(contentCheck2[0].length() < 1 || contentCheck2[1].length() < 1) {
            return error1;
        }

        //SQLite e-mail check
        if(!dbController.checkEmail(email)) {
            return error2;
        }

        return "success";
    }
}
