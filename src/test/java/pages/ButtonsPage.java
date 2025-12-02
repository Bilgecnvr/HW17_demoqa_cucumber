package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ButtonsPage extends BasePage {

    private final String base = "https://demoqa.com/elements";

    public void openPage() {
        openUrl(base);
    }

    public void openButtonsSection() {
        $(By.xpath("//span[text()='Buttons']")).shouldBe(Condition.visible).click();
    }

    public void clickClickMeButton() {
        // There are multiple "Click Me" buttons on demoqa; target visible one
        $$("button.btn.btn-primary").get(2).shouldBe(Condition.visible).click();
    }

    public String getClickMessage() {
        if ($(By.id("dynamicClickMessage")).exists()) {
            return $(By.id("dynamicClickMessage")).shouldBe(Condition.visible).getText();
        } else if ($(By.id("clickButton")).exists()) {
            // fallback - sometimes messages use different ids
            return $(By.id("clickButton")).getText();
        } else {
            // find any element that contains 'clicked' or 'Click'
            return $$("p").findBy(Condition.or("containsClick", Condition.text("clicked"), Condition.text("Click"))).getText();
        }
    }
}
