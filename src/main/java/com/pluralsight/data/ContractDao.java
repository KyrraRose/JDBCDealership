package com.pluralsight.data;
import com.pluralsight.model.Vehicle;
import com.pluralsight.model.contract.*;

import javax.swing.text.DateFormatter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContractDao {

    private final DataManager dataManager;

    public ContractDao(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<LeaseContract> loadLeaseContracts() {
        List<LeaseContract> contracts = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet results = statement.executeQuery()) {
                while (results.next()) {

                    contracts.add(new LeaseContract(
                            results.getInt("lease_id"),
                            results.getDate("date_leased").toString(),
                            results.getDate("date_ends").toString(),
                            results.getInt("leasePeriod"),
                            results.getString("customer_name"),
                            results.getString("customer_email"),
                            VehicleDao.getVehicle(results.getString("vin"))



                    ));
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return contracts;
    }

    public List<SalesContract> loadSalesContracts() {
        List<SalesContract> contracts = new ArrayList<>();
        String query = "SELECT * FROM sales_contracts";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet results = statement.executeQuery()) {
                while (results.next()) {

                    contracts.add(new SalesContract(
                            results.getInt("sales_id"),
                            results.getDate("date_sold").toString(),
                            results.getString("customer_name"),
                            results.getString("customer_email"),
                            VehicleDao.getVehicle(results.getString("vin")),
                            results.getBoolean("finance")
                    ));
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }

        return contracts;
    }

    public void addLeaseContract(LeaseContract lc){
        String insert = "INSERT INTO lease_contracts " +
                "(lease_id,date_leased,date_ends," +
                "lease_period, customer_name," +
                "customer_email,vin,expected_end_value) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            Connection connection = dataManager.getConnection();

            PreparedStatement statement = connection.prepareStatement(insert);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            statement.setInt(1, lc.getLeaseId());
            statement.setDate(2, (Date)format.parse(lc.getDate()));
            statement.setDate(3, (Date) format.parse(lc.getLeaseEnds()));
            statement.setInt(4,lc.getLeasePeriod());
            statement.setString(5,lc.getCustomerName());
            statement.setString(6,lc.getCustomerEmail());
            statement.setString(7,lc.getVehicleSold().getVin());
            statement.setDouble(8,lc.getExpectedEndValue());


            statement.executeUpdate();
            statement.close();

        } catch (SQLException | ParseException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addSalesContract(SalesContract sc){
        String insert = "INSERT INTO sales_contracts " +
                "(sales_id,date_sold, customer_name, customer_email, vin, price_sold, finance) " +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            Connection connection = dataManager.getConnection();

            PreparedStatement statement = connection.prepareStatement(insert);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            statement.setInt(1, sc.getSalesId());
            statement.setDate(2, (Date)format.parse(sc.getDate()));
            statement.setString(3,sc.getCustomerName());
            statement.setString(4,sc.getCustomerEmail());
            statement.setString(5,sc.getVehicleSold().getVin());
            statement.setDouble(6,sc.getTotalPrice());
            statement.setBoolean(7,sc.isFinance());


            statement.executeUpdate();
            statement.close();

        } catch (SQLException | ParseException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }
    }



}