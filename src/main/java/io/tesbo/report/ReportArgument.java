package io.tesbo.report;

import com.beust.jcommander.Parameter;

public class ReportArgument {

    @Parameter(
            names = {"--Key", "-k"},
            description = "Build Key",
            required = true
    )
    public static String buildKey;

    @Parameter(
            names = {"--Dir", "-d"},
            description = "Test Directory Path ",
            required = true
    )
    public static String testDir;


    @Parameter(
            names = {"--Browser", "-b"},
            description = "Browser Name "

    )
    public static String browser;

    @Parameter(
            names = {"--BrowserVer", "-bv"},
            description = "Browser Version"

    )
    public static String BrowserVersion;

    @Parameter(
            names = {"--Platform", "-p"},
            description = "Platform Name"

    )
    public static String platformName;

    @Parameter(
            names = {"--PlatformVer", "-pv"},
            description = "Platform Version"

    )
    public static String platformVer;

    @Parameter(
            names = {"--DeviceName", "-dn"},
            description = "Device Name "

    )
    public static String deviceName;

}

