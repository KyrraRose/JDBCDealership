package com.pluralsight.ui;

import com.pluralsight.data.ContractDao;
import com.pluralsight.data.DataManager;
import com.pluralsight.data.DealershipDao;
import com.pluralsight.data.VehicleDao;
import com.pluralsight.model.*;
import com.pluralsight.model.contract.*;
import com.pluralsight.util.*;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.pluralsight.util.Utility.*;

public class UserInterface {
    private Dealership dealership;
    private final DataManager dm = new DataManager();
    private final VehicleDao vd = new VehicleDao(dm);
    public void display(){
        while(true){
            init();
            mainMenu();
        }

    }
    //helper init
    private void init(){
        System.out.print("\nWelcome to YOUR Dealership Manager\n");
        //DealershipFileManager fileManager = new DealershipFileManager();
        selectDealership();
    }

    private void selectDealership(){
        DealershipDao dealershipDao = new DealershipDao(dm);
        Menus.displayDealershipMenu(dealershipDao.loadDealerships());
        this.dealership = dealershipDao.loadDealership(getUserInt("Type Here: "));
    }
    //helper menu
    private void mainMenu(){
            Menus.displayMainMenu();
            //Get input
            switch (getUserInt("Type Here: ")) {
                case 1:
                    //display all
                    processGetAllVehiclesRequest();
                    break;
                case 2:
                    //filter search
                    filterMenu();
                    break;
                case 3:
                    //add/remove vehicle
                    editInventory();
                    break;
                case 4:
                    //contract
                    contractMenu();
                    break;
                case 5:
                    //exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Input not recognized. Please try again!");
            }

            waitAndContinue();


    }
    public void filterMenu(){

        boolean keepGoing = true;
        while (keepGoing) {

            Menus.displaySearchMenu();

            switch (getUserInt("Type Here: ")) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    keepGoing = false;
                    mainMenu();
                    break;
            }
        }
    }
    public void editInventory(){
        boolean keepGoing = true;
        while (keepGoing) {
            Menus.displayEditMenu();

            switch (getUserInt("Type Here: ")) {
                case 1:
                    processAddVehicleRequest();
                    break;
                case 2:
                    processRemoveVehicleRequest();
                    break;
                case 3:
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Input not recognized. Please try again!");

            }
        }

    }

    public void displayVehicles(ArrayList<Vehicle> list){
        for (Vehicle v : list) {
            if (!list.isEmpty()) {
                System.out.printf("%s|%d|%s|%s|%s|%s|%d|$%.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
            }
        }
    }

    public void processGetByPriceRequest(){

        System.out.print("Filter Search by Price:\n");
        double lowestPrice = getUserDouble("Lowest Price: $");

        double highestPrice = getUserDouble("Highest Price: $");

        System.out.printf("\nListing All Vehicles Between $%.2f and $%.2f:\n",lowestPrice,highestPrice);
        if (!vd.searchVehicleByPrice(lowestPrice,highestPrice).isEmpty()) {
            displayVehicles(vd.searchVehicleByPrice(lowestPrice, highestPrice));
        }else{
            System.out.println("No Results found. Please try again.");
        }

    }
    public void processGetByMakeModelRequest(){

        System.out.print("Filter Search by Make/Model:\n");
        String make = getUserString("Make: ");
        String model = getUserString("Model: ");

        System.out.printf("\nListing All %s %s:\n",make,model);
        if (!vd.searchVehicleByMakeModel(make,model).isEmpty()) {
            displayVehicles(vd.searchVehicleByMakeModel(make, model));
        }else{
            System.out.println("No Results found. Please try again.");
        }

    }
    public void processGetByYearRequest(){

        System.out.print("Filter Search by Year:\n");
        int min = getUserInt("Starting Year: ");

        int max = getUserInt("Ending Year: ");

        System.out.printf("\nListing All Vehicles Between %d and %d:\n",min,max);
        if (!vd.searchVehicleByYear(min,max).isEmpty()) {
            displayVehicles(vd.searchVehicleByYear(min,max));
        }else{
            System.out.println("No Results found. Please try again.");
        }

    }
    public void processGetByColorRequest(){

        System.out.print("Filter Search by Vehicle Color:\n");
        String color = getUserString("Color: ");

        System.out.printf("\nListing All %s Vehicles:\n",color);
        if (!vd.searchVehicleByColor(color).isEmpty()) {
            displayVehicles(vd.searchVehicleByColor(color));
        }else{
            System.out.println("No Results found. Please try again.");
        }

    }
    public void processGetByMileageRequest(){

        System.out.print("Filter Search by Mileage:\n");
        int min = getUserInt("Lowest Mileage: ");
        int max = getUserInt("Highest Mileage: ");

        System.out.printf("\nListing All Vehicles With Mileage Between %d and %d:\n",min,max);
        if (!vd.searchVehicleByMileage(min,max).isEmpty()) {
            displayVehicles(vd.searchVehicleByMileage(min,max));
        }else{
            System.out.println("No Results found. Please try again.");
        }

    }

    public void processGetByVehicleTypeRequest(){

        System.out.print("Filter Search by Vehicle Type:\n");
        String vehicleType = getUserString("Type: ");

        System.out.printf("\nListing All %s Vehicles:\n",vehicleType);
        if (!vd.searchVehicleByType(vehicleType).isEmpty()) {
            displayVehicles(vd.searchVehicleByType(vehicleType));
        }else{
            System.out.println("No Results found. Please try again.");
        }

    }
    public void processGetAllVehiclesRequest(){
        System.out.println("Listing All Vehicles:");
        //System.out.printf("%s|%s|%s\n",dealership.getName(),dealership.getAddress(),dealership.getPhone());
        displayVehicles(vd.loadVehicles());
    }
    public void processAddVehicleRequest(){
        DealershipFileManager fileManager = new DealershipFileManager();

        System.out.println("Add a Vehicle to Inventory");
        System.out.println("Please input vehicle details.");

        String vin = getUserString("VIN: ");

        int year = getUserInt("Year: ");

        String make = getUserString("Make: ");

        String model = getUserString("Model: ");

        String color = getUserString("Color: ");


        int odometer = getUserInt("Mileage: ");

        String type = getUserString("Type: ");

        double price = getUserDouble("Price: ");

        Vehicle vehicle = new Vehicle(vin,year,make,model,type,color,odometer,price,false);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(this.dealership);
        System.out.println("Vehicle added to inventory.");

    }
    public void processRemoveVehicleRequest(){
        Scanner scanner = new Scanner(System.in);
        DealershipFileManager fileManager = new DealershipFileManager();

        System.out.println("Remove a Vehicle from Inventory");
        System.out.println("Please input vehicle details.");

        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        for (Vehicle vehicle : dealership.getAllVehicles()){
            if (vehicle.getVin().equals(vin)){
                System.out.printf("%s %s with vin %d removed.",vehicle.getMake(),vehicle.getModel(),vehicle.getVin());
                dealership.removeVehicle(vehicle);
            }
        }

        fileManager.saveDealership(this.dealership);

    }

    public void contractMenu(){

        boolean keepGoing = true;
        while (keepGoing) {
            Menus.displayContractMenu();

            switch (getUserInt("Type Here: ")) {
                case 1:
                    processRecordSalesContractRequest();
                    break;
                case 2:
                    processRecordLeaseContractRequest();
                    break;
                case 3:
                    keepGoing = false;
                    break;
            }
        }
    }
    public void processRecordSalesContractRequest(){
        // initializing variables to use
        String customerName,customerEmail, customerFinance,isCorrect;
        boolean finance;
        Vehicle vehicleSold = null;
        ContractFileManager contractManager = new ContractFileManager();
        final String salesContractFormat = "SALE|%d|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f|%.2f";


        System.out.println("Record a Sales Contract");
        System.out.println("Please input customer details.");

        customerName = getUserString("Customer Name: ");

        customerEmail = getUserString("Customer Email: ");

        int vin = getUserInt("Vehicle VIN: ");

        for (Vehicle vehicle : dealership.getAllVehicles()){
            if (vehicle.getVin().equals(vin)){
                System.out.printf("%s %s with vin %d found.\n",vehicle.getMake(),vehicle.getModel(),vehicle.getVin());
                vehicleSold = vehicle;
            }
        }

        customerFinance = getUserString("Finance Loan (Y/N): ");

        if (customerFinance.contains("y")){
            finance = true;
            customerFinance = "YES";

        }else {
            finance = false;
            customerFinance = "NO";
        }
        ContractDao sc = new ContractDao(new DataManager());
        int salesID = 0;
        boolean check = true;
        while(check) {
            for (SalesContract s : sc.loadSalesContracts()) {
                if (s.getSalesId() > salesID) {
                    salesID = s.getSalesId();
                }else if (s.getSalesId()==salesID){
                    salesID += 1;
                }else{
                    check = false;
                }
            }
        }

        SalesContract contract = new SalesContract(salesID,todaysDate(),customerName,customerEmail,vehicleSold,finance);
        System.out.printf(salesContractFormat, contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(), contract.getVehicleSold().getVin(), contract.getVehicleSold().getYear(), contract.getVehicleSold().getMake(), contract.getVehicleSold().getModel(), contract.getVehicleSold().getVehicleType(),contract.getVehicleSold().getColor(),contract.getVehicleSold().getOdometer(),contract.getVehicleSold().getPrice(),contract.getSalesTax(),contract.getRecordingFee(),contract.getProcessingFee(), customerFinance,contract.getTotalPrice(),contract.getMonthlyPayment());
        System.out.println();
        System.out.print("Is this information correct?\n");
        if (getUserString("Type Here (Y/N): ").contains("y")){
            contractManager.saveContract(contract);
            System.out.println("Sales Contract Saved Successfully.");
        }else {
            System.out.println("Contract Information Incorrect. Exiting.");
        }

    }
    public void processRecordLeaseContractRequest(){
        // initializing variables to use
        Scanner scanner = new Scanner(System.in);
        String customerName,customerEmail,isCorrect;
        Vehicle vehicleSold = null;
        ContractFileManager contractManager = new ContractFileManager();
        final String leaseContractFormat = "LEASE|%d|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f";


        System.out.println("Record a Sales Contract");
        System.out.println("Please input customer details.");

        customerName = getUserString("Customer Name: ");

        customerEmail = getUserString("Customer Email: ");

        int vin = getUserInt("Vehicle VIN: ");

        int leasePeriod = getUserInt("Lease Period (months): ");


        ContractDao sc = new ContractDao(new DataManager());
        int leaseID = 0;
        boolean check = true;
        while(check) {
            for (LeaseContract s : sc.loadLeaseContracts()) {
                if (s.getLeaseId() > leaseID) {
                    leaseID = s.getLeaseId();
                }else if (s.getLeaseId()==leaseID){
                    leaseID += 1;
                }else{
                    check = false;
                }
            }
        }

        for (Vehicle vehicle : dealership.getAllVehicles()){
            if (vehicle.getVin().equals(vin)){
                System.out.printf("%s %s with vin %d found.",vehicle.getMake(),vehicle.getModel(),vehicle.getVin());
                vehicleSold = vehicle;
            }
        }


        LeaseContract contract = new LeaseContract(leaseID,todaysDate(),endLeaseDate(leasePeriod),leasePeriod,customerName,customerEmail,vehicleSold);
        System.out.printf(leaseContractFormat, contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(), contract.getVehicleSold().getVin(), contract.getVehicleSold().getYear(), contract.getVehicleSold().getMake(), contract.getVehicleSold().getModel(), contract.getVehicleSold().getVehicleType(),contract.getTotalPrice(),contract.getMonthlyPayment());
        System.out.println();
        System.out.print("Is this information correct?\nType Here (Y/N): ");
        isCorrect = scanner.nextLine().trim().toLowerCase();
        if (isCorrect.contains("y")){
            contractManager.saveContract(contract);
            System.out.println("Sales Contract Saved Successfully.");
        }else {
            System.out.println("Contract Information Incorrect. Exiting.");
        }
    }
    public String todaysDate(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return today.format(format);
    }
    public String endLeaseDate(int months){
        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return today.plusMonths(months).format(format);
    }
}
