package stepdefinitions;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class CitySteps {

    @When("он кликает на кнопку {string}")
    public void clickChangeCityButton(String buttonText) {
        $(By.xpath("//span[@data-wba-header-name='DLV_Adress']")).click();

    }

    @When("в поисковую строку вводит {string}")
    public void enterCity(String city) {
        $(By.cssSelector(".ymaps-2-1-79-searchbox-input__input")).setValue("Санкт-Петербург").pressEnter();
    }

    @When("выбирает первый адрес из списка адресов")
    public void selectFirstAddress() {
        $$(".address-item.j-poo-option").first().click();
    }

    @Then("открылась информация о центре выдачи")
    public void centerInformationOpened() {
        SelenideElement info = $(".details-self__name-text");
        assertTrue(info.isDisplayed());
    }

    @Then("адрес пункта выдачи совпадает с тем адресом, что был предложен в списке адресов")
    public void addressMatches() {
        SelenideElement addressElement = $(".details-self__name-text");

        addressElement.shouldHave(text("Санкт-Петербург, Санкт-Петербург, Спасский переулок, 9"));
    }

    @When("он нажимает на кнопку {string}")
    public void selectAddressButton(String buttonText) {
        $(By.cssSelector("button.details-self__btn.btn-main")).click();
    }

    @Then("отображается адрес пункта выдачи из предыдущего шага")
    public void addressDisplays() {
        $(By.xpath("//span[@class='simple-menu__link simple-menu__link--address j-geocity-link j-wba-header-item' and text()='Санкт-Петербург, Санкт-Петербург, Спасский переулок, 9']"))
                .shouldBe(visible);
    }
}
