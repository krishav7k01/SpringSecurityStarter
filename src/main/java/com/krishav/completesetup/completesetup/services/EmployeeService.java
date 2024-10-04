package com.krishav.completesetup.completesetup.services;

import com.krishav.completesetup.completesetup.dto.EmployeeDTO;
import com.krishav.completesetup.completesetup.entities.EmployeeEntity;
import com.krishav.completesetup.completesetup.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final  EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

  public Optional<EmployeeDTO> getEmployeeById(Long employeeId)
  {
      return employeeRepository.findById(employeeId).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
  }

  public  EmployeeDTO createEmployee(EmployeeDTO employeeDTO)
  {

      EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);

      EmployeeEntity createdEmployee = employeeRepository.save(employeeEntity);

      return modelMapper.map(createdEmployee, EmployeeDTO.class);
  }

}
