package com.krishav.completesetup.completesetup.controllers;

import com.krishav.completesetup.completesetup.dto.EmployeeDTO;
import com.krishav.completesetup.completesetup.entities.User;
import com.krishav.completesetup.completesetup.exceptions.ResourceNotFoundException;
import com.krishav.completesetup.completesetup.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final  EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeID(@PathVariable Long employeeId)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("User {}" , user);
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);

        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for ID" + employeeId));
    }

    @PostMapping()
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO)
    {

            EmployeeDTO employee = employeeService.createEmployee(employeeDTO);

            return ResponseEntity.ok(employee);

    }



}
