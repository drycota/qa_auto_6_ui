package pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPage {

    public static String jsessionCookie;
    private String loginPageURL = "http://jira.hillel.it:8080/login.jsp";

    public void enterLogin(String login){
        $(By.id("login-form-username")).sendKeys(login);
    }

    public void enterPassword(String password){
        $(By.id("login-form-password")).setValue(password);
    }

    public void clickSubmitButton(){
        $(By.id("login-form-submit")).click();
        jsessionCookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("JSESSIONID").getValue();
    }

    public boolean atRequiredPage(){
        return url().equalsIgnoreCase(loginPageURL);
    }

    public void navigate(){
        open(loginPageURL);
    }
}
