package com.krishav.completesetup.completesetup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krishav.completesetup.completesetup.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {


    private  Long id;

    @NotBlank(message = "Required field in Employee: name")
    @Size(min = 3, max = 10)
    private  String name;

    @NotBlank(message = "Email of the employee cannot be blank")
    @Email(message = "Email should be a valid email")
    private  String email;

    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 18, message = "Age of employee cannot be less than 18")
    private  Integer age;

    @NotBlank(message = "Employee role cannot be blank")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary of employee should be not null")
    @Positive(message = "Salary of employee should be positive")
    private Integer salary;

    @PastOrPresent(message = "DateofJoining field in employee cannot be in future")
    private  LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private  Boolean isActive;

}
