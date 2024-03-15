package org.silverstar.service;

import lombok.extern.slf4j.Slf4j;
import org.silverstar.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ResourceInspectAnswerService {
    public long checkDuplicateAnswer(List<SurveyQuestionAnswer> answers) {
        Map<SurveyQuestionId, Long> map = answers.stream()
                .collect(Collectors.groupingBy(SurveyQuestionAnswer::getId,
                        Collectors.counting()));

        long count = map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .count();

        if (count > 0) {
            log.warn("checkDuplicateAnswer : " + count);
            log.warn("checkDuplicateAnswer : " +
                    map.entrySet().stream()
                    .filter(e -> e.getValue() > 1)
                    .limit(5).toList());
        }

        return count;
    }

    public long checkQuestionAndAnswer(List<SurveyQuestion> questions, List<SurveyQuestionAnswer> answers) {
        Set<SurveyQuestionId> questionsIds = questions.stream().map(SurveyQuestion::getId).collect(Collectors.toSet());

        Set<SurveyQuestionId> noQuestionIds = answers.stream()
                .map(answer -> SurveyQuestionId.builder()
                        .surveyId(answer.getId().getSurveyId())
                        .groupId(answer.getId().getGroupId())
                        .questionId(answer.getId().getQuestionId())
                        .build())
                .filter(answerId -> !questionsIds.contains(answerId))
                .collect(Collectors.toSet());

        if (!noQuestionIds.isEmpty()) {
            log.warn("checkSurveyAndQuestion : " + noQuestionIds.size());
            log.warn("checkSurveyAndQuestion : " + noQuestionIds.stream().limit(5).toList());
        }

        return noQuestionIds.size();
    }

}
