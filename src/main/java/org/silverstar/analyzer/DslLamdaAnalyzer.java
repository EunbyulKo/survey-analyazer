package org.silverstar.analyzer;

import org.silverstar.model.ResourceFromFiles;
import org.silverstar.service.FileReaderService;
import org.silverstar.service.ResourceInspectService;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class DslLamdaAnalyzer {

    public static ResourceFromFiles readFilesLamda(Function<FileReaderService, ResourceFromFiles> function) {
        FileReaderService fileReaderService = new FileReaderService();
        return function.apply(fileReaderService);
    }

    public static void inspectLamda(BiConsumer<ResourceInspectService, ResourceFromFiles> consumer,
                        ResourceFromFiles files) {
        ResourceInspectService resourceInspectService = new ResourceInspectService();
        consumer.accept(resourceInspectService, files);
    }
    
}
