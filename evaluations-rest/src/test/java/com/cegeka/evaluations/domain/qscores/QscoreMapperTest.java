package com.cegeka.evaluations.domain.qscores;

import com.cegeka.evaluations.domain.employee.Employee;
import com.cegeka.evaluations.domain.builders.EmployeeTestBuilder;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QscoreMapperTest {
    private Employee testEmployee, testManager;
    private Qscore testQscore;
    private QscoreMapper qscoreMapper;
    private QscoreDTO testQscoreDTO;

    @Before
    public void setUp() throws Exception {
        testManager = EmployeeTestBuilder.anEmployee().build();
        testEmployee = EmployeeTestBuilder.anEmployee().withManager(testManager).build();
        testQscore = new Qscore(testEmployee, testManager,2017,1,3);
        ReflectionTestUtils.setField(testQscore, "id", 1);
        qscoreMapper = new QscoreMapper();
        testQscoreDTO = new QscoreDTO(1,testEmployee,testManager,2017,1,3);
    }

    @Test
    public void mapQscoreToDTO_shouldReturnDTOWithSameValues() throws Exception {
        assertThat(qscoreMapper.mapQscoreToDTO(testQscore)).isEqualToComparingFieldByFieldRecursively(testQscoreDTO);
    }

    @Test
    public void mapQscoreListToDTOList_shouldReturnDTOListWithSameDTOs() throws Exception {

        List<Qscore> testQscoreList = Lists.newArrayList(testQscore);
        List<QscoreDTO> testQscoreDTOList = Lists.newArrayList(testQscoreDTO);

        assertThat(qscoreMapper.mapQscoreListToDTOList(testQscoreList).contains(testQscoreDTOList));
    }
}