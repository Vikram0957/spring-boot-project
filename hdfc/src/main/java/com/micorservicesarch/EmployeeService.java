package com.micorservicesarch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepo repo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;

    @Autowired
    private WebClient webClient;

    public Employee saveEmployee(Employee employee){
        Employee save = repo.save(employee);
        return save;
    }

    //By using RestTemplet
    public ResponseEntity<Employee> saveEmplyeeDto(Employee employee){
        Employee e = restTemplate.postForObject("localhost:8081/save", employee, Employee.class);
        return ResponseEntity.ok(e);
    }

    //By using RestClient
    public Employee getEmployee(Long id) {
        logger.trace("Employee Id :{}", id);
        return restClient
                .get()
                .uri("/employees/{id}", id)
                .retrieve()
                .body(Employee.class);
    }

    // By using WebClient
    public Mono<Employee> saveMonoEmployee(Employee employee) {

        return webClient
                .post()
                .uri("http://localhost:8081/save")
                .bodyValue(employee)
                .retrieve()
                .bodyToMono(Employee.class)
                .onErrorResume(error ->
                        Mono.just(
                                new Employee(
                                        0,
                                        "Unknown"
                                )
                        )
                );
    }

    public Flux<Employee> getAllEmployee(){
        Flux<Employee> employees = webClient
                .get()
                .uri("localhost:8081/get")
                .retrieve()
                .bodyToFlux(Employee.class);
        return employees;
    }

    //Using HttpClient
    public ResponseEntity<Employee> saveEmployeeWithHttpClient(Employee employee) {

        try {
            HttpClient httpClient = HttpClient.newHttpClient();

            ObjectMapper objectMapper = new ObjectMapper();

            // Employee object -> JSON
            String employeeJson = objectMapper.writeValueAsString(employee);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/save"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(employeeJson))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );

            // JSON response -> Employee object
            Employee savedEmployee =
                    objectMapper.readValue(response.body(), Employee.class);

            return ResponseEntity
                    .status(response.statusCode())
                    .body(savedEmployee);

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}
