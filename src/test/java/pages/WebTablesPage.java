package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.*;

public class WebTablesPage extends BasePage {

    private final String base = "https://demoqa.com/webtables";

    public void openPage() {
        openUrl(base);
    }

    public void clickAddButton() {
        $(By.id("addNewRecordButton")).shouldBe(Condition.visible).click();
    }

    public void fillForm(String firstName, String lastName, String email, String age, String salary, String department) {
        $(By.id("firstName")).shouldBe(Condition.visible).setValue(firstName);
        $(By.id("lastName")).setValue(lastName);
        $(By.id("userEmail")).setValue(email);
        $(By.id("age")).setValue(age);
        $(By.id("salary")).setValue(salary);
        $(By.id("department")).setValue(department);
        $(By.id("submit")).click();
    }

    public boolean isRecordPresentByEmail(String email) {
        ElementsCollection rows = $$("div.rt-tbody div.rt-tr-group");
        return rows.stream().anyMatch(r -> r.getText().contains(email));
    }

    public void editRecordByEmail(String email, String newFirstName) {
        ElementsCollection rows = $$("div.rt-tbody div.rt-tr-group");
        var row = rows.stream().filter(r -> r.getText().contains(email)).findFirst()
                .orElseThrow(() -> new RuntimeException("Kayıt bulunamadı: " + email));
        row.$(By.cssSelector("span[title='Edit']")).shouldBe(Condition.visible).click();
        $(By.id("firstName")).shouldBe(Condition.visible).clear();
        $(By.id("firstName")).setValue(newFirstName);
        $(By.id("submit")).click();
    }

    public boolean isFirstNameForEmail(String email, String expectedFirstName) {
        ElementsCollection rows = $$("div.rt-tbody div.rt-tr-group");
        var row = rows.stream().filter(r -> r.getText().contains(email)).findFirst();
        if (row.isEmpty()) return false;
        return row.get().getText().contains(expectedFirstName);
    }
}
