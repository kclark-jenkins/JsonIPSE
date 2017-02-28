package org.krisbox.ipse.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.krisbox.ipse.junit.tests.DynamicCookieIpseHelperTest;
import org.krisbox.ipse.junit.tests.PojoTests;
import org.krisbox.ipse.junit.tests.StaticCookieIpseHelperTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DynamicCookieIpseHelperTest.class
})
public class AllTests {
}
