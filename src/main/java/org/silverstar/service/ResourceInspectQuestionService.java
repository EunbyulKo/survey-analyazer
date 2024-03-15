package org.silverstar.service;

import lombok.extern.slf4j.Slf4j;
import org.silverstar.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ResourceInspectQuestionService {

    public long checkDuplicateQuestion(List<SurveyQuestion> questions) {
        Map<SurveyQuestionId, Long> map = questions.stream()
                .collect(Collectors.groupingBy(SurveyQuestion::getId,
                        Collectors.counting()));

        long count = map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .count();

        if (count > 0) {
            log.warn("checkDuplicateQuestion : " + count);
            log.warn("checkDuplicateQuestion : " +
                    map.entrySet().stream()
                    .filter(e -> e.getValue() > 1)
                    .limit(5).toList());
        }

        return count;
    }

    public long checkSurveyAndQuestion(List<SurveyBase> surveys, List<SurveyQuestion> questions) {
        Set<Integer> surveyIds = surveys.stream().map(SurveyBase::getId).collect(Collectors.toSet());

        Set<Integer> noSurveyIds = questions.stream()
                .map(question -> question.getId().getSurveyId())
                .filter(surveyId -> !surveyIds.contains(surveyId))
                .collect(Collectors.toSet());

        if (!noSurveyIds.isEmpty()) {
            log.warn("checkSurveyAndQuestion : " + noSurveyIds.size());
            log.warn("checkSurveyAndQuestion : " + noSurveyIds.stream().limit(5).toList());
        }

        return noSurveyIds.size();
    }

}
