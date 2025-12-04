package com.pluralsight.model;

import java.util.ArrayList;

public class Dealership {
    private String name,address,phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }
    public ArrayList<Vehicle>getVehiclesByPrice(double min, double max){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (min<= v.getPrice() && max>= v.getPrice()) {
                results.add(v);
            }
        }
        return results;
    }
    public ArrayList<Vehicle>getVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getMake().toLowerCase().contains(make.toLowerCase())&&v.getModel().toLowerCase().contains(model.toLowerCase())) {
                results.add(v);
            }
        }
        return results;
    }
    public ArrayList<Vehicle>getVehiclesByYear(int min, int max){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (min<= v.getYear() && max>= v.getYear()) {
                results.add(v);
            }
        }
        return results;
    }
    public ArrayList<Vehicle>getVehiclesByColor(String color){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().toLowerCase().contains(color.toLowerCase())) {
                results.add(v);
            }
        }
        return results;
    }
    public ArrayList<Vehicle>getVehiclesByMileage(int min, int max){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (min<= v.getOdometer() && max>= v.getOdometer()) {
                results.add(v);
            }
        }
        return results;
    }
    public ArrayList<Vehicle>getVehiclesByType(String vehicleType){
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVehicleType().toLowerCase().contains(vehicleType.toLowerCase())) {
                results.add(v);
            }
        }
        return results;
    }
    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
