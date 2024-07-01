package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    private static final SelenideElement tableWithBooksInProfilePage = $(".rt-tbody");
    private static final String nameBook = "Learning JavaScript Design Patterns";
    private static final SelenideElement basketForDelete = $("#delete-record-undefined");
    private static final SelenideElement modalApprove = $("#closeSmallModal-ok");


    @Step("Открыть страницу /profile")
    public void openPage(){
        open("/profile");
    }

    @Step("Проверить наличие книги в профиле пользователя")
    public void checkBooks(){
        tableWithBooksInProfilePage.shouldHave(text(nameBook));
    }

    @Step("Удалить книгу на странице профиля")
    public void deleteBook(){
        basketForDelete.click();
        modalApprove.click();
    }

    @Step("Проверить отсутствие книги на странице профиля")
    public void checkIsNotVisibleBook(){
        tableWithBooksInProfilePage.shouldNotHave(text(nameBook));
    }

}
