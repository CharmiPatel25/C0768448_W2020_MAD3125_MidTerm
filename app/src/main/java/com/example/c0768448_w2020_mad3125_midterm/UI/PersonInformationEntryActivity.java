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

import com.example.c0768448_w2020_mad3125_midterm.Models.CRACustomer;
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

             //
                /*int age = Integer.parseInt(String.valueOf(txtAge));
                if(age< 18)
                {
                    Toast.makeText(PersonInformationEntryActivity.this, "Age less than 18", Toast.LENGTH_SHORT).show();
                }*/
                if (txtSin.getText().toString().isEmpty() ) {
                    errorMsg();
                    txtSin.setError("Enter Correct Sin Number");

                }
                if(txtFirstName.getText().toString().isEmpty())
                {
                    errorMsg();;
                    txtFirstName.setError("Enter First Name");
                }
                if(txtLastName.getText().toString().isEmpty())
                {
                    errorMsg();;
                    txtLastName.setError("Enter Last Name");
                }
                if(txtDob.getText().toString().isEmpty())
                {
                    errorMsg();;
                    txtDob.setError("Enter  Date Of Birth");
                }

                if(txtGrossIncome.getText().toString().isEmpty())
                {
                    errorMsg();;
                    txtGrossIncome.setError("Enter Gross Income");
                }

                if(txtRrsContri.getText().toString().isEmpty())
                {
                    errorMsg();;
                    txtRrsContri.setError("EnterRRSP Contributed");
                }
                if(!validateSinNumb(txtSin.getText().toString()))
                {

                    txtSin.setError("Enter a valid SIN number");
                }
                 else {

                    txtAge.setText(currentDate());
                     String dob = txtDob.getText().toString();
                     String taxFillDate = txtTaxFillDate.getText().toString();
                    Double grossIncome = Double.parseDouble(txtGrossIncome.getText().toString());
                    Double rrsp = Double.parseDouble(txtRrsContri.getText().toString());
                    CRACustomer customer = new CRACustomer(txtSin.getText().toString(),
                            txtFirstName.getText().toString(),
                            txtLastName.getText().toString(),
                            gender, grossIncome, rrsp);
                    Intent intent = new Intent(PersonInformationEntryActivity.this, TaxDataDetailsActivity.class);
                    intent.putExtra("CRACustomer", customer);
                    intent.putExtra("age", txtAge.getText().toString());
                    intent.putExtra("gender", gender);
                    intent.putExtra("dob",dob);
                    intent.putExtra("taxFillDate",taxFillDate);
                    startActivity(intent);
                }
            }
        });

    }

    public String currentDate(){
        StringBuilder todaydate=new StringBuilder();
        Calendar today=Calendar.getInstance();
        int age=today.get(Calendar.YEAR)-picker.getDatePicker().getYear();
        if (today.get(Calendar.MONTH) < picker.getDatePicker().getYear()) {
            age--;
        } else if (today.get(Calendar.MONTH) == picker.getDatePicker().getYear()
                && today.get(Calendar.DAY_OF_MONTH) < picker.getDatePicker().getYear()) {
            age--;
        }
        todaydate.append("Age: ");
        todaydate.append(String.valueOf(age));
        return todaydate.toString();
    }
    private void errorMsg(){
        Toast.makeText(PersonInformationEntryActivity.this, "Please enter details", Toast.LENGTH_LONG).show();
    }

    public boolean validateSinNumb(String s)
    {
        int n = 0;
        if(txtSin.getText().toString().matches("^(\\d{3}-\\d{3}-\\d{3})|(\\d{9})$"))
        {
            n = 1;
            return true;
        }
        if(n == 0)
        {
            return false;
        }
        return true;
    }
}
