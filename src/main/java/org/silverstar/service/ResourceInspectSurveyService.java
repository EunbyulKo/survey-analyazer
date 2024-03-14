package org.silverstar.service;

import lombok.extern.slf4j.Slf4j;
import org.silverstar.model.SurveyBase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ResourceInspectSurveyService {

    public long checkDuplicateSurvey(List<SurveyBase> surveys) {
        Map<Integer, Long> map = surveys.stream()
                .collect(Collectors.groupingBy(SurveyBase::getId,
                        Collectors.counting()));

        long count = map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .count();

        if (count > 0) {
            log.warn("checkDuplicateSurvey : " + count);
            log.warn("checkDuplicateSurvey : " + map.keySet().stream().limit(5).toList());
        }

        return count;
    }

}
