package com.pluralsight.data;

import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private static DataManager dataManager = null;

    public VehicleDao(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void addVehicle(Vehicle vehicle){
        String insert = "INSERT INTO vehicles (vin,vehicle_year,make,model,vehicle_type,color,odometer,price,sold) VALUES (?,?,?,?,?,?,?,?,?) ";
        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(insert);){

            statement.setString(1, vehicle.getVin());
            statement.setInt(2, vehicle.getYear());
            statement.setString(3, vehicle.getMake());
            statement.setString(4, vehicle.getModel());
            statement.setString(5, vehicle.getVehicleType());
            statement.setString(6, vehicle.getColor());
            statement.setInt(7, vehicle.getOdometer());
            statement.setDouble(8, vehicle.getPrice());
            statement.setBoolean(9, vehicle.isSold());

            statement.executeUpdate();
        }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeVehicle(String vin){
        String insert = "DELETE FROM vehicles WHERE vin = ?";
        try {
            Connection connection = dataManager.getConnection();

            try(PreparedStatement statement = connection.prepareStatement(insert)) {

                statement.setString(1,vin);


                statement.executeUpdate();

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public ArrayList<Vehicle> loadVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet results = statement.executeQuery()) {
                    while (results.next()) {

                        vehicles.add(new Vehicle(
                               results.getString("vin"),
                               results.getInt("vehicle_year"),
                               results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicle_type"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price"),
                                results.getBoolean("sold")
                        ));
                    }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    public ArrayList<Vehicle> searchVehicleByPrice(double low,double high) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setDouble(1,low);
                statement.setDouble(2,high);

                try(ResultSet results = statement.executeQuery();) {
                    while (results.next()) {

                        vehicles.add(new Vehicle(
                                results.getString("vin"),
                                results.getInt("vehicle_year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicle_type"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price"),
                                results.getBoolean("sold")
                        ));
                    }
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    public ArrayList<Vehicle> searchVehicleByMakeModel(String make,String model) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE make LIKE ? or model LIKE ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1,"%"+make+"%");
                statement.setString(2,"%"+model+"%");

                try(ResultSet results = statement.executeQuery();) {
                    while (results.next()) {

                        vehicles.add(new Vehicle(
                                results.getString("vin"),
                                results.getInt("vehicle_year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicle_type"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price"),
                                results.getBoolean("sold")
                        ));
                    }
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    public ArrayList<Vehicle> searchVehicleByYear(int low,int high) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE vehicle_year BETWEEN ? AND ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1,low);
                statement.setInt(2,high);

                try(ResultSet results = statement.executeQuery();) {
                    while (results.next()) {

                        vehicles.add(new Vehicle(
                                results.getString("vin"),
                                results.getInt("vehicle_year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicle_type"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price"),
                                results.getBoolean("sold")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    public ArrayList<Vehicle> searchVehicleByColor(String input) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE color LIKE ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1,"%"+input+"%");

                try(ResultSet results = statement.executeQuery();) {
                    while (results.next()) {

                        vehicles.add(new Vehicle(
                                results.getString("vin"),
                                results.getInt("vehicle_year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicle_type"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price"),
                                results.getBoolean("sold")
                        ));
                    }
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    public ArrayList<Vehicle> searchVehicleByMileage(int low,int high) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setInt(1,low);
                statement.setInt(2,high);

                try(ResultSet results = statement.executeQuery();) {
                    while (results.next()) {

                        vehicles.add(new Vehicle(
                                results.getString("vin"),
                                results.getInt("vehicle_year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicle_type"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price"),
                                results.getBoolean("sold")
                        ));
                    }
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    public ArrayList<Vehicle> searchVehicleByType(String input) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE vehicle_type LIKE ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1,"%"+input+"%");

                try(ResultSet results = statement.executeQuery();) {
                    while (results.next()) {

                        vehicles.add(new Vehicle(
                                results.getString("vin"),
                                results.getInt("vehicle_year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicle_type"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price"),
                                results.getBoolean("sold")
                        ));
                    }
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    public static Vehicle getVehicle(String vin) {
        String query = "SELECT * FROM vehicles WHERE vin = ?";
        Vehicle vehicle = null;

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);){
                statement.setString(1,vin);

                try( ResultSet results = statement.executeQuery()) {
                    while (results.next()) {

                        vehicle = new Vehicle(
                                results.getString("vin"),
                                results.getInt("vehicle_year"),
                                results.getString("make"),
                                results.getString("model"),
                                results.getString("vehicle_type"),
                                results.getString("color"),
                                results.getInt("odometer"),
                                results.getDouble("price"),
                                results.getBoolean("sold")
                        );
                    }
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicle;
    }

}
