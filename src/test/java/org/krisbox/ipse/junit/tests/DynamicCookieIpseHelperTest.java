package org.krisbox.ipse.junit.tests;

import org.junit.Test;
import org.krisbox.ipse.control.ControlStrings;
import org.krisbox.ipse.junit.subjects.DynamicJsonIPSE;
import org.springframework.mock.web.MockHttpServletRequest;
import javax.servlet.http.Cookie;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DynamicCookieIpseHelperTest extends ControlStrings {
    private MockHttpServletRequest servletRequest;

    public DynamicCookieIpseHelperTest() {
        servletRequest = new MockHttpServletRequest();
        Cookie[] cookies = {new Cookie(getJsonCookieName(), getIpseJson())};
        servletRequest.setCookies(cookies);
    }

    @Test
    public void testIpse() {
        DynamicJsonIPSE jsonIPSE = new DynamicJsonIPSE();

        assertEquals(getAuthenticated(),  jsonIPSE.authenticate(servletRequest));
        assertEquals(getUsername(),       jsonIPSE.getUserName());
        assertEquals(getPassword(),       jsonIPSE.getPassword());
        assertEquals(getVolume(),         jsonIPSE.getVolume());
        assertEquals(getVolumeProfile(),  jsonIPSE.getVolumeProfile());
        assertEquals(getBytesEqual(),     compareByteArrays(jsonIPSE.getExtendedCredentials()));
        assertEquals(getEnterprise(),     jsonIPSE.isEnterprise());
        assertEquals(getUserHomeFolder(), jsonIPSE.getUserHomeFolder());
    }

    private boolean compareByteArrays(byte[] array1) {
        return Arrays.equals(getExtendedCredentials().getBytes(StandardCharsets.UTF_8), array1);
    }
}
