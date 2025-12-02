package steps;

import io.cucumber.java.en.*;
import pages.ButtonsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import com.codeborne.selenide.Selenide;

public class ButtonsSteps {

    ButtonsPage buttonsPage = new ButtonsPage();

    @Given("demoqa elements sayfasına gidilir")
    public void open_elements_page() {
        buttonsPage.openPage();
    }

    @When("\"Buttons\" menüsü açılır")
    public void open_buttons_menu() {
        buttonsPage.openButtonsSection();
    }

    @When("\"Click Me\" butonuna tıklanır")
    public void click_click_me() {
        buttonsPage.clickClickMeButton();
    }

    @Then("görünen mesaj doğrulanır")
    public void verify_message() {
        String msg = buttonsPage.getClickMessage().toLowerCase();
        assertThat("Mesaj beklenen içeriği içermelidir", msg, containsString("click"));
        Selenide.closeWebDriver();
    }
}
