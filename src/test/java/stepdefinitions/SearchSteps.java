package stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchSteps {

    @When("он вводит {string} в строку поиска")
    public void userEntersSearchTerm(String searchTerm) {
        $("#searchInput").setValue(searchTerm).pressEnter();
    }

    @Then("он видит текст {string}")
    public void userSeesText(String expectedText) {
        $(By.cssSelector(".searching-results__title")).shouldHave(text("Iphone 13"));
    }

    @Then("первый фильтр - {string}")
    public void firstFilter(String filter) {
        $$x("//div[@class='catalog-page__filters-block']").first().shouldHave(text("По популярности"));
    }

    @Then("применен фильтр {string}")
    public void appliedFilter(String filter) {
        $(By.xpath("//button[contains(text(), 'По популярности')]")).shouldBe(visible);
    }

    @Then("у первого устройства из списка бренд - {string}")
    public void firstDeviceBrand(String brand) {
        $$(".product-card__brand").first().shouldHave(text("Apple"));
    }

    @When("он нажимает на крестик")
    public void userClicksCross() {
        $(By.cssSelector(".search-catalog__btn.search-catalog__btn--clear.search-catalog__btn--active")).click();
    }

    @Then("строка поиска становится пустой")
    public void searchInputIsEmpty() {
        $(By.cssSelector("#searchInput")).shouldHave(value(""));
    }
}