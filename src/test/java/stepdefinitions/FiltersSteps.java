package stepdefinitions;

import com.codeborne.selenide.CollectionCondition;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FiltersSteps {

    @When("выбирает фильтр {string}")
    public void selectFilter(String filterPath) {
        $x("//span[contains(@class, 'menu-burger__main-list-link') and text()='Электроника']").click();
        $x("//span[contains(@class, 'menu-burger__link--next') and text()='Ноутбуки и компьютеры']").click();
        $x("//a[contains(@class, 'menu-burger__link') and contains(@href, 'noutbuki-ultrabuki')]").click();
    }

    @Then("открылась страница с ноутбуками")
    public void laptopsPageOpened() {
        $(By.cssSelector(".catalog-title")).shouldHave(text("Ноутбуки и ультрабуки"));
    }

    @When("он примеряет фильтры как показано на скриншоте")
    public void applyFilters() {
        $("button.dropdown-filter__btn.dropdown-filter__btn--all").click();
        $x("//input[@name='startN']").setValue("100000");
        $x("//input[@name='endN']").setValue("149000");
        $x("//div[contains(@class, 'checkbox-with-text') and .//span[contains(text(), 'Apple')]]").click();
        $x("//span[contains(@class, 'radio-with-text__decor') and following-sibling::span[contains(text(), 'до 5 дней')]]").click();
        $x("//span[contains(@class, 'checkbox-with-text__text') and contains(text(), '13.3')]").click();
    }

    @When("нажимает кнопку {string}")
    public void showButton(String buttonText) {
        $x("//button[contains(@class, 'filters-desktop__btn-main') and contains(text(), 'Показать')]").click();
    }

    @Then("фильтр активировался")
    public void filterActivated() {
        $(".dropdown-filter__count").shouldBe(visible).shouldHave(text("4"));

    }

    @Then("количество товара на странице равно количеству товара на странице")
    public void itemCount() {
        String totalItemsText = $x("//span[contains(@class, 'goods-count')]/span").getText();
        int totalItems = Integer.parseInt(totalItemsText.replaceAll("[^0-9]", ""));
        $$("div.product-card__wrapper").shouldHave(CollectionCondition.size(totalItems));
    }

    @Then("выбранные фильтры отображаются на странице")
    public void filtersDisplayed() {
        $x("//span[@class='your-choice__btn' and contains(text(), 'от 100 000 до 149 000')]").shouldBe(visible);
        $x("//span[@class='your-choice__btn' and contains(text(), 'до 5 дней')]").shouldBe(visible);
        $x("//span[@class='your-choice__btn' and contains(text(), 'Apple')]").shouldBe(visible);
        $x("//span[@class='your-choice__btn' and contains(text(), '13.3')]").shouldBe(visible);
    }

    @Then("появилась кнопка {string}")
    public void resetButton(String buttonText) {
        $x("//li[contains(@class, 'your-choice__item--reset')]//button[contains(@class, 'your-choice__btn') and contains(text(), 'Сбросить все')]").shouldBe(visible);
    }
}