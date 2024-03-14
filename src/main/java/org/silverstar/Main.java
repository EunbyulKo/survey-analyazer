package org.silverstar;

import org.silverstar.analyzer.DslMethodChainAnalyzer;
import org.silverstar.model.ResourceFromFiles;
import org.silverstar.service.FileReaderService;
import org.silverstar.service.ResourceInspectService;

import static org.silverstar.analyzer.DslLamdaAnalyzer.inspectLamda;
import static org.silverstar.analyzer.DslLamdaAnalyzer.readFilesLamda;
import static org.silverstar.analyzer.DslNestedFunctionAnalyzer.*;

public class Main {
    public static void main(String[] args) {
        // 1. 기존 방식
        FileReaderService fileReaderService = new FileReaderService();

        ResourceFromFiles files = fileReaderService.getResourceFromFiles();

        ResourceInspectService resourceInspectService = new ResourceInspectService();

        resourceInspectService.inspect(files);

        // 2. DSL 메서드 체인
        DslMethodChainAnalyzer.builder()
                .readFiles()
                .inspect();

        // 3. DSL 중첩 메서드
        inspect(
                readFiles(
                        readSurveyFile(),
                        readQuestionFile(),
                        readAnswerFile(),
                        readResultFile()
                )
        );

        // 4. 람다식
        inspectLamda(
                ResourceInspectService::inspect,
                readFilesLamda(
                        FileReaderService::getResourceFromFiles
                )
        );


    }
}