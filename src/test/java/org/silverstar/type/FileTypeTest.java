package org.silverstar.type;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.silverstar.exception.FileFormatException;

class FileTypeTest {

    @Test
    @DisplayName("실패 - validateColumns - 파라미터 파일타입이 잘못되었을때")
    void fail_validateColumns_파라미터_파일타입이_잘못되었을때() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            FileType.validateColumns(null, null);
        });
    }

    @Test
    @DisplayName("실패 - validateColumns - 파라미터 String배열이 잘못되었을때")
    void fail_validateColumns_파라미터_String배열이_잘못되었을때() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            FileType.validateColumns(FileType.SURVEY, null);
        });

        Assertions.assertThrows(FileFormatException.class, () -> {
            FileType.validateColumns(FileType.SURVEY, new String[] {});
        });

        Assertions.assertThrows(FileFormatException.class, () -> {
            FileType.validateColumns(FileType.SURVEY, new String[] {"string"});
        });
    }


    @Test
    @DisplayName("성공 - validateColumns")
    void success_validateColumns() throws FileFormatException {
        FileType.validateColumns(FileType.SURVEY, new String [] {"100", "만족도설문조사"});
    }
}