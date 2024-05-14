package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends AbstractPage {
    private final By applyCouponLocator = By.name("apply_coupon");
    private final String addedBookToCartLocator = "//a[.='%s']";
    private final By addedPriceToCartLocator = By.xpath("//td[@class='product-price']/span[@class='woocommerce-Price-amount amount']");
    private final By bookQuantityLocator = By.cssSelector(".qty");
    private final By finalPrice = By.xpath("//th[.='Total']/following-sibling::td//span[@class='woocommerce-Price-amount amount']");
    private final By proceedToCheckoutLocator = By.cssSelector(".checkout-button");

    public boolean isApplyCouponVisible() {
        try {
            WebElement applyCoupon = waitForVisibilityOf(applyCouponLocator);
            logger.log(LogStatus.INFO, "Coupon appears with title: " + applyCoupon.getText());
            return applyCoupon.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get apply coupon displayed due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean isBookTitleVisible(String bookTitle) {
        try {
            WebElement bookItem = waitForVisibilityOf(By.xpath(String.format(addedBookToCartLocator, bookTitle)));
            logger.log(LogStatus.INFO, "Book appears with title: " + bookItem.getText());
            return bookItem.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get Book title displayed due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean isBookPriceVisible() {
        try {
            WebElement bookPrice = waitForVisibilityOf(addedPriceToCartLocator);
            logger.log(LogStatus.INFO, "Book price with value: " + bookPrice.getText());
            return bookPrice.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get Book price displayed due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean isBookQuantityVisible() {
        try {
            WebElement bookQ = waitForVisibilityOf(bookQuantityLocator);
            logger.log(LogStatus.INFO, "Total price with value: " + bookQ.getText());
            return bookQ.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get Book quantity displayed due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean isTotalPriceVisible() {
        try {
            WebElement total = waitForVisibilityOf(finalPrice);
            logger.log(LogStatus.INFO, "Total price with value: " + total.getText());
            return total.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get Final price displayed due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean clickOnProceedToCheckout() {
        try {
            clickOnButton(proceedToCheckoutLocator);
            logger.log(LogStatus.INFO, "proceed to checkout");
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to click on shop cart button due to: " + ex.getMessage());
            return false;
        }
    }
}
