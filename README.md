# 목적
- TDD(테스트주도개발) 연습
- DSL(도메인특화언어) 연습

# 기술스택
- Java 17
- Gradle

# 프로젝트 기능
- 설문데이터를 읽어와 유효성 검사를 한다.
- 공공데이터포털에 있는 무료 데이터를 이용한다.

### 샘플데이터
|파일|설명|
|----|---|
|survey.csv|설명번호와 설문이름|
|surveyQuestion.csv|설문별 문항번호와 문항내용|
|surveyQuestionAnswer.csv|설문별 문항별 답안보기번호와 답안보기내용|
|surveyResult.csv|설문응답|

### 데이터 읽어오기
- survey.csv, surveyQuestion.csv, surveyQuestionAnswer.csv, surveyResult.csv 읽어온다.
- 파일을 읽어올 때 정규식으로 split한다.
- 간단한 데이터 타입 검사(숫자, 날짜)를 진행한다.

### 데이터 유효성 검사하기
- survey.csv
    - 중복 ID가 있는가?
- surveyQuestion.csv
    - 중복 ID가 있는가?
    - survey ID가 유효한가?
- surveyQuestionAnswer.csv
    - 중복 ID가 있는가?
    - question ID가 유효한가?
- surveyResult.csv
    - answer ID가 유효한가?
    - 응답결과가 없는 데이터가 있는가?
    - 스코어나 최대스코어가 없거나 최대스코어보다 스코어가 큰 경우가 있는가?
    - 작성자번호와 설문대상일련번호가 일치하지 않는 경우가 있는가?
