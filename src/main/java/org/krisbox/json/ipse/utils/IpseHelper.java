package org.krisbox.json.ipse.utils;

import com.actuate.iportal.security.iPortalSecurityAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.krisbox.json.ipse.mapper.JsonIpseMapper;
import org.krisbox.json.ipse.properties.IpseProperties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public abstract class IpseHelper extends iPortalSecurityAdapter {
    private final String   PROPERTIES_FILE;
    private Properties     ipseProperties;
    private IpseProperties jsonIpseProperties;

    public IpseHelper(String filename) {
        PROPERTIES_FILE = new String(filename);
        ipseProperties  = loadProperties(PROPERTIES_FILE);
    }

    public void mapJson(HttpServletRequest request) {
        try {
            jsonIpseProperties = new ObjectMapper().readValue(readCookie(ipseProperties.getProperty("ipse.cookie.name"), request), JsonIpseMapper.class).getIpseProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mapJson(String cookieName, HttpServletRequest request) {
        try {
            jsonIpseProperties = new ObjectMapper().readValue(readCookie(cookieName, request), JsonIpseMapper.class).getIpseProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to determine if the user making the request is allowed
     * to access the requested resource.  Anything can be passed to it via HttpServletRequest
     * for you to manually check.
     *
     * @param request Http Servlet Request passed into iHub
     * @return Returns a boolean representing if the user is allowed to access the requested resource
     */
    public abstract boolean authenticate(HttpServletRequest request);

    /**
     * This method returns the username for the current request.
     *
     * @return Returns the username for the current requests
     */
    public String  getUserName() {return jsonIpseProperties.getUsername();}

    /**
     *  This method returns the password associated with the user.
     *
     * @return Returns the users password
     */
    public String  getPassword() {return jsonIpseProperties.getPassword();}

    /**
     *  This method get's the user's volume.
     *
     * @return Returns the user's volume
     */
    public String  getVolume() {return jsonIpseProperties.getVolume();}

    /**
     * This method get's the user's volume profile.
     * @return
     */
    public String  getVolumeProfile() {return jsonIpseProperties.getVolumeProfile();}

    /**
     * This method get's the user's extended credentials set in the RSSE
     *
     * @return Returns the user's extended credentials
     */
    public byte[]  getExtendedCredentials() {return jsonIpseProperties.getExtendedCredentials().getBytes(StandardCharsets.UTF_8);}

    /**
     * This method returns if the iHub is set to enterprise or workgroup
     * mode
     *
     * @return Returns a boolean, if true then enterprise, if false then
     * workgroup
     */
    public boolean isEnterprise() {return jsonIpseProperties.isEnterprise();};

    /**
     * This method returns the user's home folder
     *
     * @return Returns the user's home folders
     */
    public String  getUserHomeFolder() {return jsonIpseProperties.getUserHomeFolder();};

    private Properties  loadProperties(String filename) {
        try {
            Properties ipseProperties = new Properties();
            ipseProperties.load(readResourceFile(filename));
            return ipseProperties;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    private String      readCookie(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals(ipseProperties.getProperty("ipse.cookie.name"))) {
                return cookie.getValue();
            }
        }

        return null;
    }
    private String      readCookie(String cookieName, HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
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
    protected String    getProperty(String key) {return ipseProperties.getProperty(key);}
}
