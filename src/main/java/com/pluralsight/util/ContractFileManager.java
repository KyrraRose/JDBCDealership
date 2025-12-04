package com.pluralsight.util;

import com.pluralsight.model.contract.Contract;
import com.pluralsight.model.contract.LeaseContract;
import com.pluralsight.model.contract.SalesContract;


import java.io.BufferedWriter;
import java.io.FileWriter;

public class ContractFileManager {
    public void saveContract(Contract contract){
        try {
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter("src/main/resources/contracts.csv", true));

            //CONTRACT_TYPE|DATE|CUSTOMER_NAME|CUSTOMER_EMAIL|VIN|YEAR|MAKE|MODEL|VEHICLE_TYPE|COLOR|ODOMETER|VEHICLE_PRICE|[contract-specific-fields]|TOTAL_PRICE|MONTHLY_PAYMENT
            //• SALE: SALES_TAX|RECORDING_FEE|PROCESSING_FEE|TOTAL_PRICE|FINANCE_OPTION|TOTAL_PRICE|MONTHLY_PAYMENT
            final String salesContractFormat = "SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f|%.2f";
            //• LEASE: EXPECTED_ENDING_VALUE|LEASE_FEE|TOTAL_PRICE|MONTHLY_PAYMENT
            final String leaseContractFormat = "LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f";

            if (contract instanceof SalesContract){
                String finance = "";
                if (((SalesContract) contract).isFinance()){
                    finance = "YES";
                }else{finance = "NO";}
                buffWriter.write(String.format(salesContractFormat,
                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicleSold().getVin(),
                        contract.getVehicleSold().getYear(),
                        contract.getVehicleSold().getMake(),
                        contract.getVehicleSold().getModel(),
                        contract.getVehicleSold().getVehicleType(),
                        ((SalesContract) contract).getSalesTax(),
                        ((SalesContract) contract).getRecordingFee(),
                        ((SalesContract) contract).getProcessingFee(),
                        finance,((SalesContract) contract).getTotalPrice(),
                        ((SalesContract) contract).getMonthlyPayment()));
                buffWriter.newLine();
            }else if (contract instanceof LeaseContract) {
                buffWriter.write(String.format(leaseContractFormat,
                        contract.getDate(), contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getVehicleSold().getVin(),
                        contract.getVehicleSold().getYear(),
                        contract.getVehicleSold().getMake(),
                        contract.getVehicleSold().getModel(),
                        contract.getVehicleSold().getVehicleType(),
                        ((LeaseContract) contract).getExpectedEndValue(),
                        ((LeaseContract) contract).getLeaseFee(),
                        ((LeaseContract) contract).getTotalPrice(),
                        ((LeaseContract) contract).getMonthlyPayment()));
                buffWriter.newLine();
            }

            buffWriter.close();
        }catch(
        Exception e)

        {
            System.out.println(e.getMessage());
        }
}
}
