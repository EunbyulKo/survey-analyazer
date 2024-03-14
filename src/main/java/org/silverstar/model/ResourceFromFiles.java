package org.silverstar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ResourceFromFiles {

    List<SurveyBase> surveys;
    List<SurveyQuestion> questions;
    List<SurveyQuestionAnswer> answers;
    List<SurveyResult> results;

}
