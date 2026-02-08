package com.threading.enhanced;

import com.threading.SalesData;
import com.threading.SalesDataRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class EnhancedCsvImportService {


    private final SalesDataRepository repository;
    private static final int BATCH_SIZE = 1000;
    private static final int THREAD_POOL_SIZE = 10;

    public String importCsv(String filePath) {
        long startTime = System.currentTimeMillis();

        try (
                Reader reader = Files.newBufferedReader(Path.of(filePath));
                CSVParser csvParser = new CSVParser(
                        reader,
                        CSVFormat.DEFAULT
                                .withFirstRecordAsHeader()
                                .withTrim()
                )
        ) {
            // Read all records first
            List<SalesData> allData = new ArrayList<>();
            for (CSVRecord record : csvParser) {
                SalesData data = mapRecordToEntity(record);
                allData.add(data);
            }

            System.out.println("Total records read: " + allData.size());

            // Clear existing data
            System.out.println("Deleting all records");
            //repository.deleteAll();
            //repository.deleteAllInBatch();
            clearTable();
            System.out.println("All records deleted!");

            // Process in batches using ThreadPool
            System.out.println("Starting batch insert with " + THREAD_POOL_SIZE + " threads");
            processBatchesInParallel(allData);

            long endTime = System.currentTimeMillis();
            System.out.println("CSV import completed in " + (endTime - startTime) / 1000.0 + " seconds");

        } catch (Exception e) {
            throw new RuntimeException("Failed to import CSV", e);
        }

        return "Done!";
    }

    private void processBatchesInParallel(List<SalesData> allData) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future<Integer>> futures = new ArrayList<>();

        // Divide data into batches
        for (int i = 0; i < allData.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, allData.size());
            List<SalesData> batch = allData.subList(i, end);

            // Submit batch processing task
            Callable<Integer> task = new BatchInsertTask(batch, repository);
            Future<Integer> future = executorService.submit(task);
            futures.add(future);
        }

        // Wait for all tasks to complete and collect results
        int totalSaved = 0;
        for (Future<Integer> future : futures) {
            totalSaved += future.get();
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("Total records saved: " + totalSaved);
    }

    private SalesData mapRecordToEntity(CSVRecord record) {
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
        return data;
    }

    @jakarta.transaction.Transactional
    public void clearTable() {
        try {
            repository.truncateTable(); // native truncate
        } catch (Exception e) {
            repository.deleteAllInBatch(); // safe fallback
        }
    }

    // Inner class for batch processing task
    private static class BatchInsertTask implements Callable<Integer> {
        private final List<SalesData> batch;
        private final SalesDataRepository repository;

        public BatchInsertTask(List<SalesData> batch, SalesDataRepository repository) {
            this.batch = batch;
            this.repository = repository;
        }

        @Override
        @Transactional
        public Integer call() {
            try {
                List<SalesData> saved = repository.saveAll(batch);
                System.out.println("Thread " + Thread.currentThread().getName() +
                        " saved " + saved.size() + " records");
                return saved.size();
            } catch (Exception e) {
                System.err.println("Error in thread " + Thread.currentThread().getName() + ": " + e.getMessage());
                throw new RuntimeException("Batch insert failed", e);
            }
        }
    }
}
