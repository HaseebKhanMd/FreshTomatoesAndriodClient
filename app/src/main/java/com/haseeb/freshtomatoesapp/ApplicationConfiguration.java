package com.haseeb.freshtomatoesapp;

/**
 * Created by Haseeb on 7/20/2015.
 */
public class ApplicationConfiguration {

    //  http://localhost:2020/api/v1/
    // For Emulator it needs to be 10.0.2.2 for localhost
    public static String getBaseUrl() {
        return "http://10.0.2.2:2020/api/v1/";
    }

    // This needs to come from Login Screen
    public static String getCredentials() { return ("haseeb"+":"+"password"); }

}
