package com.qualiTest.test.framework.Helpers;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
    private static Properties envConfigProps;
    private static Properties messageProps;
    private static final String profile;
    static {
        profile=System.getProperty("profile.name");
        loadProps(profile);
    }

    public static void loadProps(String profile) {
        final String configPropertyFileLocation="/profiles/"+profile+"/config.properties";
        final String messagePropertFileLocation="/TestData/"+profile+"/messages.properties";
        envConfigProps = new Properties();
        messageProps = new Properties();
        try {
            InputStream inputStream = Props.class.getResourceAsStream(configPropertyFileLocation);
            envConfigProps.load(inputStream);
            InputStream inputStreamMessages = Props.class.getResourceAsStream(messagePropertFileLocation);
            messageProps.load(inputStreamMessages);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the key from messages.properties for a environment
     *
     * @param key
     **/
    public static String getMessage(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return messageProps.getProperty(key);
        }
    }

    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return envConfigProps.getProperty(key);

        }
    }

}
