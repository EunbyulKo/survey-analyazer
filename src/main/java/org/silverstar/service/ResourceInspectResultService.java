package org.silverstar.service;

import lombok.extern.slf4j.Slf4j;
import org.silverstar.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ResourceInspectResultService {

    public long checkNoResponseResult(List<SurveyResult> results) {
        long count = results.stream()
                .filter(result -> result.getResponses() == null || result.getResponses().isEmpty())
                .count();

        if (count > 0) {
            log.warn("checkNoResponseResult : " + count);
        }

        return count;
    }

    public long checkAnswerAndResult(List<SurveyQuestionAnswer> answers, List<SurveyResult> results) {
        Set<SurveyQuestionAnswerId> answerIds = answers.stream().map(SurveyQuestionAnswer::getId).collect(Collectors.toSet());

        Set<SurveyQuestionAnswerId> noAnswerIds =  results.stream()
                .filter(result -> result.getResponses() != null && !result.getResponses().isEmpty())
                .flatMap(result ->
                        result.getResponses().stream().map(
                                response -> SurveyQuestionAnswerId.builder()
                                .surveyId(result.getId().getSurveyId())
                                .groupId(result.getId().getGroupId())
                                .questionId(result.getId().getQuestionId())
                                .answerId(response)
                                .build())
                )
                .filter(id -> !answerIds.contains(id))
                .collect(Collectors.toSet());

        if (!noAnswerIds.isEmpty()) {
            log.warn("checkSurveyAndQuestion : " + noAnswerIds.size());
            log.warn("checkSurveyAndQuestion : " + noAnswerIds.stream().limit(5).toList());
        }

        return noAnswerIds.size();
    }


    public long checkNullScoreAndMaxScore(List<SurveyResult> results) {
        long count = results.stream()
                .filter(result -> result.getMaxScore() == null || result.getScore() == null)
                .count();

        if (count > 0) {
            log.warn("checkNullScoreAndMaxScore : " + count);
        }

        return count;
    }

    public long checkMoreScoreThanMaxScore(List<SurveyResult> results) {
        long count = results.stream()
                .filter(result -> result.getMaxScore() != null && result.getScore() != null)
                .filter(result -> result.getMaxScore() < result.getScore())
                .count();

        if (count > 0) {
            log.warn("checkMoreScoreThanMaxScore : " + count);
        }

        return count;
    }

    public long checkTargetIdAndUserId(List<SurveyResult> results) {
        Map<Integer, Map<String, Long>> map = results.stream()
                .collect(Collectors.groupingBy(SurveyResult::getTargetId,
                        Collectors.groupingBy(SurveyResult::getUesrId, Collectors.counting())));

        long count = map.values().stream()
                .filter(e -> e.values().size() > 1)
                .count();

        if (count > 0) {
            log.warn("checkTargetIdAndUserId : " + count);
            log.warn("checkTargetIdAndUserId : " + map.keySet().stream().limit(5).toList());
        }

        return count;
    }
}
