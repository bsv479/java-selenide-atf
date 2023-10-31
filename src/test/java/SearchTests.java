import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class SearchTests {
    Logger logger = LoggerFactory.getLogger(SearchTests.class);

    @Test
    void successfulSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("User opens google page", () -> {
            open("https://www.google.com/");
            attachScreenshot();
        });

        step("User set value 'selenide'", () -> {
            $("[name=q]").setValue("selenide").pressEnter();
        });

        step("Result should have 'https://selenide.org'", () -> {
            $("[id=search]").shouldHave(text("https://selenide.org"));
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
        logger.info("Test finished.");
    }

    @Attachment(value = "Мой любимый скриншот", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
