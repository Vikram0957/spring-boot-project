package com.threading;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvImportService {

    private final SalesDataRepository repository;

    public String importCsv(String filePath) {

        try (
                Reader reader = Files.newBufferedReader(Path.of(filePath));
                CSVParser csvParser = new CSVParser(
                    reader,
                    CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .withTrim()
            )
        ) {

            List<SalesData> dataList = new ArrayList<>();

            for (CSVRecord record : csvParser) {
                SalesData data = new SalesData();

                data.setRegion(record.get("Region"));
                data.setCountry(record.get("Country"));
                data.setItemType(record.get("Item Type"));
                data.setSalesChannel(record.get("Sales Channel"));
                data.setOrderPriority(record.get("Order Priority"));
                data.setOrderDate(record.get("Order Date"));
                data.setOrderId(record.get("Order ID"));
                data.setShipDate(record.get("Ship Date"));
                data.setUnitsSold(record.get("Units Sold"));
                data.setUnitPrice(record.get("Unit Price"));
                data.setUnitCost(record.get("Unit Cost"));
                data.setTotalRevenue(record.get("Total Revenue"));
                data.setTotalCost(record.get("Total Cost"));
                data.setTotalProfit(record.get("Total Profit"));

                dataList.add(data);
            }
            System.out.println("Deleting all records");
            clearTable();
            System.out.println("All records Deleted!\nInserting new records!");
            repository.saveAll(dataList);
            System.out.println("CSV data saved successfully!");

        } catch (Exception e) {
            throw new RuntimeException("Failed to import CSV", e);
        }
        return "Done!";
    }

    @Transactional
    public void clearTable() {
        try {
            repository.truncateTable(); // native truncate
        } catch (Exception e) {
            repository.deleteAllInBatch(); // safe fallback
        }
    }
}
