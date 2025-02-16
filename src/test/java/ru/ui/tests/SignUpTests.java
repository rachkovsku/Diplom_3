package ru.ui.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTests extends BaseTest {

    @Test
    @DisplayName("Проверка регистрации")
    @Description("Проверка регистрации с валидными данными")
    public void successfulRegisterTest(){

        String generatedEmail = randomizer.generateEmail(6);
        String generatedUserName = randomizer.generateUserName(6);
        String generatedPassword = randomizer.generatePassword(6);
        setGeneratedData(generatedEmail ,generatedPassword);

        registerPage.openRegisterPage();
        registerPage.registerUser(generatedUserName, generatedEmail,generatedPassword);
        loginPage.loginIn(generatedEmail,generatedPassword);
        mainPage.clickAccountLink();

        softAssertions.assertThat(generatedEmail).isEqualTo(profilePage.getEmail());
        softAssertions.assertThat(generatedUserName).isEqualTo(profilePage.getUserName());
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Сообщение об ошибке при вводе пароля")
    @Description("Появление ошибки при вводе менее 6сти символов")
    public void incorrectPasswordTest(){

        String generatedEmail = randomizer.generateEmail(6);
        String generatedUserName = randomizer.generateUserName(6);
        String generatedPassword = randomizer.generatePassword(3);
        setGeneratedData(generatedEmail ,generatedPassword);

        loginPage.openLoginPage();
        loginPage.clickRegisterButton();
        registerPage.registerUser(generatedUserName, generatedEmail,generatedPassword);

        Assert.assertEquals("Некорректный пароль", registerPage.incorrectPasswordMessage());
    }
}
