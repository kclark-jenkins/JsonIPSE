package org.krisbox.ipse.junit.tests;

import org.junit.Test;
import org.krisbox.ipse.control.ControlStrings;
import org.krisbox.ipse.junit.subjects.DynamicJsonIPSE;
import org.krisbox.json.ipse.JsonIpse;
import org.springframework.mock.web.MockHttpServletRequest;
import javax.servlet.http.Cookie;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DynamicCookieIpseHelperTest {
    private MockHttpServletRequest servletRequest;

    public DynamicCookieIpseHelperTest() {
        servletRequest = new MockHttpServletRequest();
        Cookie[] cookies = {new Cookie("ipseJSON", "{\"ipseProperties\": {\"username\": \"Administrator\",\"password\": \"pass\",\"volume\": \"Default Volume\",\"volumeProfile\": \"Default Volume\",\"extendedCredentials\": \"stuff\",\"enterprise\": true,\"userHomeFolder\": \"/home/Administrator\",\"sessionLevelParameters\": {\"parameterNames\": [\"testParam0\", \"testParam1\"],\"parameterValues\": [\"testValue0\", \"testValue1\"]}}}")};
        servletRequest.setCookies(cookies);
    }

    @Test
    public void testIpse() {
        JsonIpse jsonIPSE = new JsonIpse();

        System.out.println(jsonIPSE.authenticate(servletRequest));
        System.out.println(jsonIPSE.getUserName());
        System.out.println(jsonIPSE.getPassword());
        System.out.println(jsonIPSE.getVolume());
        System.out.println(jsonIPSE.getVolumeProfile());
        System.out.println(jsonIPSE.getExtendedCredentials());
        System.out.println(jsonIPSE.isEnterprise());
        System.out.println(jsonIPSE.getUserHomeFolder());
        /*assertEquals(getAuthenticated(),  jsonIPSE.authenticate(servletRequest));
        assertEquals(getUsername(),       jsonIPSE.getUserName());
        assertEquals(getPassword(),       jsonIPSE.getPassword());
        assertEquals(getVolume(),         jsonIPSE.getVolume());
        assertEquals(getVolumeProfile(),  jsonIPSE.getVolumeProfile());
        assertEquals(getBytesEqual(),     compareByteArrays(jsonIPSE.getExtendedCredentials()));
        assertEquals(getEnterprise(),     jsonIPSE.isEnterprise());
        assertEquals(getUserHomeFolder(), jsonIPSE.getUserHomeFolder());*/
    }

    private boolean compareByteArrays(byte[] array1) {
        return false;
        //return Arrays.equals(getExtendedCredentials().getBytes(StandardCharsets.UTF_8), array1);
    }
}
