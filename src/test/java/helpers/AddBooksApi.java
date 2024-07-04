package helpers;

import io.qameta.allure.Step;
import models.AddBooksReq;
import models.ResponseAuth;


import static helpers.AuthorizationAPI.getTokenForClient;
import static io.restassured.RestAssured.given;
import static spec.GeneralSpec.requestSpec;
import static spec.GeneralSpec.responseSpec;

public class AddBooksApi {
    @Step("Добавить книгу в профиль через API")
    public static void addBookToProfileViaAPI(ResponseAuth response, String idBook) {
        AddBooksReq addBooksReq = new AddBooksReq();
        addBooksReq.setUserId(response.getUserId());
        addBooksReq.addIsbns(idBook);

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
