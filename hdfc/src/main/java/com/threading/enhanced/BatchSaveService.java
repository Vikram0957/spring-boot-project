package com.threading.enhanced;

import com.threading.SalesData;
import com.threading.SalesDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchSaveService {

    private final SalesDataRepository repository;

    @Transactional
    public void saveBatch(List<SalesData> batch) {
        repository.saveAll(batch);
    }
}
