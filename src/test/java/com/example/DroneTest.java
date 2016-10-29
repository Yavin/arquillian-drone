package com.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.File;

@RunAsClient
@RunWith(Arquillian.class)
public class DroneTest {

    private static final String WEBAPP_SRC = "src/main/webapp/";

    @Drone
    WebDriver browser;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
            .addClass(ExampleBean.class)
            .addAsWebResource(new File(WEBAPP_SRC, "index.xhtml"))
            .addAsWebInfResource(new File(WEBAPP_SRC + "/WEB-INF", "web.xml"))
            .addAsWebInfResource(
                new StringAsset("<faces-config version=\"2.2\"/>"),
                "faces-config.xml"
            )
        ;
    }

    @Test
    public void testDataTableRendered(@InitialPage HomePage homePage) {
        Assert.assertEquals("Drone example", browser.getTitle());
        Assert.assertEquals("hello!", homePage.getGreet());
    }
}
