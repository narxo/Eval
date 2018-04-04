package com.cegeka.evaluations.application;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.employee.EmployeeService;
import com.cegeka.evaluations.domain.employee.EmployeeDTO;
import com.cegeka.evaluations.domain.qscores.QscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private QscoreService qscoreService;

    @Secured({"ROLE_PEOPLE_MANAGER","ROLE_PORTFOLIO_MANAGER"})
    @RequestMapping(value = "/teamMembers/{userName}", method = RequestMethod.GET, produces = "application/json")
    public List<EmployeeDTO> getEmployeeDtoOfTeamMembers(@PathVariable String userName) {
        return employeeService.getEmployeeDtoOfTeamMembers(userName);
    }

    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = "application/json")
    public EmployeeDTO getEmployeeDtoByUserName(@PathVariable String userName) {
        return employeeService.getEmployeeDtoByUsername(userName);
    }

    @Secured("ROLE_ASF_LEAD")
    @RequestMapping(value = "/employeesForAsfLead", method = RequestMethod.GET, produces = "application/json")
    public List<EmployeeDTO> getAllEmployeesForAsfLead(){
        return employeeService.getAllEmployeesForAsfLead();
    }

    @Secured("ROLE_ASF_LEAD")
    @RequestMapping(value = "/getCsv", method = RequestMethod.GET)
    public void getCsv(HttpServletResponse response) {
        List<Employee> employees = employeeService.getAllEmployees();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"overview\"");
        try {
            OutputStream outputStream = response.getOutputStream();
            StringBuilder sb = new StringBuilder();
            sb.append("Employee first name");
            sb.append(';');
            sb.append("Employee last name");
            sb.append(';');
            sb.append("Manager first name");
            sb.append(';');
            sb.append("Manager last name");
            sb.append(';');
            sb.append("Score");
            sb.append(';');
            sb.append("Comment");
            sb.append('\n');
            for (Employee e : employees) {
                sb.append(e.getFirstName());
                sb.append(';');
                sb.append(e.getLastName());
                sb.append(';');
                if(e.getManager()!=null) {
                    sb.append(e.getManager().getFirstName());
                    sb.append(';');
                    sb.append(e.getManager().getLastName());
                    sb.append(';');
                    sb.append(Integer.toString(qscoreService.findLastQscoreDTOByUserName(e.getUserName()).getScore()));
                    sb.append(';');
                    sb.append(qscoreService.findLastQscoreDTOByUserName(e.getUserName()).getComment());
                }
                else{
                    sb.append("");
                }
                sb.append('\n');
            }
            String outputResult = sb.toString();
            outputStream.write(outputResult.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
