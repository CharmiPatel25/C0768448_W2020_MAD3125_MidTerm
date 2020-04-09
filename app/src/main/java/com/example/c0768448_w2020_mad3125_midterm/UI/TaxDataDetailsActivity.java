package com.example.c0768448_w2020_mad3125_midterm.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.c0768448_w2020_mad3125_midterm.Models.CRACustomer;
import com.example.c0768448_w2020_mad3125_midterm.R;

public class TaxDataDetailsActivity extends AppCompatActivity {
    CRACustomer customer;
    private TextView lblSin,lblfullName,lblBirthDate,lblGender,lblAge,lblTaxFillingDate,lblGrossIncome,lblRRsContri,lblRrsCarry,lblFederal,lblProvincial,lblEi,
    lblCpp,lblTaxableIncome,lblTaxPaid;

    double cpp = 0, ei = 0;  double rrsp = 0, rrspCf = 0, taxableIncome, federalTax,
            provincialTax, totalTaxPaid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_data_details);
        lblSin = findViewById(R.id.lblSinNumber);
        lblfullName = findViewById(R.id.lblFullName);
        lblGender =   findViewById(R.id.lblGender);
        lblGrossIncome = findViewById(R.id.lblGrossIncome);
        lblRRsContri = findViewById(R.id.lblRrsContributed);
        lblCpp = findViewById(R.id.lblCpp);
        lblEi = findViewById(R.id.lblEi);
        lblRrsCarry = findViewById(R.id.lblCarryRrs);
        lblTaxableIncome = findViewById(R.id.lblTotalTaxIncome);
        lblFederal = findViewById(R.id.lblFederalTax);
        lblProvincial = findViewById(R.id.lblProvincialTax);
        lblTaxPaid = findViewById(R.id.lblTaxPayed);
        lblAge = findViewById(R.id.lblAge);
      //  lblAge.setText(getIntent().getStringExtra("age"));
        lblTaxFillingDate = findViewById(R.id.lblTaxFillDate);
        lblBirthDate = findViewById(R.id.lblBirthDate);

    }
}
