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
