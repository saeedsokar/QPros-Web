package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends AbstractPage {
    private final By applyCouponLocator = By.name("apply_coupon");
    private final String addedBookToCartLocator = "//a[.='%s']";
    private final By addedPriceToCartLocator = By.xpath("//td[@class='product-price']/span[@class='woocommerce-Price-amount amount']");
    private final By bookQuantityLocator = By.cssSelector(".qty");
    private final By proceedToCheckoutLocator = By.cssSelector(".checkout-button");

    public boolean isApplyCouponVisible() {
        try {
            WebElement applyCoupon = waitForVisibilityOf(applyCouponLocator);
            logger.log(LogStatus.INFO,"Coupon appears with title: " + applyCoupon.getText());
            return applyCoupon.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get apply coupon displayed due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean isBookTitleVisible(String bookTitle) {
        try {
            return waitForVisibilityOf(By.xpath(String.format(addedBookToCartLocator, bookTitle))).isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get apply coupon displayed due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean isBookPriceVisible() {
        try {
            return waitForVisibilityOf(addedPriceToCartLocator).isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get apply coupon displayed due to: " + ex.getMessage());
            return false;
        }
    }
}
