package com.cegeka.evaluations.domain.builders;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import org.assertj.core.util.Lists;

import java.util.List;


public class QscoreDTOsWithListForTest {

    public static final String USER_NAME = "leka";
    public static List<QscoreDTO> qscoreDTOList = Lists.newArrayList();;
    private static Employee manager = EmployeeTestBuilder.anEmployee().build();
    private static Employee employee = EmployeeTestBuilder.anEmployee().withUserName(USER_NAME).withManager(manager).build();
    public static final QscoreDTO testQscoreDTOQ1 = new QscoreDTO(1, employee, manager, 2017, 1,3);
    public static final QscoreDTO testQscoreDTOQ2 = new QscoreDTO(2, employee, manager, 2017, 2,3);
    public static final QscoreDTO testQscoreDTOQ3 = new QscoreDTO(3, employee, manager, 2017, 3,3);
    public static final QscoreDTO testQscoreDTOQ4 = new QscoreDTO(4, employee, manager, 2017, 4,3);

    public static List<QscoreDTO> return4QuartersListforTest() {
        qscoreDTOList.add(testQscoreDTOQ1);
        qscoreDTOList.add(testQscoreDTOQ2);
        qscoreDTOList.add(testQscoreDTOQ3);
        qscoreDTOList.add(testQscoreDTOQ4);
        return qscoreDTOList;
    }

    public static List<QscoreDTO> return3QuadrimesterListforTest() {
        qscoreDTOList.add(testQscoreDTOQ1);
        qscoreDTOList.add(testQscoreDTOQ2);
        qscoreDTOList.add(testQscoreDTOQ3);
        return qscoreDTOList;
    }

}
