package org.silverstar;

import org.silverstar.model.ResourceFromFiles;
import org.silverstar.service.FileReaderService;
import org.silverstar.service.ResourceInspectService;

public class Main {
    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderService();

        ResourceFromFiles files = fileReaderService.getResourceFromFiles();

        ResourceInspectService resourceInspectService = new ResourceInspectService();

        resourceInspectService.inspect(files);
    }
}