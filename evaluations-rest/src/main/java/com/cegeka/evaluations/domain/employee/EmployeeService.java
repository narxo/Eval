package com.cegeka.evaluations.domain.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDtoMapper employeeDtoMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getEmployeeDtoOfTeamMembers(String managerUserName) {
        Employee manager = employeeRepository.findByUserName(managerUserName.toLowerCase());
        return employeeRepository.findByManager(manager).stream()
                .map(employee -> employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(employee))
                .sorted(Comparator.comparing(EmployeeDTO::getRole).reversed()
                        .thenComparing(EmployeeDTO::getLastName))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeDtoByUsername(String userName) {
        Employee employee = employeeRepository.findByUserName(userName.toLowerCase());
        return employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployeesForAsfLead() {
        List<Employee> peopleManagers;
        List<Employee> portfolioManagers;
        List<EmployeeDTO> superList = new ArrayList<>();
        portfolioManagers = employeeRepository.findAllPortfolioManagers();
        for (Employee portfolioManager : portfolioManagers) {
            if (portfolioManager.getManager() == null) {
                EmployeeDTO portfolioManagerDto = employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(portfolioManager);
                superList.add(portfolioManagerDto);
            }
        }
        peopleManagers = employeeRepository.findByRolePeopleManager();
        for (Employee peopleManager : peopleManagers) {
            if (peopleManager.getManager() == null) {
                EmployeeDTO peopleManagerDto = employeeDtoMapper.mapEmployeeToLoggedInEmployeeDTO(peopleManager);
                superList.add(peopleManagerDto);
            }
        }
        return superList;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
}
