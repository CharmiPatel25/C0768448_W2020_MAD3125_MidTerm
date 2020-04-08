package com.example.c0768448_w2020_mad3125_midterm.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class CRACustomer implements Parcelable
{
    String sin_number;
    String first_name;
    String  last_name;
    String full_name;
    String gender;
    Date dob,filingDate;
    double fed_Tax, prov_Tax;
    double rrsp_CarryForward;
    double grossIncome;
    double rrsp_contri;
    double EI;
    double total_taxable_amount;
    double total_tax_paid;

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
        return full_name;
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
}
