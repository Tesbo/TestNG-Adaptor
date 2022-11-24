package io.tesbo.report;

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


    @Parameter(
            names = {"--Browser", "-b"},
            description = "Browser Name "

    )
    public String browser;

    @Parameter(
            names = {"--BrowserVer", "-bv"},
            description = "Test Directory Path "

    )
    public String BrowserVersion;

    @Parameter(
            names = {"--Platform", "-p"},
            description = "Test Directory Path "

    )
    public String platformName;

    @Parameter(
            names = {"--PlatformVer", "-pv"},
            description = "Test Directory Path "

    )
    public String platformVer;


}

