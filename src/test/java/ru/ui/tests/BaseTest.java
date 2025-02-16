package ru.ui.tests;

import edu.user.request.*;
import edu.Randomizer;
import edu.browser.BrowserSetUp;
import edu.page.objects.*;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    private UserMethods userMethods = new UserMethods();
    private UserSerialization userSerialization;
    private ValidatableResponse response;
    protected String generatedEmail;
    protected String generatedPassword;

    MainPage mainPage;
    LoginPage loginPage;
    SignUpPage registerPage;
    UserProfilePage profilePage;
    ForgotPasswordPage forgotPasswordPage;
    Randomizer randomizer;
    SoftAssertions softAssertions;
    BrowserSetUp browser = new BrowserSetUp();



    @Before
    public void startUp() throws IOException {
        driver = BrowserSetUp.initDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new SignUpPage(driver);
        profilePage = new UserProfilePage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        randomizer = new Randomizer();
        softAssertions = new SoftAssertions();
    }

    @After
    public void tearDown(){
        deleteUser();
        driver.quit();
    }

    public void deleteUser(){
        userSerialization = new UserSerialization(generatedEmail,generatedPassword);
        response = userMethods.loginUser(userSerialization);
        String accessToken = response.extract().jsonPath().getString("accessToken");

        if (accessToken == null || accessToken.isEmpty()){
            System.out.println("Message: Токен пустой. Удаление токена производиться не будет");
            return;
        }
        try {
            userMethods.deleteUser(accessToken).log().all();
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя");
            throw new RuntimeException(e);
        }
    }

    public void setGeneratedData(String generatedEmail, String generatedPassword) {
        this.generatedEmail = generatedEmail;
        this.generatedPassword = generatedPassword;
    }
}

