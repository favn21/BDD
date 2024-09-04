package stepdefinitions;

import com.codeborne.selenide.*;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartSteps {


    @When("выбирает {string}")
    public void selectFilterOption(String filterOption) {
        sleep(40000);
        $x("//span[contains(@class, 'menu-burger__main-list-link') and text()='Бытовая техника']").click();
        $x("//span[contains(@class, 'menu-burger__link--next') and text()='Техника для дома']").click();
        $x("//span[contains(text(), 'Пылесосы и пароочистители')]").click();
        $x("//a[contains(text(), 'Вертикальные пылесосы')]").click();
    }

    @Then("происходит переход на страницу с бытовой техникой")
    public void userIsOnHomeAppliancePage() {
        String expectedUrl = "https://www.wildberries.ru/catalog/elektronika/tehnika-dlya-doma/pylesosy-i-parootchistiteli/vertikalnye-pylesosy";
        assertTrue(WebDriverRunner.url().contains(expectedUrl));
    }


    @Then("отображается {string}")
    public void filterDisplay(String filterDisplay) {
        $(By.cssSelector(".catalog-title")).shouldHave(text("Вертикальные пылесосы"));
    }

    @Then("Путь фильтра {string}")
    public void filterPath(String filterPath) {
        String[] expectedBreadcrumbs = filterPath.split(" - ");
        ElementsCollection breadcrumbs = $$("ul.breadcrumbs__list li.breadcrumbs__item span[itemprop='name']");
        breadcrumbs.shouldHave(CollectionCondition.size(expectedBreadcrumbs.length));
        for (int i = 0; i < expectedBreadcrumbs.length; i++) {
            breadcrumbs.get(i).shouldHave(Condition.text(expectedBreadcrumbs[i]));
        }
        $$(".product-card").first().shouldBe(Condition.visible).scrollTo();
    }

    @When("он нажимает на кнопку {string}")
    public void addToCart(String buttonText) {
        $(".product-card__add-basket.j-add-to-basket.btn-main").click();

    }

    @Then("в правом верхнем углу над логотипом {string} появилась красная цифра {string}")
    public void cartCount(String cartIcon, String count) {

        $(By.cssSelector("span.navbar-pc__notify")).scrollTo().shouldHave(text("1"));

    }

    @When("он нажимает на {string}")
    public void clickCartIcon(String cartIcon) {

        $(By.cssSelector(".navbar-pc__icon--basket")).click();

    }

    @Then("текст и цена товара соответствует цене и названию товара из предыдущих шагов")
    public void verifyCartItem() {
        SelenideElement cartItem = $$(".list-item__wrap").first();
        assertTrue(cartItem.isDisplayed());
    }

    @Then("{string} = сумме товара")
    public void sumEqualsTotal(String label) {
        String totalText = $x("//span[contains(@data-link, 'basketPriceWithCurrencyV2')]")
                .shouldBe(visible)
                .getText()
                .replace("\u00A0", "");

        String sumText = $x("//div[contains(@class, 'list-item__price-new')]")
                .shouldBe(visible)
                .getText()
                .replace("\u00A0", "");

        assertEquals("Итого не соответствует сумме товара!", totalText, sumText);
    }

    @Then("кнопка {string} активна для нажатия")
    public void verifyButton(String buttonText) {
        SelenideElement orderButton = $x("//button[@class='b-btn-do-order btn-main j-btn-confirm-order']");
        orderButton.shouldBe(Condition.visible);
        orderButton.shouldBe(Condition.enabled);
    }
}