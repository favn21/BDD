package stepdefinitions;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.WebDriverConfig;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


public class BrowseSteps {

    @Given("пользователь на главной странице Wildberries")
    public void userOnHomePage() {
        WebDriverConfig.setUp();
        Selenide.open("https://www.wildberries.ru/");
    }
    @When("он нажимает {string}")
    public void clickFilter(String filterName) {
        $("button.nav-element__burger.j-menu-burger-btn.j-wba-header-item").click();
    }
}
