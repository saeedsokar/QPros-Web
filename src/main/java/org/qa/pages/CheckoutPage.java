package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends AbstractPage{

    private final By formLocator = By.name("checkout");
    private final By billingDetailsTitleLocator = By.cssSelector(".woocommerce-billing-fields > h3");

    public boolean isFormDisplayed() {
        try {
            WebElement form = waitForVisibilityOf(formLocator);
            logger.log(LogStatus.INFO, "form appearing with value: " + form.toString());
            logger.log(LogStatus.INFO, "form displayed: " + form.isDisplayed());
            return form.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get form displayed due to: " + ex.getMessage());
            return false;
        }
    }
    public boolean isBillingHeaderDisplayed() {
        try {
            WebElement billingHeader = waitForVisibilityOf(billingDetailsTitleLocator);
            logger.log(LogStatus.INFO, "Billing Header appearing with value: " + billingHeader.getText());
            logger.log(LogStatus.INFO, "billing Header displayed: " + billingHeader.isDisplayed());
            return billingHeader.isDisplayed();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to get billing Header displayed due to: " + ex.getMessage());
            return false;
        }
    }

}
