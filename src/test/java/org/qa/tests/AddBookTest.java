package org.qa.tests;

import org.qa.pages.CartPage;
import org.qa.pages.CheckoutPage;
import org.qa.pages.HomePage;
import org.qa.utilities.WebBaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddBookTest extends WebBaseTest {

    final String testBook = "Thinking in HTML";
    final HomePage homePage = new HomePage();
    final CartPage cartPage = new CartPage();
    final CheckoutPage checkoutPage = new CheckoutPage();

    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 0)
    public void addBookToCartFromHome() {
        String resultBookTitle = homePage.isItemTitleDisplayed(testBook);
        softAssert.assertEquals(resultBookTitle.contains(testBook), true);
        boolean isPriceDisplayed = homePage.isItemPricesDisplayed();
        softAssert.assertEquals(isPriceDisplayed, true);
        boolean isBookAddedToBasket = homePage.addItemToBasket(testBook);
        softAssert.assertEquals(isBookAddedToBasket, true);
        boolean isShopCartClicked = homePage.clickOnShopCart();
        softAssert.assertEquals(isShopCartClicked, true);
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void checkShopCart() {
        boolean isApplyCouponVisible = cartPage.isApplyCouponVisible();
        softAssert.assertEquals(isApplyCouponVisible, true);
        boolean isBookAddedToCart = cartPage.isBookTitleVisible(testBook);
        softAssert.assertEquals(isBookAddedToCart, true);
        boolean isBookPriceVisible = cartPage.isBookPriceVisible();
        softAssert.assertEquals(isBookPriceVisible, true);
        boolean isBookQVisible = cartPage.isBookQuantityVisible();
        softAssert.assertEquals(isBookQVisible, true);
        boolean isFinalPriceVisible = cartPage.isTotalPriceVisible();
        softAssert.assertEquals(isFinalPriceVisible, true);
        boolean isProceedToCheckoutClicked = cartPage.clickOnProceedToCheckout();
        softAssert.assertEquals(isProceedToCheckoutClicked, true);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void checkCheckoutForm(){
        boolean isFormDisplayed = checkoutPage.isFormDisplayed();
        softAssert.assertEquals(isFormDisplayed, true);
        boolean isBillingHeaderDisplayed = checkoutPage.isBillingHeaderDisplayed();
        softAssert.assertEquals(isBillingHeaderDisplayed, true);
        softAssert.assertAll();
    }
}
