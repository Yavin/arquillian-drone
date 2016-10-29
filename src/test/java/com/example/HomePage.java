package com.example;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("")
public class HomePage {

    @FindBy(id = "greet")
    private WebElement greet;

    public String getGreet() {
        return greet.getText();
    }
}
