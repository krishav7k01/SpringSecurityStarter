package com.krishav.completesetup.completesetup.utils;

import com.krishav.completesetup.completesetup.dto.EmployeeDTO;
import com.krishav.completesetup.completesetup.entities.EmployeeEntity;
import com.krishav.completesetup.completesetup.entities.User;
import com.krishav.completesetup.completesetup.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final EmployeeService employeeService;

    boolean isEmployeeOfDepartment(Long employeeId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(employeeId);

        return employee.map(employeeDTO ->
                employeeDTO.getUserDto().getId().equals(user.getId())
        ).orElse(false);

    }

}
