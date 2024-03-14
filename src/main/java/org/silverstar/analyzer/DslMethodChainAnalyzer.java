package org.silverstar.analyzer;

import org.silverstar.model.ResourceFromFiles;
import org.silverstar.service.FileReaderService;
import org.silverstar.service.ResourceInspectService;

public class DslMethodChainAnalyzer {

    private final FileReaderService fileReaderService;

    private final ResourceInspectService resourceInspectService;

    private ResourceFromFiles files;

    public DslMethodChainAnalyzer() {
        this.fileReaderService = new FileReaderService();
        this.resourceInspectService = new ResourceInspectService();
    }

    public static DslMethodChainAnalyzer builder() {
        return new DslMethodChainAnalyzer();
    }

    public DslMethodChainAnalyzer readFiles() {
        files = fileReaderService.getResourceFromFiles();
        return this;
    }

    public DslMethodChainAnalyzer inspect() {
        resourceInspectService.inspect(files);
        return this;
    }
    
}
