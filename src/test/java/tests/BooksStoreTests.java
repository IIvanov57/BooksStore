package tests;

import helpers.AuthorizationAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static helpers.AddBooksApi.addBookToProfileViaAPI;

public class BooksStoreTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Удаление книги из профиля пользователя")
    void deleteBookTest() {

        addBookToProfileViaAPI(AuthorizationAPI.auth());
        profilePage.openPage();
        profilePage.checkBooks();
        profilePage.deleteBook();
        profilePage.checkIsNotVisibleBook();

    }


}
