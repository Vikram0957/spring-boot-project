package com.micorservicesarch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo repo;

    @Mock
    private RestTemplate restTemplate;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RestClient restClient;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private WebClient webClient;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(1, "Vikram");
    }

    // 1. Test saveEmployee() - success

    @Test
    void saveEmployee_shouldReturnSavedEmployee() {

        when(repo.save(employee)).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employee);

        assertNotNull(result);
        assertSame(employee, result);
        verify(repo, times(1)).save(employee);
    }

    // 2. Test saveEmplyeeDto() - success

    @Test
    void saveEmplyeeDto_shouldReturnEmployeeResponse() {

        when(restTemplate.postForObject(
                "localhost:8081/save",
                employee,
                Employee.class
        )).thenReturn(employee);

        ResponseEntity<Employee> response =
                employeeService.saveEmplyeeDto(employee);

        assertEquals(200, response.getStatusCode().value());
        assertSame(employee, response.getBody());

        verify(restTemplate).postForObject(
                "localhost:8081/save",
                employee,
                Employee.class
        );
    }

    // 3. Test getEmployee() - success

    @Test
    void getEmployee_shouldReturnEmployee() {

        when(restClient.get()
                .uri("/employees/{id}", 1L)
                .retrieve()
                .body(Employee.class))
                .thenReturn(employee);

        Employee result = employeeService.getEmployee(1L);

        assertNotNull(result);
        assertSame(employee, result);

        verify(restClient.get(), times(2))
                .uri("/employees/{id}", 1L);
    }

    // 4. Test saveMonoEmployee() - success

    @Test
    void saveMonoEmployee_shouldReturnSavedEmployee() {

        when(webClient.post()
                .uri("http://localhost:8081/save")
                .bodyValue(employee)
                .retrieve()
                .bodyToMono(Employee.class))
                .thenReturn(Mono.just(employee));

        Mono<Employee> result =
                employeeService.saveMonoEmployee(employee);

        StepVerifier.create(result)
                .expectNext(employee)
                .verifyComplete();
    }

    // 5. Test saveMonoEmployee() - error fallback

    @Test
    void saveMonoEmployee_shouldReturnUnknownEmployeeOnError() {

        when(webClient.post()
                .uri("http://localhost:8081/save")
                .bodyValue(employee)
                .retrieve()
                .bodyToMono(Employee.class))
                .thenReturn(Mono.error(
                        new RuntimeException("Service unavailable")
                ));

        Mono<Employee> result =
                employeeService.saveMonoEmployee(employee);

        StepVerifier.create(result)
                .assertNext(emp -> {
                    assertEquals(0, emp.id());
                    assertEquals("Unknown", emp.name());
                })
                .verifyComplete();
    }

    // 6. Test getAllEmployee() - employees found

    @Test
    void getAllEmployee_shouldReturnAllEmployees() {

        Employee employee2 = new Employee(2, "Rahul");

        when(webClient.get()
                .uri("localhost:8081/get")
                .retrieve()
                .bodyToFlux(Employee.class))
                .thenReturn(Flux.just(employee, employee2));

        Flux<Employee> result = employeeService.getAllEmployee();

        StepVerifier.create(result)
                .expectNext(employee)
                .expectNext(employee2)
                .verifyComplete();
    }

    // 7. Test getAllEmployee() - no employees

    @Test
    void getAllEmployee_shouldReturnEmptyFlux() {

        when(webClient.get()
                .uri("localhost:8081/get")
                .retrieve()
                .bodyToFlux(Employee.class))
                .thenReturn(Flux.empty());

        StepVerifier.create(employeeService.getAllEmployee())
                .verifyComplete();
    }

    // 8. Test saveEmployeeWithHttpClient() - exception handling

    @Test
    void saveEmployeeWithHttpClient_shouldReturn500OnFailure() throws IOException, InterruptedException {

        try (var mockedStatic = mockStatic(HttpClient.class)) {

            HttpClient httpClient = mock(HttpClient.class);

            mockedStatic.when(HttpClient::newHttpClient)
                    .thenReturn(httpClient);

            doThrow(new IOException("Connection failed"))
                    .when(httpClient)
                    .send(
                            any(HttpRequest.class),
                            any(HttpResponse.BodyHandler.class)
                    );

            ResponseEntity<Employee> response =
                    employeeService.saveEmployeeWithHttpClient(employee);

            assertEquals(500, response.getStatusCode().value());
            assertNull(response.getBody());
        }
    }
}