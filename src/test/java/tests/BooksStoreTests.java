package tests;

import io.qameta.allure.Step;
import models.AddBooksReq;
import models.AuthReq;
import models.ResponseAuth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static spec.GeneralSpec.requestSpec;
import static spec.GeneralSpec.responseSpec;

public class BooksStoreTests extends TestBase {

    ResponseAuth response;
    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Удаление книги из профиля пользователя")
    void deleteBookTest() {

        auth();
        addBookToProfile(response);
        profilePage.openPage();
        profilePage.checkBooks();
        profilePage.deleteBook();
        profilePage.checkIsNotVisibleBook();

    }


    @Step("Авторизоваться на сайте")
    void auth() {
        AuthReq authReq = new AuthReq();
        authReq.setUserName("IIvanov");
        authReq.setPassword("Admin1234!");

        response = given()
                .spec(requestSpec)
                .body(authReq)
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(ResponseAuth.class);


        open("/images/WB.svg");

        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires().toString()));
    }


    String getTokenForClient(ResponseAuth response) {
        return response.getToken();
    }

    @Step("Добавить книгу в профиль через API")
    void addBookToProfile(ResponseAuth response) {
        AddBooksReq addBooksReq = new AddBooksReq();
        addBooksReq.setUserId(response.getUserId());
        addBooksReq.addIsbns("9781449331818");

        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + getTokenForClient(response))
                .body(addBooksReq)
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(201);
    }
}
