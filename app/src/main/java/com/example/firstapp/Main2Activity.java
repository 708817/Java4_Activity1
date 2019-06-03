package com.example.firstapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        runist();
    }

    public void runist() {

        // GET BUNDLE VARIABLES START
        Intent prevIn = getIntent();
        Bundle prevB = prevIn.getExtras();

        final String name = prevB.getString("inputName");
        final String email = prevB.getString("inputEmail");
        final String pass = prevB.getString("inputPassword");
        final String sexgender = prevB.getString("inputSexGender");
        // GET BUNDLE VARIABLES END

        // SET XML IDS START
        final TextView tv2 = (TextView) findViewById(R.id.tv2);
        final Spinner s1 = (Spinner) findViewById(R.id.s1);
        final Spinner s2 = (Spinner) findViewById(R.id.s2);
        final EditText et5 = (EditText) findViewById(R.id.et5);
        final CheckBox cb1 = (CheckBox) findViewById(R.id.cb1);
        final CheckBox cb2 = (CheckBox) findViewById(R.id.cb2);
        final CheckBox cb3 = (CheckBox) findViewById(R.id.cb3);
        final CheckBox cb4 = (CheckBox) findViewById(R.id.cb4);
        final CheckBox cb5 = (CheckBox) findViewById(R.id.cb5);
        final CheckBox cb6 = (CheckBox) findViewById(R.id.cb6);
        Button b1 = (Button) findViewById(R.id.b1);
        Button b2 = (Button) findViewById(R.id.b2);
        // SET XML IDS END

        // SET SPINNER VALUES START
        Spinner s1set = (Spinner) findViewById(R.id.s1);
        Spinner s2set = (Spinner) findViewById(R.id.s2);
        ArrayAdapter<String> aas1 = new ArrayAdapter<String>(Main2Activity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sp1list));
        s1set.setAdapter(aas1);
        ArrayAdapter<String> aas2 = new ArrayAdapter<String>(Main2Activity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sp2list));
        s2set.setAdapter(aas2);
        // SET SPINNER VALUES END

        // DATE CLICK START
        et5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Main2Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                et5.setText(date);
            }
        };
        // DATE CLICK END

        // NEXT BUTTON START
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tv2.setText("");
                    boolean trial1 = false;

                    String hobbies = "";
                    int max = 30;
                    if(cb1.isChecked()) {
                        if (!hobbies.isEmpty()) {
                            hobbies += ", ";
                        }
                        hobbies += "Football";
                    }
                    if(cb2.isChecked()) {
                        if (!hobbies.isEmpty()) {
                            hobbies += ", ";
                        }
                        hobbies += "Swimming";
                    }
                    if(cb3.isChecked()) {
                        if (!hobbies.isEmpty()) {
                            hobbies += ", ";
                        }
                        hobbies += "Sleeping";
                    }
                    if(cb4.isChecked()) {
                        if (!hobbies.isEmpty()) {
                            hobbies += ", ";
                        }
                        hobbies += "Gaming";
                    }
                    if(cb5.isChecked()) {
                        if (!hobbies.isEmpty()) {
                            hobbies += ", ";
                        }
                        hobbies += "Music";
                    }
                    if(cb6.isChecked()) {
                        if (!hobbies.isEmpty()) {
                            hobbies += ", ";
                        }
                        hobbies += "Hiking";
                    }

                    String degree = s1.getSelectedItem().toString();
                    String level = s2.getSelectedItem().toString();
                    String birth = et5.getText().toString();

                    if (birth.isEmpty()) {
                        tv2.setText("Fill all required fields!");
                    } else {
                        trial1 = true;
                    }

                    if (trial1) {
                        Bundle b = new Bundle();
                        b.putString("name", name);
                        b.putString("email", email);
                        b.putString("pass", pass);
                        b.putString("sexgender", sexgender);
                        b.putString("degree", degree);
                        b.putString("level", level);
                        b.putString("birth", birth);
                        b.putString("hobbies", hobbies);

                        Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                        i.putExtras(b);

                        startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // NEXT BUTTON END

        // BACK BUTTON START
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onBackPressed();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // BACK BUTTON END

        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextView tv1 = (TextView) findViewById(R.id.tv1);
                final EditText et1 = (EditText) findViewById(R.id.et1);

                tv1.setText(b.getString("input1"));

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

                        Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                        i.putExtras(b);

                        startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/
    }
}
