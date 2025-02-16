package edu.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SignUpPage extends BaseMethods{

    public SignUpPage(WebDriver driver){
        this.driver = driver;
    }

    //URL
    public static final String REGISTER_PAGE = "https://stellarburgers.nomoreparties.site/register";

    //xpaths
    private By inputName = By.xpath(".//label[contains(text(),'Имя')]/following-sibling::input");
    private By inputEmail = By.xpath(".//label[contains(text(),'Email')]/following-sibling::input");
    private By inputPassword = By.xpath(".//label[contains(text(),'Пароль')]/following-sibling::input");
    private By registerButton = By.xpath(".//button[contains(text(),'Зарегистрироваться')]");
    private By incorrectPassoword = By.xpath(".//p[contains(text(),'Некорректный пароль')]");
    private By loginLink = By.xpath(".//a[contains(@href, 'account')]");



    @Step("Регистрация")
    public void registerUser(String generatedNameData, String generatedEmailData, String generatedPasswordData){
        allElementsArePresent();
        enterData(inputName,generatedNameData);
        enterData(inputEmail,generatedEmailData);
        enterData(inputPassword,generatedPasswordData);
        findAndClick(registerButton);
    }

    @Step("Открыть страницу регистрации")
    public SignUpPage openRegisterPage(){
        driver.get(REGISTER_PAGE);
        return this;
    }

    @Step("Сообщение о неверном пароле")
    public String incorrectPasswordMessage(){
        allElementsArePresent();
        return getText(incorrectPassoword);
    }

    @Step("Кликнуть по ЛК")
    public void loginLink(){
        allElementsArePresent();
        findAndClick(loginLink);
    }

    @Step("Проверка отображения всех элементов")
    public void allElementsArePresent (){
        List<By> locators = List.of(inputName, inputEmail, inputPassword, registerButton, loginLink);
        for (int i = 0; i < locators.size(); i++) {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(locators.get(i)));
        }
    }
}
