package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfileListPage;
import pages.UserAvatarPage;
import utils.ConfigProperties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class UserAvatar {
    public static LoginPage loginPage;
    public static UserAvatarPage userAvatarPage;
    public static ProfileListPage profileListPage;

    @BeforeTest
    public void setup(){
        loginPage = new LoginPage();
        profileListPage = new ProfileListPage();
        userAvatarPage = new UserAvatarPage();
        Configuration.browser = ConfigProperties.getTestProperty("useBrowser");
        open(ConfigProperties.getTestProperty("jiraURL"));
        loginPage.enterLogin(ConfigProperties.getTestProperty("LoginWebinar5"));
        loginPage.enterPassword(ConfigProperties.getTestProperty("PasswordWebinar5"));
        loginPage.clickSubmitButton();
    }

    @Test
    public void EditUserAvatar(){
        profileListPage.openProfileWindow();
        userAvatarPage.clickOnUserAvatar();
        assertEquals(userAvatarPage.getUserAvatarPopUpTitle(), "Select a User Avatar");
        WebDriverRunner.clearBrowserCache();
    }

    @Test
    public void UploadUserAvatar() throws InterruptedException {
        String valIdAvatar;
        Cookie ck = new Cookie("JSESSIONID", loginPage.jsessionCookie );
        WebDriverRunner.getWebDriver().manage().addCookie(ck);

        open("http://jira.hillel.it:8080/secure/Dashboard.jspa");
        profileListPage.openProfileWindow();
        valIdAvatar = userAvatarPage.getIdAvatar();
        userAvatarPage.uploadNewAvatar();
        SECONDS.sleep(3);
        assertNotEquals(valIdAvatar, userAvatarPage.getIdAvatar());
    }
}
