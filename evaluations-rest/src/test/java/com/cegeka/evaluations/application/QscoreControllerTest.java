package com.cegeka.evaluations.application;

import com.cegeka.evaluations.domain.qscores.QscoreDTO;
import com.cegeka.evaluations.domain.qscores.QscoreService;
import com.cegeka.evaluations.domain.qscores.UpdateQscoreDTO;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QscoreControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private QscoreController qscoreController;

    @Mock
    private QscoreService qscoreService;
    private QscoreDTO qscoreDTO;
    private UpdateQscoreDTO updateQscoreDTO;
    //    private UpdateQscoreCommentDTO updateQscoreCommentDTO;
    private List<QscoreDTO> qscoreDTOList;

    @Before
    public void setUp() throws Exception {
        qscoreDTO = new QscoreDTO();
        qscoreDTOList = Lists.newArrayList(qscoreDTO);
    }

    @Test
    public void findQscoreByUsername_shouldReturnQscoreDTOList() throws Exception {
        when(qscoreService.findQScoresByUserName("username")).thenReturn(qscoreDTOList);
        Assertions.assertThat(qscoreController.findQscoresByUsername("username")).isEqualTo(qscoreDTOList);
    }

    @Test
    public void updateQscore() {
        qscoreController.updateQscore(updateQscoreDTO);
        verify(qscoreService).updateQscore(updateQscoreDTO);
    }
}

//    @Test
//    public void updateQscoreComment_ShouldInvokeQscoreService() throws Exception {
//        qscoreController.updateQscoreComment(updateQscoreCommentDTO);
//        verify(qscoreService).updateQscoreComment(updateQscoreCommentDTO);
//    }
//}