package pages;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    public BasePage() {
        // Start browser maximized; adjust if CI needs headless
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }

    public void openUrl(String url) {
        open(url);
    }
}
