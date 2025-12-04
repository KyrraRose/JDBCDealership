package com.pluralsight.util;

import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;

import java.io.*;

public class DealershipFileManager {
public Dealership getDealership(){
    try {
        BufferedReader buffReader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));
        String[] header =buffReader.readLine().split("\\|");

        Dealership dealership = new Dealership(header[0],header[1],header[2]);

        String input;
        while((input = buffReader.readLine()) != null){
            String[] list = input.split("\\|");
            Vehicle vehicle = new Vehicle(Integer.parseInt(list[0]),Integer.parseInt(list[1]),list[2],list[3],list[4],list[5],Integer.parseInt(list[6]),Double.parseDouble(list[7]));
            dealership.addVehicle(vehicle);
        }
        return dealership;
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    return null;
}
public void saveDealership(Dealership dealership){
    try{
        BufferedWriter buffWriter = new BufferedWriter(new FileWriter("src/main/resources/inventory.csv"));
        buffWriter.write(String.format("%s|%s|%s",dealership.getName(),dealership.getAddress(),dealership.getPhone()));
        buffWriter.newLine();
        for (Vehicle v : dealership.getAllVehicles()){
            buffWriter.write(String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",v.getVin(),v.getYear(),v.getMake(),v.getModel(),v.getVehicleType(),v.getColor(),v.getOdometer(),v.getPrice()));
            buffWriter.newLine();
        }
        buffWriter.close();
    }catch (Exception e){
        System.out.println(e.getMessage());
    }

}
}
