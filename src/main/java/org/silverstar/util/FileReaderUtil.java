package org.silverstar.util;

import lombok.extern.slf4j.Slf4j;
import org.silverstar.exception.FileFormatException;
import org.silverstar.type.FileType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FileReaderUtil {

    public static <T, R> List<R> readSurveyFile(String fileName, Function<T, R> func) {
        String path = FileReaderUtil.class.getClassLoader()
                .getResource(fileName).getPath();

        try (Stream<String> lines = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            return lines
                    .map(l -> func.apply((T) l))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("readSurveyFile : " + fileName);
            return Collections.emptyList();
        }
    }

    public static boolean validateLine(FileType fileType, String [] cols) {
        try {
            FileType.validateColumns(fileType, cols);
            return true;
        } catch (FileFormatException e) {
            log.error(e.getMessage());
            return false;
        }
    }

}
