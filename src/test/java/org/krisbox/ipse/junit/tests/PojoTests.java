package org.krisbox.ipse.junit.tests;

import org.junit.Test;
import org.krisbox.ipse.control.ControlStrings;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.krisbox.json.ipse.mapper.JsonIpseMapper;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PojoTests {
    private ControlStrings control;

    public PojoTests() {
        control = new ControlStrings();
    }

    @Test
    public void testIpseJsonToPojo() {
        ObjectMapper mapper     = new ObjectMapper();
        JsonIpseMapper ipseJsonObject = null;
        IOException ioException = null;

        try {
            ipseJsonObject = mapper.readValue(control.getIpseJson(), JsonIpseMapper.class);
        } catch (IOException e) {
            ioException = e;
        }

        assertEquals(null, ioException);
    }

    @Test
    public void testIpseJsonToPojoValues() {
        ObjectMapper   mapper         = new ObjectMapper();
        JsonIpseMapper ipseJsonObject = null;

        try {
            ipseJsonObject = mapper.readValue(control.getIpseJson(), JsonIpseMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(control.getUsername(),            ipseJsonObject.getIpseProperties().getUsername());
        assertEquals(control.getPassword(),            ipseJsonObject.getIpseProperties().getPassword());
        assertEquals(control.getVolume(),              ipseJsonObject.getIpseProperties().getVolume());
        assertEquals(control.getVolumeProfile(),       ipseJsonObject.getIpseProperties().getVolumeProfile());
        assertEquals(control.getExtendedCredentials(), ipseJsonObject.getIpseProperties().getExtendedCredentials());
        assertEquals(control.getEnterprise(),          ipseJsonObject.getIpseProperties().isEnterprise());
        assertEquals(control.getUserHomeFolder(),      ipseJsonObject.getIpseProperties().getUserHomeFolder());
        assertEquals(control.getParameterNames(),      ipseJsonObject.getIpseProperties().getSessionLevelParameters().getParameterNames());
        assertEquals(control.getParameterValues(),     ipseJsonObject.getIpseProperties().getSessionLevelParameters().getParameterValues());
    }

    @Test
    public void testIpseJsonPojoEquals() {
        ObjectMapper mapper     = new ObjectMapper();
        JsonIpseMapper ipseJsonObject = null;

        try {
            ipseJsonObject = mapper.readValue(control.getIpseJson(), JsonIpseMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(true, ipseJsonObject.equals(control.getIpseJsonObject()));
    }

    @Test
    public void testIpseJsonPojoDescribeContents() {
        ObjectMapper mapper     = new ObjectMapper();
        JsonIpseMapper ipseJsonObject = null;

        try {
            ipseJsonObject = mapper.readValue(control.getIpseJson(), JsonIpseMapper.class);
            assertEquals(control.getDescribe(), ipseJsonObject.describeContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIpseJsonObjectHashCode() {
        ObjectMapper mapper     = new ObjectMapper();
        JsonIpseMapper ipseJsonObject = null;

        try {
            ipseJsonObject = mapper.readValue(control.getIpseJson(), JsonIpseMapper.class);
            assertEquals(control.getHashCode(), ipseJsonObject.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIpseJsonObjectToString() {
        ObjectMapper mapper     = new ObjectMapper();
        JsonIpseMapper ipseJsonObject = null;

        try {
            ipseJsonObject = mapper.readValue(control.getIpseJson(), JsonIpseMapper.class);
//            assertEquals(control.getObjectToString(), ipseJsonObject.toString().substring(control.getOffset()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
