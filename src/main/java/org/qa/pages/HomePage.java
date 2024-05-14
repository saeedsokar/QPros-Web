package org.qa.pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends AbstractPage {

    private final String bookTitleLocator = "//a[h3[.='%s']]";
    private final String basketLocator = "/following-sibling::a[.='Add to basket']";
    private By addToBasketLocator;
    //    private final By addToBasketLocator = By.xpath("//li[@class='post-163 product type-product status-publish has-post-thumbnail product_cat-html product_tag-html has-post-title no-post-date has-post-category has-post-tag has-post-comment has-post-author first instock sale downloadable taxable shipping-taxable purchasable product-type-simple']/a[.='Add to basket']");
    private final By itemPrice = By.xpath(".//span//span[@class='woocommerce-Price-amount amount']");
    private final By shopCartButton = By.xpath("//a[.='View Basket']");
    private WebElement book;

    public String isItemTitleDisplayed(String bookTitle) {
        try {
            By bookName = By.xpath(String.format(bookTitleLocator, bookTitle));
            scrollToFindElement(bookName);
            book = waitForVisibilityOf(bookName);
            logger.log(LogStatus.INFO, "Book title is : " + book.getText());
            return book.getText();
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "Book title failed to get displayed due to: " + ex.getMessage());
            return "";
        }
    }

    public boolean isItemPricesDisplayed() {
        try {
            List<WebElement> itemPriceList = findElementList(book, itemPrice);
            logger.log(LogStatus.INFO, "Item price list size: " + itemPriceList.size());
            if (itemPriceList.isEmpty()) {
                logger.log(LogStatus.INFO, "Item price not found");
                return false;
            } else {
                for (WebElement element : itemPriceList) {
                    logger.log(LogStatus.INFO, "Price before: " + element.getText());
                }
                return true;
            }
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "Item title failed due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean addItemToBasket(String testBookTitle) {
        try {
//            clickOnButton(book, addToBasketLocator);
            addToBasketLocator = By.xpath(String.format(bookTitleLocator, testBookTitle) + basketLocator);
            scrollToFindElement(addToBasketLocator);
            clickOnButton(addToBasketLocator);
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to add to basket due to: " + ex.getMessage());
            return false;
        }
    }

    public boolean clickOnShopCart() {
        try {
            clickOnButton(shopCartButton);
            logger.log(LogStatus.INFO, "proceed to cart Page");
            return true;
        } catch (Exception ex) {
            logger.log(LogStatus.ERROR, "failed to click on shop cart button due to: " + ex.getMessage());
            return false;
        }
    }
}
