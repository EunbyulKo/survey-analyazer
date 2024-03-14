package org.silverstar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.silverstar.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ResourceInspectResultServiceTest {

    private final ResourceInspectResultService resourceInspectResultService = new ResourceInspectResultService();

    @Test
    @DisplayName("실패 - checkNoResponseResult - 파라미터 null일때")
    void fail_checkNoResponseResult_파라미터가_null_일때() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectResultService.checkNoResponseResult(null);
        });
    }

    @Test
    @DisplayName("성공 - checkNoResponseResult - 파라미터 emptylist일때")
    void success_checkNoResponseResult_파라미터가_emptylist_일때() {
        Assertions.assertEquals(0,
                resourceInspectResultService.checkNoResponseResult(Collections.emptyList()));
    }

    @Test
    @DisplayName("성공 - checkNoResponseResult - 응답값이 없을때")
    void success_checkNoResponseResult_응답값이_없을때() {
        List<SurveyResult> list = new ArrayList<>();
        list.add(SurveyResult.builder().responses(Collections.emptyList()).build());

        Assertions.assertEquals(1,
                resourceInspectResultService.checkNoResponseResult(list));

    }

    @Test
    @DisplayName("성공 - checkNoResponseResult - 응답값이 있을때")
    void success_checkNoResponseResult_응답값이_있을때() {
        List<SurveyResult> list = new ArrayList<>();
        list.add(SurveyResult.builder().responses(List.of(1)).build());

        Assertions.assertEquals(0,
                resourceInspectResultService.checkNoResponseResult(list));

    }

    @Test
    @DisplayName("실패 - checkAnswerAndResult - 파라미터 null일때")
    void fail_checkAnswerAndResult_파라미터가_null_일때() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectResultService.checkAnswerAndResult(null, null);
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectResultService.checkAnswerAndResult(Collections.emptyList(), null);
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectResultService.checkAnswerAndResult(null, Collections.emptyList());
        });
    }

    @Test
    @DisplayName("성공 - checkAnswerAndResult - 파라미터 emptylist일때")
    void success_checkAnswerAndResult_파라미터가_emptylist_일때() {
        Assertions.assertEquals(0,
                resourceInspectResultService.checkAnswerAndResult(Collections.emptyList(), Collections.emptyList()));
    }

    @Test
    @DisplayName("성공 - checkAnswerAndResult - 모든 survey id가 유효할 때")
    void success_checkAnswerAndResult_모두유효() {
        SurveyQuestionAnswerId id1 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(1).questionId(1).answerId(1).build();

        List<SurveyQuestionAnswer> answers = new ArrayList<>();
        answers.add(SurveyQuestionAnswer.builder().id(id1).build());

        SurveyQuestionAnswerId id2 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(1).questionId(1).answerId(1).build();

        List<SurveyResult> results = new ArrayList<>();
        results.add(SurveyResult.builder().id(id2).responses(List.of(1)).build());

        Assertions.assertEquals(0,
                resourceInspectResultService.checkAnswerAndResult(answers, results));

    }

    @Test
    @DisplayName("성공 - checkAnswerAndResult - 유효하지 않은 survey id가 있을 때")
    void success_checkAnswerAndResult_유효하지않은_id가_있을때() {
        SurveyQuestionAnswerId id1 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(1).questionId(1).answerId(1).build();

        List<SurveyQuestionAnswer> answers = new ArrayList<>();
        answers.add(SurveyQuestionAnswer.builder().id(id1).build());

        SurveyQuestionAnswerId id2 = SurveyQuestionAnswerId.builder().surveyId(1).groupId(2).questionId(1).answerId(1).build();

        List<SurveyResult> results = new ArrayList<>();
        results.add(SurveyResult.builder().id(id2).responses(List.of(1)).build());

        Assertions.assertEquals(1,
                resourceInspectResultService.checkAnswerAndResult(answers, results));

    }

}