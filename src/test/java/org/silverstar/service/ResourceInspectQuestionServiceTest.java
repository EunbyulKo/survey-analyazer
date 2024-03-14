package org.silverstar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.silverstar.model.SurveyBase;
import org.silverstar.model.SurveyQuestion;
import org.silverstar.model.SurveyQuestionId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ResourceInspectQuestionServiceTest {

    private final ResourceInspectQuestionService resourceInspectQuestionService = new ResourceInspectQuestionService();

    @Test
    @DisplayName("실패 - checkDuplicateQuestion - 파라미터 null일때")
    void fail_checkDuplicateQuestion_파라미터가_null_일때() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectQuestionService.checkDuplicateQuestion(null);
        });
    }

    @Test
    @DisplayName("성공 - checkDuplicateQuestion - 파라미터 emptylist일때")
    void success_checkDuplicateQuestion_파라미터가_emptylist_일때() {
        Assertions.assertEquals(0,
                resourceInspectQuestionService.checkDuplicateQuestion(Collections.emptyList()));
    }

    @Test
    @DisplayName("성공 - checkDuplicateQuestion - 중복이 없을때")
    void success_checkDuplicateQuestion_중복이_없을때() {
        SurveyQuestionId id1 = SurveyQuestionId.builder().surveyId(1).groupId(1).questionId(1).build();
        SurveyQuestionId id2 = SurveyQuestionId.builder().surveyId(1).groupId(2).questionId(1).build();
        SurveyQuestionId id3 = SurveyQuestionId.builder().surveyId(1).groupId(1).questionId(2).build();

        List<SurveyQuestion> list = new ArrayList<>();
        list.add(SurveyQuestion.builder().id(id1).build());
        list.add(SurveyQuestion.builder().id(id2).build());
        list.add(SurveyQuestion.builder().id(id3).build());

        Assertions.assertEquals(0,
                resourceInspectQuestionService.checkDuplicateQuestion(list));

    }

    @Test
    @DisplayName("성공 - checkDuplicateQuestion - 중복이 있을때")
    void success_checkDuplicateQuestion_중복이_있을때() {
        SurveyQuestionId id1 = SurveyQuestionId.builder().surveyId(1).groupId(1).questionId(1).build();
        SurveyQuestionId id2 = SurveyQuestionId.builder().surveyId(1).groupId(2).questionId(1).build();
        SurveyQuestionId id3 = SurveyQuestionId.builder().surveyId(1).groupId(1).questionId(1).build();

        List<SurveyQuestion> list = new ArrayList<>();
        list.add(SurveyQuestion.builder().id(id1).build());
        list.add(SurveyQuestion.builder().id(id2).build());
        list.add(SurveyQuestion.builder().id(id3).build());

        Assertions.assertEquals(1,
                resourceInspectQuestionService.checkDuplicateQuestion(list));

    }

    @Test
    @DisplayName("실패 - checkSurveyAndQuestion - 파라미터 null일때")
    void fail_checkSurveyAndQuestion_파라미터가_null_일때() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectQuestionService.checkSurveyAndQuestion(null, null);
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectQuestionService.checkSurveyAndQuestion(Collections.emptyList(), null);
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectQuestionService.checkSurveyAndQuestion(null, Collections.emptyList());
        });
    }

    @Test
    @DisplayName("성공 - checkSurveyAndQuestion - 파라미터 emptylist일때")
    void success_checkSurveyAndQuestion_파라미터가_emptylist_일때() {
        Assertions.assertEquals(0,
                resourceInspectQuestionService.checkSurveyAndQuestion(Collections.emptyList(), Collections.emptyList()));
    }

    @Test
    @DisplayName("성공 - checkSurveyAndQuestion - 모든 survey id가 유효할 때")
    void success_checkSurveyAndQuestion_모두유효() {
        List<SurveyBase> surveys = new ArrayList<>();
        surveys.add(SurveyBase.builder().id(1).build());

        SurveyQuestionId id = SurveyQuestionId.builder().surveyId(1).groupId(1).questionId(1).build();

        List<SurveyQuestion> questions = new ArrayList<>();
        questions.add(SurveyQuestion.builder().id(id).build());

        Assertions.assertEquals(0,
                resourceInspectQuestionService.checkSurveyAndQuestion(surveys, questions));

    }

    @Test
    @DisplayName("성공 - checkSurveyAndQuestion - 유효하지 않은 survey id가 있을 때")
    void success_checkSurveyAndQuestion_유효하지않은_id가_있을때() {
        List<SurveyBase> surveys = new ArrayList<>();
        surveys.add(SurveyBase.builder().id(1).build());

        SurveyQuestionId id = SurveyQuestionId.builder().surveyId(4).groupId(1).questionId(1).build();

        List<SurveyQuestion> questions = new ArrayList<>();
        questions.add(SurveyQuestion.builder().id(id).build());

        Assertions.assertEquals(1,
                resourceInspectQuestionService.checkSurveyAndQuestion(surveys, questions));

    }

}