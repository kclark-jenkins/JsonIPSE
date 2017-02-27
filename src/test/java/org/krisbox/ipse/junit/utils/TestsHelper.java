package org.krisbox.ipse.junit.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.krisbox.json.ipse.mapper.JsonIpseMapper;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.Cookie;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TestsHelper {
    private final Properties JUNIT_PROPERTIES;
    private MockHttpServletRequest servletRequest;

    public TestsHelper(String filename) {
        JUNIT_PROPERTIES = loadProperties(filename);
        servletRequest   = new MockHttpServletRequest();

    }
    public String getProperty(String key) {
        return JUNIT_PROPERTIES.getProperty(key);
    }

    protected void setCookie(String key, String value) {
        Cookie[] cookies = {new Cookie(key, value)};
        servletRequest.setCookies(cookies);
    }
    protected MockHttpServletRequest getServletRequest() {return servletRequest;}
    protected void setServletRequest(MockHttpServletRequest servletRequest){this.servletRequest=servletRequest;}
    private Properties loadProperties(String filename) {
        try {
            Properties ipseProperties = new Properties();
            ipseProperties.load(readResourceFile(filename));
            return ipseProperties;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    private InputStream readResourceFile(String filename) {
        try {
            return new FileInputStream(new File(getClass().getClassLoader().getResource(filename).getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    protected boolean propertyToBoolean(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
    protected int propertyToInteger(String key) {
        return Integer.parseInt(getProperty(key));
    }
    protected String propertyToString(String key) {
        return getProperty(key);
    }
    protected List<String> propertyToList(String key) {
        return Arrays.asList(getProperty(key).split("\\s*,\\s*"));
    }
    protected JsonIpseMapper createIpseJsonObject(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, JsonIpseMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    protected boolean compareByteArrays(String extendedCredentials, byte[] array1) {
        return Arrays.equals(extendedCredentials.getBytes(StandardCharsets.UTF_8), array1);
    }
}
