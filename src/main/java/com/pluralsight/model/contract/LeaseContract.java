package com.pluralsight.model.contract;

import com.pluralsight.model.Vehicle;

public class LeaseContract extends Contract{
    private int leaseId,leasePeriod;
    private double expectedEndValue,leaseFee;
    private String leaseEnds;


    public LeaseContract(int leaseId, String date, String leaseEnds,int leasePeriod,String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.leaseId = leaseId;
        this.leasePeriod = leasePeriod;
        this.leaseEnds = leaseEnds;
        this.expectedEndValue = this.vehicleSold.getPrice()/2;
        this.leaseFee = this.vehicleSold.getPrice()*.07;
    }

    public double getExpectedEndValue() {
        return expectedEndValue;
    }

    public void setExpectedEndValue(double expectedEndValue) {
        this.expectedEndValue = expectedEndValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return this.expectedEndValue + this.leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double interestRate = .04, length = 36;
        return  getTotalPrice() * (interestRate * (Math.pow((1 + interestRate), length) / (Math.pow((1 + interestRate), length) - 1)));
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public int getLeasePeriod() {
        return leasePeriod;
    }

    public void setLeasePeriod(int leasePeriod) {
        this.leasePeriod = leasePeriod;
    }

    public String getLeaseEnds() {
        return leaseEnds;
    }

    public void setLeaseEnds(String leaseEnds) {
        this.leaseEnds = leaseEnds;
    }
}
