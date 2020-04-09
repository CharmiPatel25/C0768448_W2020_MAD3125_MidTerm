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
    @SuppressLint("DefaultLocale")
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
        lblCpp.setText(String.format("%.2f", cpp));

        // calculate employement insurance
        if(grossIncome > 53100){
            ei = (53100 * 0.0162); //1.62%
        }else{
            ei = (grossIncome * (1.62/100));
        }
        lblEi.setText(String.format("%.2f", ei));

        // calculate RRSP
        rrsp = customer.getRrsp_contri();
        double maxRRSP = (grossIncome * 0.18); //18%

            rrspCf = maxRRSP - rrsp ;
            //rrsp = maxRRSP;

        if(rrspCf<0)
        {
            lblRrsCarry.setTextColor(Color.parseColor("#9c060b"));
            lblRrsCarry.setText(String.format("%.2f",rrspCf));
        }
      else
        {
            lblRrsCarry.setText(String.format("%.2f",rrspCf));
        }
        //taxable income
        taxableIncome = grossIncome - (cpp + ei + rrsp);

        lblTaxableIncome.setText(String.format("%.2f",taxableIncome));

        //federal tax
        double calFederal = calcFedralTax();
        lblFederal.setText( String.format("%.2f",calFederal));

        // Provincial Tax
        double calProvincial = calcProvincialTax();
        lblProvincial.setText(String.format("%.2f",calProvincial));

        //Total Tax Payed
        double taxpaid = calFederal+ calProvincial;

        lblTaxPaid.setText( String.format("%.2f",taxpaid));
    }
    public double calcFedralTax(){
        //calculate federal tax
        double temp = taxableIncome ;
        if(temp <= 12069.00){
            federalTax = 0;//0%
            temp = taxableIncome - 12069.00;
        }
        if(temp >= 12069.01){
            federalTax = (temp * 0.15);//15%
            temp = temp - 35561;
        }
        if(temp >= 47630.01){
            federalTax = (temp * 0.205); //20.50%
            temp = temp - 47628.99;
        }
        if(temp >= 95259.01){
            federalTax = (temp * 0.26); //26%
            temp = temp - 52407.99;
        }
        if (temp >= 147667.01){
            federalTax = (temp * 0.29);//29%
            temp = temp - 62703.99;
        }
        if(temp >= 210371.01){
            federalTax = (temp * 0.33);//33%
            //temp = temp - federalTax;
        }
        return federalTax;
    }

    public  double calcProvincialTax(){
        //calculate provincial tax
        double temp = taxableIncome ;

        if(temp <= 10582.00){
            provincialTax = 0;
            temp = taxableIncome - 10582.00;
        }
        if(temp >= 10582.01){
            provincialTax = (temp * 0.0505); //5.05%
            temp = temp - 33323.99;
        }
        if(temp >= 43906.01){
            provincialTax = (temp * 0.0915); //9.15%
            temp = temp - 43906.99;
        }
        if(temp >= 87813.01){
            provincialTax = (temp * 0.1116); //11.16%
            temp = temp - 62187.99;
        }
        if (temp >= 150000.01){
            provincialTax = (temp * 0.1216);//12.16%
            temp = temp - 69999.99;
        }
        if(temp >= 220000.01){
            provincialTax = (temp * 0.1316);//13.16%

        }
        return provincialTax;
    }
}

