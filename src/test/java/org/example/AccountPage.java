package org.example;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AccountPage {
    private SelenideElement accountField = $x("//*[@id= 'my-account']");
    private SelenideElement accountNameField = $x("//*[@class = 'account']");
    private SelenideElement signoutField = $x("//*[@title = 'Log me out']");

    public SelenideElement getAccountField() { return accountField; }
    public SelenideElement getAccountNameField() { return accountNameField; }
    public SelenideElement getSignoutField() {return signoutField;}

}
