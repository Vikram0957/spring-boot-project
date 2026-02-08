package com.threading.enhanced;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnhancedCsvImportServiceTest {
    @Autowired
    private EnhancedCsvImportService enhancedService;

    @Test
    void dumpDataInEnhancedWay() {

        long startTime = System.nanoTime();

        String path = "E:\\Practice\\Sales Records\\Sales Records.csv";
        String result = enhancedService.importCsv(path);

        long endTime = System.nanoTime();     // end

        long durationNanos = endTime - startTime;
        long durationSeconds = durationNanos / 1_000_000_000;
        long minutes = durationSeconds / 60;
        long seconds = durationSeconds % 60;

        System.out.println("Execution Time: " + minutes + " minutes " + seconds + " seconds");// 1 minutes 25 seconds

        assertEquals("Done!", result);
    }
}