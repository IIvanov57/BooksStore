package helpers;

import io.qameta.allure.Step;
import models.AuthReq;
import models.ResponseAuth;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static spec.GeneralSpec.requestSpec;
import static spec.GeneralSpec.responseSpec;

public class AuthorizationAPI {

    @Step("Авторизоваться на сайте")
    public static ResponseAuth auth() {
        AuthReq authReq = new AuthReq();
        authReq.setUserName("IIvanov");
        authReq.setPassword("Admin1234!");

        ResponseAuth response = given()
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

        return response;
    }
}
