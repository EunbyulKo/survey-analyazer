package org.silverstar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.silverstar.model.SurveyQuestion;
import org.silverstar.model.SurveyQuestionAnswer;
import org.silverstar.model.SurveyQuestionAnswerId;
import org.silverstar.model.SurveyQuestionId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ResourceInspectAnswerServiceTest {

    private final ResourceInspectAnswerService resourceInspectAnswerService = new ResourceInspectAnswerService();

    @Test
    @DisplayName("실패 - checkDuplicateAnswer - 파라미터 null일때")
    void fail_checkDuplicateAnswer_파라미터가_null_일때() {
        Assertions.assertThrows(NullPointerException.class,
                () -> resourceInspectAnswerService.checkDuplicateAnswer(null));
    }

    @Test
    @DisplayName("성공 - checkDuplicateAnswer - 파라미터 emptylist일때")
    void success_checkDuplicateAnswer_파라미터가_emptylist_일때() {
        Assertions.assertEquals(0,
                resourceInspectAnswerService.checkDuplicateAnswer(Collections.emptyList()));
    }

    @Test
    @DisplayName("성공 - checkDuplicateAnswer - 중복이 없을때")
    void success_checkDuplicateAnswer_중복이_없을때() {
        SurveyQuestionAnswerId id1 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(1).questionId(1).answerId(1).build();
        SurveyQuestionAnswerId id2 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(2).questionId(1).answerId(1).build();
        SurveyQuestionAnswerId id3 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(1).questionId(2).answerId(1).build();

        List<SurveyQuestionAnswer> list = new ArrayList<>();
        list.add(SurveyQuestionAnswer.builder().id(id1).build());
        list.add(SurveyQuestionAnswer.builder().id(id2).build());
        list.add(SurveyQuestionAnswer.builder().id(id3).build());

        Assertions.assertEquals(0,
                resourceInspectAnswerService.checkDuplicateAnswer(list));

    }

    @Test
    @DisplayName("성공 - checkDuplicateQuestion - 중복이 있을때")
    void success_checkDuplicateQuestion_중복이_있을때() {
        SurveyQuestionAnswerId id1 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(1).questionId(1).answerId(1).build();
        SurveyQuestionAnswerId id2 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(2).questionId(1).answerId(1).build();
        SurveyQuestionAnswerId id3 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(1).questionId(1).answerId(1).build();

        List<SurveyQuestionAnswer> list = new ArrayList<>();
        list.add(SurveyQuestionAnswer.builder().id(id1).build());
        list.add(SurveyQuestionAnswer.builder().id(id2).build());
        list.add(SurveyQuestionAnswer.builder().id(id3).build());

        Assertions.assertEquals(1,
                resourceInspectAnswerService.checkDuplicateAnswer(list));

    }

    @Test
    @DisplayName("실패 - checkQuestionAndAnswer - 파라미터 null일때")
    void fail_checkQuestionAndAnswer_파라미터가_null_일때() {
        Assertions.assertThrows(NullPointerException.class,
                () -> resourceInspectAnswerService.checkQuestionAndAnswer(null, null));

        Assertions.assertThrows(NullPointerException.class,
                () -> resourceInspectAnswerService.checkQuestionAndAnswer(Collections.emptyList(), null));

        Assertions.assertThrows(NullPointerException.class,
                () -> resourceInspectAnswerService.checkQuestionAndAnswer(null, Collections.emptyList()));
    }

    @Test
    @DisplayName("성공 - checkQuestionAndAnswer - 파라미터 emptylist일때")
    void success_checkQuestionAndAnswer_파라미터가_emptylist_일때() {
        Assertions.assertEquals(0,
                resourceInspectAnswerService.checkQuestionAndAnswer(Collections.emptyList(), Collections.emptyList()));
    }

    @Test
    @DisplayName("성공 - checkQuestionAndAnswer - 모든 survey id가 유효할 때")
    void success_checkQuestionAndAnswer_모두유효() {
        SurveyQuestionId questionId = SurveyQuestionId.builder().surveyId(1).groupId(1).questionId(1).build();

        List<SurveyQuestion> questions = new ArrayList<>();
        questions.add(SurveyQuestion.builder().id(questionId).build());

        SurveyQuestionAnswerId answerId = SurveyQuestionAnswerId.builder().surveyId(1).groupId(1).questionId(1).answerId(1).build();

        List<SurveyQuestionAnswer> answers = new ArrayList<>();
        answers.add(SurveyQuestionAnswer.builder().id(answerId).build());

        Assertions.assertEquals(0,
                resourceInspectAnswerService.checkQuestionAndAnswer(questions, answers));

    }

    @Test
    @DisplayName("성공 - checkSurveyAndQuestion - 유효하지 않은 survey id가 있을 때")
    void success_checkSurveyAndQuestion_유효하지않은_id가_있을때() {
        SurveyQuestionId questionId = SurveyQuestionId.builder().surveyId(1).groupId(1).questionId(1).build();

        List<SurveyQuestion> questions = new ArrayList<>();
        questions.add(SurveyQuestion.builder().id(questionId).build());

        SurveyQuestionAnswerId answerId = SurveyQuestionAnswerId.builder().surveyId(1).groupId(2).questionId(1).answerId(1).build();

        List<SurveyQuestionAnswer> answers = new ArrayList<>();
        answers.add(SurveyQuestionAnswer.builder().id(answerId).build());

        Assertions.assertEquals(1,
                resourceInspectAnswerService.checkQuestionAndAnswer(questions, answers));

    }

}