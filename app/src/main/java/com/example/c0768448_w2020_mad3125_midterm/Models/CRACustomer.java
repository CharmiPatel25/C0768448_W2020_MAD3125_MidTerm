package com.example.c0768448_w2020_mad3125_midterm.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class CRACustomer implements Parcelable
{
    private String sin_number;
    private String first_name;
    private String  last_name;
    private String full_name;
    private  String gender;
    private Date dob,filingDate;
    private  double fed_Tax, prov_Tax;
    private  double rrsp_CarryForward;
    private  double grossIncome;
    private double rrsp_contri;
    private double EI;
    private double total_taxable_amount;
    private double total_tax_paid;

    public CRACustomer(String sinNumber, String firstName,
                       String lastName, String gender, double grossIncome, double rrspContri)
    {
        this.sin_number = sinNumber;
        this.first_name = firstName;
        this.last_name = lastName;
        this.gender = gender;
        this.grossIncome = grossIncome;
        this.rrsp_contri = rrspContri;
    }

    public String getSin_number() {
        return sin_number;
    }

    public void setSin_number(String sin_number) {
        this.sin_number = sin_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFull_name() {
        return last_name.toUpperCase() + ", " +
                first_name.substring(0,1).toUpperCase() + first_name.substring(1);
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public double getFed_Tax() {
        return fed_Tax;
    }

    public void setFed_Tax(double fed_Tax) {
        this.fed_Tax = fed_Tax;
    }

    public double getProv_Tax() {
        return prov_Tax;
    }

    public void setProv_Tax(double prov_Tax) {
        this.prov_Tax = prov_Tax;
    }

    public double getRrsp_CarryForward() {
        return rrsp_CarryForward;
    }

    public void setRrsp_CarryForward(double rrsp_CarryForward) {
        this.rrsp_CarryForward = rrsp_CarryForward;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getRrsp_contri() {
        return rrsp_contri;
    }

    public void setRrsp_contri(double rrsp_contri) {
        this.rrsp_contri = rrsp_contri;
    }

    public double getEI() {
        return EI;
    }

    public void setEI(double EI) {
        this.EI = EI;
    }

    public double getTotal_taxable_amount() {
        return total_taxable_amount;
    }

    public void setTotal_taxable_amount(double total_taxable_amount) {
        this.total_taxable_amount = total_taxable_amount;
    }

    public double getTotal_tax_paid() {
        return total_tax_paid;
    }

    public void setTotal_tax_paid(double total_tax_paid) {
        this.total_tax_paid = total_tax_paid;
    }

    protected CRACustomer(Parcel in) {
        sin_number = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        full_name = in.readString();
        gender = in.readString();
        fed_Tax = in.readDouble();
        prov_Tax = in.readDouble();
        rrsp_CarryForward = in.readDouble();
        grossIncome = in.readDouble();
        rrsp_contri = in.readDouble();
        EI = in.readDouble();
        total_taxable_amount = in.readDouble();
        total_tax_paid = in.readDouble();
    }

    public static final Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel in) {
            return new CRACustomer(in);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sin_number);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(full_name);
        dest.writeString(gender);
        dest.writeDouble(fed_Tax);
        dest.writeDouble(prov_Tax);
        dest.writeDouble(rrsp_CarryForward);
        dest.writeDouble(grossIncome);
        dest.writeDouble(rrsp_contri);
        dest.writeDouble(EI);
        dest.writeDouble(total_taxable_amount);
        dest.writeDouble(total_tax_paid);
    }


    // Calculating Federal tax
    public double federalTax(){
        double fed_tax=0.0;

        double first_slab_perc=15.00;
        double first_slab=35561;

        double second_slab_perc=20.50;
        double second_slab=47628.99;

        double third_slab_perc=26.00;
        double third_slab=52407.99;

        double fourth_slab_perc=29.00;
        double fourth_slab=60703.99;

        double final_slab=0.01;
        double final_slab_perc=33.00;
        total_taxable_amount=total_taxable_amount-12069.00;
        if(total_taxable_amount>=first_slab) {
            fed_tax = (first_slab * first_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - first_slab;
        }

        if(total_taxable_amount>=second_slab) {
            fed_tax = (second_slab * second_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - second_slab;
        }
        if(total_taxable_amount>=third_slab) {
            fed_tax = (third_slab * third_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - third_slab;
        }
        if(total_taxable_amount>=fourth_slab) {
            fed_tax = (fourth_slab * fourth_slab_perc) / 100;
            total_taxable_amount = total_taxable_amount - fourth_slab;
        }
        if(total_taxable_amount>=final_slab) {
            fed_tax=(final_slab * final_slab_perc)/100;
        }
        return fed_tax;
    }


    // calculating RRSP amount
    public double rrspAmount(){
        double rrsp_perc=18.00;
        double actual_rrsp=(grossIncome*rrsp_perc)/100;
        return actual_rrsp;
    }

    public String amountFormatter()
    {
        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
        String val = nf.format("$"+this);

        return val;

    }
}
