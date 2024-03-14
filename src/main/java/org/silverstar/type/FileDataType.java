package org.silverstar.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum FileDataType {
    NUMBER(Pattern.compile("[0-9]+")),
    STRING(Pattern.compile("^.+$")),
    DATE(Pattern.compile("^[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")),
    NUMBER_ARRAY(Pattern.compile("(^[0-9^]+$)|^$")),
    NUMBER_OR_EMPTY(Pattern.compile("(^[0-9]+$)|^$"));

    private final Pattern pattern;

    public static int convertNumber(String s) {
        return Integer.parseInt(s);
    }

    public static LocalDate convertDate(String s) {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static List<Integer> convertNumberArray(String s) {
        return s.isEmpty()? Collections.emptyList() :
                Arrays.stream(s.split("\\^")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static Integer convertNumberOrEmpty(String s) {
        return s.isEmpty()? null : Integer.parseInt(s);
    }
}
