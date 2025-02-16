package edu.user.request;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserMethods {

    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String CREATE_USER_URI = "api/auth/register";
    public static final String LOGIN_USER_URI = "api/auth/login";
    public static final String CHANGE_USER_DATA_URI = "api/auth/user";
    public static final String DELETE_USER_URI = "api/auth/user";

    protected RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured())
                .log().all();
    }


    @Step("Создаем пользователя")
    public ValidatableResponse createUser(UserSerialization user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(CREATE_USER_URI)
                .then();
    }

    @Step("Логинимся пользователем")
    public ValidatableResponse loginUser(UserSerialization user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(LOGIN_USER_URI)
                .then();
    }

    @Step("Удаляем пользователя")
    public ValidatableResponse deleteUser(String accessToken){
        return given()
                .spec(requestSpecification())
                .header("Authorization",accessToken)
                .and()
                .when()
                .delete(DELETE_USER_URI)
                .then();
    }
}
