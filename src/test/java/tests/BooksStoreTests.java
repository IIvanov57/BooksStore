package tests;

import helpers.AuthorizationAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static helpers.AddBooksApi.addBookToProfileViaAPI;

public class BooksStoreTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();
    private static final String idBook = "9781449331818";
    private static final String userName = "IIvanov";
    private static final String userPassword = "Admin1234!";

    @Test
    @DisplayName("Удаление книги из профиля пользователя")
    void deleteBookTest() {

        addBookToProfileViaAPI(AuthorizationAPI.auth(userName,userPassword),idBook);
        profilePage.openPage();
        profilePage.checkBooks();
        profilePage.deleteBook();
        profilePage.checkIsNotVisibleBook();

    }

}
