package com.threading;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class CsvImportServiceTest {

    @Autowired
    private CsvImportService service;

    @Test
    @Disabled
    void dumpData() {

        long startTime = System.nanoTime();

        String path = "E:\\Practice\\Sales Records\\Sales Records.csv";
        String result = service.importCsv(path);

        long endTime = System.nanoTime();     // end

        long durationNanos = endTime - startTime;
        long durationSeconds = durationNanos / 1_000_000_000;
        long minutes = durationSeconds / 60;
        long seconds = durationSeconds % 60;

        System.out.println("Execution Time: " + minutes + " minutes " + seconds + " seconds");// 6 min 30 sec

        assertEquals("Done!", result);
    }
}