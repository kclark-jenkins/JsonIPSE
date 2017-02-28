package org.krisbox.json.ipse.utils;

import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import com.actuate.reportcast.exceptions.AuthenticationException;
import com.actuate.iportal.security.iPortalSecurityAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.krisbox.json.ipse.mapper.JsonIpseMapper;
import org.krisbox.json.ipse.properties.IpseProperties;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class IpseHelper extends iPortalSecurityAdapter {
    private IpseProperties jsonIpseProperties = new IpseProperties();
    private String         cookieName;

    public IpseHelper(String cookieName) {
        this.cookieName = new String(cookieName);
    }

    public void mapJson(HttpServletRequest request) {
        String rawCookie = request.getHeader("Cookie");
        String[] rawCookieParams = rawCookie.split(";");
        for(String rawCookieNameAndValue :rawCookieParams) {
            String[] rawCookieNameAndValuePair = rawCookieNameAndValue.split("=");
            if(new String(rawCookieNameAndValuePair[0]).trim().equals(cookieName)) {
                try {
                    jsonIpseProperties = new ObjectMapper().readValue(rawCookieNameAndValuePair[1], JsonIpseMapper.class).getIpseProperties();
                    setJsonSessionLevelParameter(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public IpseProperties getIpsePropertiesPojo() {
        return jsonIpseProperties;
    }

    private void setJsonSessionLevelParameter(HttpServletRequest request) {
        request.getSession().setAttribute(cookieName, jsonIpseProperties);
    }

    private String ipseToJsonString(IpseProperties ipseProperties) {
        try {
            return new ObjectMapper().writeValueAsString(ipseProperties);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method returns the username for the current request.
     *
     * @return Returns the username for the current requests
     */
    public String  getUserName() {
        return jsonIpseProperties.getUsername();
    }

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
    public String  getVolume() {
        return jsonIpseProperties.getVolume();
    }

    /**
     * This method get's the user's volume profile.
     * @return
     */
    public String  getVolumeProfile() {
        return jsonIpseProperties.getVolumeProfile();
    }

    /**
     * This method get's the user's extended credentials set in the RSSE
     *
     * @return Returns the user's extended credentials
     */
    public byte[]  getExtendedCredentials() {
        return jsonIpseProperties.getExtendedCredentials().getBytes(StandardCharsets.UTF_8);
    }

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

    private String      readCookie(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals(cookieName)) {
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
}
