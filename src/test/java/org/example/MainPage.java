package org.example;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    SelenideElement loginField = $x("//*[@class = 'login']");

    public void goToSignInPage(){
        loginField.pressEnter();
    }
}