package com.krishav.completesetup.completesetup.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private  Long id;
    private  String name;
    private  String email;
    private  Integer age;
    private LocalDate dateOfJoining;
    private String role;
    private Integer salary;

    @JsonProperty("isActive")
    private  Boolean isActive;

}
