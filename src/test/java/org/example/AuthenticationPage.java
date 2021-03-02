package org.example;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class AuthenticationPage {
    private SelenideElement authenticationField = $x("//*[@id = 'authentication']");
    private SelenideElement emailField = $x("//*[@id ='email']");
    private SelenideElement passwdField = $x("//*[@id='passwd']");
    private SelenideElement submitButton = $x("//button[@id ='SubmitLogin']");

    public void setEmail(String email) { emailField.setValue(email); }
    public void setPasswdField (String passwd) { passwdField.setValue(passwd); }
    public void pressSubmitButton () { submitButton.pressEnter(); }
    public SelenideElement getAuthenticationField() { return authenticationField; }

}



