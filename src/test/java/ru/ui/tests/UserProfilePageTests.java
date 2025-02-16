package ru.ui.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import static edu.page.objects.LoginPage.LOGIN_PAGE_URL;
import static edu.page.objects.MainPage.MAIN_PAGE_URL;
import static edu.page.objects.UserProfilePage.PROFILE_PAGE_URL;

public class UserProfilePageTests extends BaseTest {

    @Test
    @DisplayName("Переход на страницу профиля")
    @Description("Для зарегистрированного пользователя")
    public void goToProfilePageTest(){

        String generatedEmail = randomizer.generateEmail(6);
        String generatedUserName = randomizer.generateUserName(6);
        String generatedPassword = randomizer.generatePassword(6);
        setGeneratedData(generatedEmail ,generatedPassword);

        registerPage.openRegisterPage();
        registerPage.registerUser(generatedUserName, generatedEmail,generatedPassword);
        loginPage.loginIn(generatedEmail,generatedPassword);
        mainPage.clickAccountLink();

        softAssertions.assertThat(profilePage.getProfileText()).isEqualTo("Профиль");
        softAssertions.assertThat(driver.getCurrentUrl()).isEqualTo(PROFILE_PAGE_URL);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу ")
    @Description("Через ссылку Конструктор")
    public void goToMainPageFromProfilePageViaConstructorButton(){

        String generatedEmail = randomizer.generateEmail(6);
        String generatedUserName = randomizer.generateUserName(6);
        String generatedPassword = randomizer.generatePassword(6);
        setGeneratedData(generatedEmail ,generatedPassword);

        registerPage.openRegisterPage();
        registerPage.registerUser(generatedUserName, generatedEmail,generatedPassword);
        loginPage.loginIn(generatedEmail,generatedPassword);
        mainPage.clickAccountLink();
        profilePage.clickConstructorLink();

        Assert.assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета на главную страницу")
    @Description("Через ссылку 'Лого'")
    public void goToMainPageFromProfilePageViaLogoLink(){

        String generatedEmail = randomizer.generateEmail(6);
        String generatedUserName = randomizer.generateUserName(6);
        String generatedPassword = randomizer.generatePassword(6);
        setGeneratedData(generatedEmail ,generatedPassword);

        registerPage.openRegisterPage();
        registerPage.registerUser(generatedUserName, generatedEmail,generatedPassword);
        loginPage.loginIn(generatedEmail,generatedPassword);
        mainPage.clickAccountLink();
        profilePage.clickBurgerLogoLink();

        Assert.assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход из профиля")
    @Description("Через кнопку 'Выйти'")
    public void logOutViaLogOutButtonFromProfilePageTest(){

        String generatedEmail = randomizer.generateEmail(6);
        String generatedUserName = randomizer.generateUserName(6);
        String generatedPassword = randomizer.generatePassword(6);
        setGeneratedData(generatedEmail ,generatedPassword);

        registerPage.openRegisterPage();
        registerPage.registerUser(generatedUserName, generatedEmail,generatedPassword);
        loginPage.loginIn(generatedEmail,generatedPassword);
        mainPage.clickAccountLink();
        profilePage.clickLogOutButton();
        loginPage.allElementsArePresent();

        Assert.assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
