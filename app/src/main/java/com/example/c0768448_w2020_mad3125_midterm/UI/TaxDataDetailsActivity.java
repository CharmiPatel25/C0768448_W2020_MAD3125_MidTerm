package com.example.c0768448_w2020_mad3125_midterm.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.c0768448_w2020_mad3125_midterm.Models.CRACustomer;
import com.example.c0768448_w2020_mad3125_midterm.R;

import java.text.NumberFormat;
import java.util.Locale;

public class TaxDataDetailsActivity extends AppCompatActivity {
    CRACustomer customer;
    private TextView lblSin,lblfullName,lblBirthDate,lblGender,lblAge,lblTaxFillingDate,lblGrossIncome,lblRRsContri,lblRrsCarry,lblFederal,lblProvincial,lblEi,
    lblCpp,lblTaxableIncome,lblTaxPaid;

    double cpp = 0, ei = 0;  double rrsp = 0, rrspCf = 0, taxableIncome, federalTax,
            provincialTax, totalTaxPaid;
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
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
    // lblAge.setText(getIntent().getStringExtra("age"));
        lblTaxFillingDate = findViewById(R.id.lblTaxFillDate);
        lblBirthDate = findViewById(R.id.lblBirthDate);


        Intent mIntent = getIntent();
        CRACustomer customer = mIntent.getParcelableExtra("CRACustomer");

        lblSin.setText( customer.getSin_number());
        lblfullName.setText( customer.getFull_name());
        lblGender.setText(getIntent().getStringExtra("gender"));
        lblGrossIncome.setText(String.valueOf(customer.getGrossIncome()));
        lblRRsContri.setText(String.valueOf(customer.getRrsp_contri()));


        // calculate  cpp
        double grossIncome = customer.getGrossIncome();
        if(grossIncome > 57400.00){
            cpp = (57400.00 * 0.051); //5.10%
        } else {
            cpp = (grossIncome * 0.051);
        }
        lblCpp.setText("$ " +String.format("%.2f", cpp));

        // calculate employement insurance
        if(grossIncome > 53100){
            ei = (53100 * 0.0162); //1.62%
        }else{
            ei = (grossIncome * (1.62/100));
        }
        lblEi.setText("$ " +String.format("%.2f", ei));

        // calculate RRSP
        rrsp = customer.getRrsp_contri();
        double maxRRSP = (grossIncome * 0.18); //18%

            rrspCf = maxRRSP - rrsp ;
            //rrsp = maxRRSP;

        if(rrspCf<0)
        {
            lblRrsCarry.setTextColor(Color.parseColor("#9c060b"));
            lblRrsCarry.setText("$ " +String.format("%.2f",rrspCf));
        }
      else
        {
            lblRrsCarry.setText("$ " +String.format("%.2f",rrspCf));
        }
        //taxable income
        taxableIncome = grossIncome - (cpp + ei + rrsp);

        lblTaxableIncome.setText("$ " +String.format("%.2f",taxableIncome));

        //federal tax
        double calFederal = calcFedralTax();
        lblFederal.setText( "$ " +String.format("%.2f",calFederal));

        // Provincial Tax
        double calProvincial = calcProvincialTax();
        lblProvincial.setText("$ " +String.format("%.2f",calProvincial));

        //Total Tax Payed
        double taxpaid = calFederal+ calProvincial;

        lblTaxPaid.setText( "$ " +String.format("%.2f",taxpaid));
    }
    public double calcFedralTax(){
        //calculate federal tax
        if(taxableIncome <=  12069.0d) {
            return 0.0d;
        }
        else if(taxableIncome >= 12069.0d && taxableIncome <= 47630.0d){
            return 0.15d;
        }
        else if(taxableIncome >= 47630.01d && taxableIncome <= 95259.0d){
            return 0.2050d;
        }
        else if(taxableIncome >= 95259.01d && taxableIncome <= 147667.0d){
            return 0.26d;
        }
        else if(taxableIncome >= 147667.01d && taxableIncome <= 210371.0d){
            return 0.29d;
        }
        else {
            return 0.33d;
        }
    }

    public  double calcProvincialTax(){
        //calculate provincial tax
        if(taxableIncome <= 10582.0d) {
            return 0.0d;
        }
        else if(taxableIncome >= 10582.0d && taxableIncome <=43906.0d)
        {
            return 0.0505d;
        }
        else if(taxableIncome >= 43906.0d && taxableIncome <= 87813.0d)
        {
            return 0.0915d;
        }
        else if (taxableIncome >= 87813.0d && taxableIncome <= 150000.0d) {
            return 0.1116d;
        }
        else if (taxableIncome >= 150000.0d && taxableIncome <= 220000.0d)
        {
            return 0.1216d;
        }
        else
        {
            return 0.1316d;
        }
    }
}

