package org.silverstar.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.silverstar.type.FileType;

import java.io.UncheckedIOException;

class FileReaderUtilTest {

    @Test
    @DisplayName("실패 - readSurveyFile - 파라미터 파일명이 잘못되었을때")
    void fail_readSurveyFile_파라미터_파일명이_잘못되었을때() {
        Assertions.assertThrows(NullPointerException.class,
                () -> FileReaderUtil.readSurveyFile(null, null));

        Assertions.assertThrows(UncheckedIOException.class,
                () -> FileReaderUtil.readSurveyFile("", null));

        Assertions.assertThrows(NullPointerException.class,
                () -> FileReaderUtil.readSurveyFile("test.txt", null));
    }

    @Test
    @DisplayName("실패 - readSurveyFile - 파라미터 func이 잘못되었을때")
    void fail_readSurveyFile_파라미터_func이_잘못되었을때() {
        Assertions.assertThrows(NullPointerException.class,
                () -> FileReaderUtil.readSurveyFile("survey.csv", null));

        Assertions.assertThrows(RuntimeException.class,
                () -> FileReaderUtil.readSurveyFile("survey.csv",
                        (s) -> {throw new RuntimeException();}));
    }

    @Test
    @DisplayName("성공 - readSurveyFile")
    void success_readSurveyFile() {
        FileReaderUtil.readSurveyFile("survey.csv", (s) -> "return string");
    }

    @Test
    @DisplayName("실패 - validateLine - 파라미터 파일타입이 잘못되었을때")
    void fail_validateLine_파라미터_파일타입이_잘못되었을때() {
        Assertions.assertThrows(NullPointerException.class,
                () -> FileReaderUtil.validateLine(null, null));
    }

    @Test
    @DisplayName("실패 - validateLine - 파라미터 String 배열이 잘못되었을때")
    void fail_파라미터_String배열이_잘못되었을때() {
        Assertions.assertThrows(NullPointerException.class,
                () -> FileReaderUtil.validateLine(FileType.SURVEY, null));

        Assertions.assertFalse(
                FileReaderUtil.validateLine(FileType.SURVEY, new String [] {}));

        Assertions.assertFalse(
                FileReaderUtil.validateLine(FileType.SURVEY, new String [] {"string"}));
    }

    @Test
    @DisplayName("성공 - validateLine")
    void success_validateLine() {
        Assertions.assertTrue(FileReaderUtil.validateLine(FileType.SURVEY, new String[]{"100", "만족도설문조사"}));
    }

}