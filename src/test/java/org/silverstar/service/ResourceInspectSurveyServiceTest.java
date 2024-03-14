package org.silverstar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.silverstar.model.SurveyBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ResourceInspectSurveyServiceTest {

    private final ResourceInspectSurveyService resourceInspectSurveyService = new ResourceInspectSurveyService();

    @Test
    @DisplayName("실패 - checkDuplicateSurvey - 파라미터 null일때")
    void fail_checkDuplicateSurvey_파라미터가_null_일때() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            resourceInspectSurveyService.checkDuplicateSurvey(null);
        });
    }

    @Test
    @DisplayName("성공 - checkDuplicateSurvey - 파라미터 emptylist일때")
    void success_checkDuplicateSurvey_파라미터가_emptylist_일때() {
        Assertions.assertEquals(0,
                resourceInspectSurveyService.checkDuplicateSurvey(Collections.emptyList()));
    }

    @Test
    @DisplayName("성공 - checkDuplicateSurvey - 중복이 없을때")
    void success_checkDuplicateSurvey_중복이_없을때() {
        List<SurveyBase> list = new ArrayList<>();
        list.add(SurveyBase.builder().id(1).build());
        list.add(SurveyBase.builder().id(2).build());
        list.add(SurveyBase.builder().id(3).build());

        Assertions.assertEquals(0,
                resourceInspectSurveyService.checkDuplicateSurvey(list));

    }

    @Test
    @DisplayName("성공 - checkDuplicateSurvey - 중복이 있을때")
    void success_checkDuplicateSurvey_중복이_있을때() {
        List<SurveyBase> list = new ArrayList<>();
        list.add(SurveyBase.builder().id(1).build());
        list.add(SurveyBase.builder().id(2).build());
        list.add(SurveyBase.builder().id(2).build());

        Assertions.assertEquals(1,
                resourceInspectSurveyService.checkDuplicateSurvey(list));

    }

}