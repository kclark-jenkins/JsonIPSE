package org.krisbox.ipse.junit.subjects;

import com.actuate.iportal.security.iPortalSecurityAdapter;
import org.krisbox.json.ipse.mapper.JsonIpseMapper;
import org.krisbox.json.ipse.properties.IpseProperties;
import org.krisbox.json.ipse.utils.IpseHelper;

import javax.servlet.http.HttpServletRequest;

public class DynamicJsonIPSE extends iPortalSecurityAdapter {
    private IpseProperties jsonIpseProperties;
    private JsonIpseMapper jsonIpseMapper;

    IpseHelper helper;

    public DynamicJsonIPSE() {
        helper = new IpseHelper("ipseJSON");
    }

    public boolean authenticate(HttpServletRequest request) {
        try {
            helper.mapJson(request);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return true;
    }

    public String getUserName() {
        System.out.println(helper.getUserName());
        return helper.getUserName();
    }

    public String getPassword() {
        System.out.println(helper.getPassword());
        return helper.getPassword();
    }

    public String getVolume() {
        System.out.println(helper.getVolume());
        return helper.getVolume();
    }

    public String getVolumeProfile() {
        System.out.println(helper.getExtendedCredentials().toString());

        String vp;

        try {
            vp = new String(helper.getVolumeProfile());
        }catch(Exception ex){
            ex.printStackTrace();
            vp = new String("Default Volume");
        }

        return vp;
    }

    public byte[] getExtendedCredentials() {
        System.out.println(helper.getExtendedCredentials().toString());
        return helper.getExtendedCredentials();
    }

    public boolean isEnterprise() {
        System.out.println(helper.isEnterprise());
        return helper.isEnterprise();
    }

    public String getUserHomeFolder() {
        System.out.println(helper.getUserHomeFolder());
        return helper.getUserHomeFolder();
    }
}
