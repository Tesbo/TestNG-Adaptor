package io.tesbo.testreport;

import com.beust.jcommander.Parameter;

public class ReportArgument {

    @Parameter(
            names = {"--Key", "-k"},
            description = "Build Key",
            required = true
    )
    public String buildKey;

    @Parameter(
            names = {"--Dir", "-d"},
            description = "Test Directory Path ",
            required = true
    )
    public String testDir;


}
