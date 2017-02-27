package org.krisbox.ipse.control;

import org.krisbox.ipse.junit.utils.TestsHelper;
import org.krisbox.json.ipse.mapper.JsonIpseMapper;

import java.util.List;

public class ControlStrings extends TestsHelper {
    public ControlStrings() {
        super("controlValues.properties");
    }

    public String getIpseJson() {
        return getProperty("ipseJson");
    }

    public boolean        getAuthenticated()       {return propertyToBoolean("authenticated");}
    public boolean        getEnterprise()          {return propertyToBoolean("enterprise");}
    public boolean        getBytesEqual()          {return propertyToBoolean("bytesEqual");}
    public int            getDescribe()            {return propertyToInteger("describe");}
    public int            getHashCode()            {return propertyToInteger("hashCode");}
    public int            getOffset()              {return propertyToInteger("offset");}
    public String         getUsername()            {return propertyToString( "username");}
    public String         getPassword()            {return propertyToString( "password");}
    public String         getVolume()              {return propertyToString( "volume");}
    public String         getVolumeProfile()       {return propertyToString( "volumeProfile");}
    public String         getExtendedCredentials() {return propertyToString( "extendedCredentials");}
    public String         getUserHomeFolder()      {return propertyToString( "userHomeFolder");}
    public String         getObjectToString()      {return propertyToString( "objectToString");}
    public String         getJsonCookieName()      {return propertyToString( "jsonCookieName");}
    public List<String>   getParameterNames()      {return propertyToList(   "parameterNames");}
    public List<String>   getParameterValues()     {return propertyToList(   "parameterValues");}
    public JsonIpseMapper getIpseJsonObject()      {return createIpseJsonObject(propertyToString("ipseJson"));}


}
