package com.example.c0768448_w2020_mad3125_midterm.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c0768448_w2020_mad3125_midterm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PersonInformationEntryActivity extends AppCompatActivity {

    private EditText txtSin,txtFirstName,txtLastName,txtDob,txtGrossIncome,txtRrsContri;
    private  TextView txtAge,txtTaxFillDate;
    private RadioButton rdMr,rdMrs,rdOther;
    private RadioGroup rdGender;
    private Button btnCalculate;
    private   DatePickerDialog picker;
    final Calendar cldr = Calendar.getInstance();
    final int day = cldr.get(Calendar.DAY_OF_MONTH);
    final   int month = cldr.get(Calendar.MONTH);
    final int year = cldr.get(Calendar.YEAR);
    String gender=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_information_entry);

        txtSin = findViewById(R.id.txtSinNumb);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtDob = findViewById(R.id.txtBirthDate);
        txtTaxFillDate = findViewById(R.id.txtTaxFillDate);
        txtGrossIncome = findViewById(R.id.txtGrossIncome);
        txtRrsContri = findViewById(R.id.txtRrs);
        txtAge = findViewById(R.id.txtAge);
        rdGender = findViewById(R.id.rdGrpGender);
        rdMr = findViewById(R.id.rdMale);
        rdMrs = findViewById(R.id.rdFeMale);
        rdOther = findViewById(R.id.rdOther);
        btnCalculate = findViewById(R.id.btnCalculate);


        txtTaxFillDate.setText(new StringBuilder()
                .append(day).append(" ").append("-").append(month + 1).append("-")
                .append(year));
        //Birth Date Picker
        txtDob.setInputType(InputType.TYPE_NULL);
        txtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // date picker dialog
                picker = new DatePickerDialog(PersonInformationEntryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {


                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, day);
                picker.show();
            }
        });


        rdGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i == R.id.rdMale){
                    gender = rdMr.getText().toString();
                }else if(i == R.id.rdFeMale){
                    gender = rdMrs.getText().toString();
                }else {
                    gender = rdOther.getText().toString();
                }
            }

        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int age;
                age = calculateAge(txtDob.getText().toString());
                if (age < 18) {
                    btnCalculate.setAlpha(.5f);
                    btnCalculate.setClickable(false);
                } else {

                    Intent intent = new Intent(PersonInformationEntryActivity.this, TaxDataDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    int calculateAge(String date){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }

}
