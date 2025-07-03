package com.example.customerapi.demo.controller;

import com.example.customerapi.demo.dto.CustomerDTO;
import com.example.customerapi.demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer created",
                            content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    @PostMapping
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO customer) {
        return ResponseEntity.ok(service.create(customer));
    }

    @Operation(summary = "Get a customer by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer found",
                            content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all customers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of customers",
                            content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Update an existing customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer updated",
                            content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @Valid @RequestBody CustomerDTO customer) {
        return service.update(id, customer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a customer",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Customer deleted"),
                    @ApiResponse(responseCode = "404", description = "Customer not found")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
