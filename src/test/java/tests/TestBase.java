package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        pageLoadStrategy = "eager";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        RestAssured.baseURI = "https://demoqa.com";
        baseUrl = "https://demoqa.com";
        remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }
}
