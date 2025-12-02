package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.WebTablesPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import com.codeborne.selenide.Selenide;

import java.util.List;
import java.util.Map;

public class WebTablesSteps {

    WebTablesPage webTablesPage = new WebTablesPage();

    @Given("demoqa webtables sayfasına gidilir")
    public void open_webtables() {
        webTablesPage.openPage();
    }

    @When("\"ADD\" ile yeni kayıt eklenir")
    public void add_new_record(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> r = rows.get(0);
        webTablesPage.clickAddButton();
        webTablesPage.fillForm(
                r.get("firstName"),
                r.get("lastName"),
                r.get("email"),
                r.get("age"),
                r.get("salary"),
                r.get("department")
        );
    }

    @When("eklenen kayıt {string} ile bulunup düzenlenir")
    public void edit_record_by_email(String email, io.cucumber.datatable.DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        String newFirstName = rows.get(0).get("firstName");
        boolean exists = webTablesPage.isRecordPresentByEmail(email);
        assertThat("Kayıt eklenmiş olmalı", exists, is(true));
        webTablesPage.editRecordByEmail(email, newFirstName);
    }

    @Then("kayıtta değişiklik {string} olarak görünmelidir")
    public void verify_changed_firstname(String expectedFirstName) {
        String email = "bilge@test.com";
        boolean ok = webTablesPage.isFirstNameForEmail(email, expectedFirstName);
        assertThat("FirstName güncellenmiş olmalı", ok, is(true));
        Selenide.closeWebDriver();
    }
}
