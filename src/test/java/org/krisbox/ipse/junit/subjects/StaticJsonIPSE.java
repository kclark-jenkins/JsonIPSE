package org.krisbox.ipse.junit.subjects;

import org.krisbox.json.ipse.mapper.JsonIpseMapper;
import org.krisbox.json.ipse.properties.IpseProperties;
import org.krisbox.json.ipse.utils.IpseHelper;

import javax.servlet.http.HttpServletRequest;

public class StaticJsonIPSE extends IpseHelper {
    private IpseProperties jsonIpseProperties;
    private JsonIpseMapper jsonIpseMapper;

    public StaticJsonIPSE() {
        super("JsonIPSE.properties");
    }

    public boolean authenticate(HttpServletRequest request) {
        mapJson("ipseJSON", request);
        // Perform something here with the IpseHelper POJO
        return true;
    }
}
