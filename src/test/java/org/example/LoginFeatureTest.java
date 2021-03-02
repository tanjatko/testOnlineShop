package org.example;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.open;

public class LoginFeatureTest {

    @Test
    public void login() {
        open("http://automationpractice.com/");
        new MainPage().pressSignIn();
        AuthenticationPage authenPage = new AuthenticationPage();
        authenPage.getAuthenticationField().isDisplayed();
        authenPage.setEmail("ttt@ukr.net");
        authenPage.setPasswdField("1234567");
        authenPage.pressSubmitButton();
        AccountPage accountPage = new AccountPage();
        accountPage.getAccountField().isDisplayed();
        accountPage.getAccountNameField().shouldHave(Condition.text("tetiana bon"));
    }

    @Test
    public void logout(){
        login();
        new AccountPage().getSignoutField().pressEnter();
        new AuthenticationPage().getAuthenticationField().isDisplayed();
    }
}
