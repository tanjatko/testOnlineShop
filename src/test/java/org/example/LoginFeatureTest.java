package org.example;

import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;

public class LoginFeatureTest {
    String url = "";
    String registeredEmail = "";
    String password = "";
    String userName = "";

    @BeforeEach
    public void startUp() throws Exception {

        File file = new File("test.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        url = properties.getProperty("url");
        registeredEmail = properties.getProperty("registeredEmail");
        password = properties.getProperty("password");
        userName = properties.getProperty("userName");
    }
    @Test
    public void login() {
        open(url);
        new MainPage().goToSignInPage();
        LoginPage loginPage = new LoginPage();
        loginPage.getAuthenticationField().isDisplayed();
        loginPage.setEmail(registeredEmail);
        loginPage.setPasswdField(password);
        loginPage.pressSubmitButton();
        AccountPage accountPage = new AccountPage();
        accountPage.getAccountField().isDisplayed();
        accountPage.getAccountNameField().shouldHave(Condition.text(userName));
    }
//    @ParameterizedTest
//    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
//    void palindromes(String candidate) {
//        assertTrue(StringUtils.isPalindrome(candidate));
//    }

    @ParameterizedTest
    @ValueSource (strings = {"1222222"," ","~!@##$%%^&^&**(","/r"})
    public void loginWithIncorrectPassword(String incorrectPassword){
        open(url);
        new MainPage().goToSignInPage();
        LoginPage loginPage = new LoginPage();
        loginPage.setEmail(registeredEmail);
        loginPage.setPasswdField(incorrectPassword);
        loginPage.pressSubmitButton();
        loginPage.getAuthenticationField().isDisplayed();// we are on the same page
        loginPage.getErrorField().shouldHave(Condition.text("Authentication failed"));
    }

    @Test
    public void loginWithIncorrectEmail(){
        open(url);
        new MainPage().goToSignInPage();
        LoginPage loginPage = new LoginPage();
        loginPage.setEmail("nnn@ukr.net");
        loginPage.setPasswdField("1234567");
        loginPage.pressSubmitButton();
        loginPage.getAuthenticationField().isDisplayed();// we are on the same page
        loginPage.getErrorField().shouldHave(Condition.text("Authentication failed"));
    }

    @Test
    public void logout(){
        login();
        new AccountPage().getSignoutField().pressEnter();
        new LoginPage().getAuthenticationField().isDisplayed();
    }
}
