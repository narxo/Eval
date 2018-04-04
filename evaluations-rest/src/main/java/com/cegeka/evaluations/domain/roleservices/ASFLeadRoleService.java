package com.cegeka.evaluations.domain.roleservices;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.cegeka.evaluations.domain.employee.Role.*;

@Component
public class ASFLeadRoleService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Value("#{'${AsfLeads}'.split(',')}")
    private List<String> asfLeads;

    @Transactional
    public void setRoleForASFLeads() {
        asfLeads.stream()
                .map(userName -> employeeRepository.findByUserName(userName))
                .forEach(user -> user.setRole(ASFLead));
    }
}
