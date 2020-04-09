package com.example.c0768448_w2020_mad3125_midterm.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.c0768448_w2020_mad3125_midterm.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PersonInformationEntryActivity extends AppCompatActivity {

    private EditText txtSin,txtFirstName,txtLastName,txtDob,txtTaxFillDate,txtGrossIncome,txtRrsContri;
    private  TextView txtAge;
    private RadioButton rdMr,rdMrs,rdOther;
    private RadioGroup rdGender;
    private Button btnCalculate;
    private   DatePickerDialog picker;
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

        //Birth Date Picker
        txtDob.setInputType(InputType.TYPE_NULL);
        txtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(PersonInformationEntryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {


                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }
}
