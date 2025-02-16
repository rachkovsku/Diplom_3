package edu.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

    public class ForgotPasswordPage extends BaseMethods {

        public ForgotPasswordPage(WebDriver driver) {
            this.driver = driver;
        }

        //URLs
        public static final String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

        //xpaths
        private By loginLink = By.xpath(".//a[contains(@href, 'login')]");


        @Step("Находим ссылку вход и кликаем")
        public void clickLoginLink() {
            allElementsArePresent();
            findAndClick(loginLink);
        }

        @Step("Открывает страницу формы 'Восстановление пароля'")
        public ForgotPasswordPage openPage() {
            driver.get(FORGOT_PASSWORD_URL);
            return this;
        }

        @Step("Проверка отображения всех элементов")
        public void allElementsArePresent() {
            List<By> locators = List.of(loginLink);
            for (int i = 0; i < locators.size(); i++) {
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(locators.get(i)));
            }
        }
    }

