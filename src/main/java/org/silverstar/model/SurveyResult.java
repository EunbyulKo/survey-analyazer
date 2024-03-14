package org.silverstar.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class SurveyResult {

    private SurveyQuestionId id;

    private List<Integer> responses;

    private int resultId;

    private int targetId;

    private String uesrId;

    private Integer score;

    private Integer maxScore;

    private LocalDate resultDate;

    private LocalDate regDate;

}
