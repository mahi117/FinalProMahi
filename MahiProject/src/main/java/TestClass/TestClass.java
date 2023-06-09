package TestClass;
import java.lang.*;

import DriverClass.DriverClass;
import UtilClass.UtilClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestClass extends DriverClass {

    public static Properties prop = new Properties();
    public Actions actions;
    public TestClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public TestClass() {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "testData" + File.separator + "config.properties");
            prop.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @Test(testName = "TC-1", priority = 1, groups = {"regression"})
    public void verifyItemIsAddedAndAmountAvailability() {
        String title = driver.getTitle();
        System.out.println(title);
        driver.get(prop.getProperty("url"));
        LandingPage.VerifyLandingDisplayed();
        LandingPage.ClickOnBuyNowButton();
        Assert.assertTrue(LandingPage.VerifyMPPIsDisplayed());
    }

    @Test(testName = "TC-2", priority = 2, groups = {"regression", "smoke"})
    public void VerifyClicking_BuyNowButton_redirectUserToCheckoutPopup() {
        LandingPage.ClickOnBuyNowButton();
        LandingPage.ClickCOButton();
    }
    @Test(testName = "TC-3", priority = 3, groups = {"regression"})
    public void VerifyName_Email_Phone_City_Address_Postalcode_On_CheckoutPage() {
        LandingPage.ClickOnBuyNowButton();
        LandingPage.VerifyNameIsDisplayed();
        //System.out.println(homePage.NameOnCheckoutPage.getAttribute("value"));
        LandingPage.Verify_Email_IsDisplayed();
        //System.out.println(homePage.EmailOnCheckoutPage.getAttribute("value"));
        LandingPage.Verify_Phone_IsDisplayed();
        //System.out.println(homePage.PhoneOnCheckoutPage.getAttribute("value"));
        LandingPage.Verify_City_IsDisplayed();
        //System.out.println(homePage.CityOnCheckoutPage.getAttribute("value"));
        LandingPage.Verify_Address_IsDisplayed();
        //System.out.println(homePage.AddressOnCheckoutPage.getAttribute("value"));
    }
    @Test(testName = "TC-4", priority = 4, groups = {"regression"})
    public void Verify_Customer_Details_Are_Editable() {
        LandingPage.ClickOnBuyNowButton();
        LandingPage.clickClearAndType(LandingPage.NameOnCheckoutPage, prop.getProperty("name"));
        LandingPage.clickClearAndType(LandingPage.EmailOnCheckoutPage, prop.getProperty("email"));
        LandingPage.clickClearAndType(LandingPage.PhoneOnCheckoutPage, prop.getProperty("phone"));
        LandingPage.clickClearAndType(LandingPage.CityOnCheckoutPage, prop.getProperty("city"));
        LandingPage.clickClearAndType(LandingPage.AddressOnCheckoutPage, prop.getProperty("address"));
        LandingPage.clickClearAndType(LandingPage.PostalCodeOnCheckoutPage, prop.getProperty("postcode"));
        LandingPage.ClickCOButton();
    }
    @Test(testName = "TC-5", priority = 5, groups = {"regression", "smoke"})
    public void Verify_clicking_on_CheckoutButton_redirect_User_on_Order_SummaryPopup() {
        LandingPage.ClickOnBuyNowButton();
        LandingPage.ClickCOButton();
        driver.switchTo().frame("snap-midtrans");
        System.out.println(LandingPage.DemostorePageLogo.getText());
        LandingPage.total_OrderID_on_popout();
    }
    @Test(testName = "TC-6", priority = 6, groups = {"regression"})
    public void Verify_Product_Details_on_Order_Summery() {
        LandingPage.ClickOnBuyNowButton();
        LandingPage.ClickCOButton();
        //driver.switchTo().frame("snap-midtrans");
        LandingPage.paymentSection();
        LandingPage.OrderSummary();
    }
    @Test(testName = "TC-7", priority = 7, groups = {"smoke", "regression"})
    public void verifySelectPaymentPageFromOrderSummary() {
        LandingPage.ClickOnBuyNowButton();
        LandingPage.ClickCOButton();
        // driver.switchTo().frame("snap-midtrans");
        LandingPage.paymentSection();
        LandingPage.Verify_PaymentSection_IsDisplayed();
    }
    @Test(testName = "TC-8", priority = 8, groups = {"regression"})
    public void verifyPaymentOption_onPaymentPage() {
        LandingPage.ClickOnBuyNowButton();
        LandingPage.ClickCOButton();
        //driver.switchTo().frame("snap-midtrans");
        LandingPage.paymentSection();
        LandingPage.AllPayments();
    }
    @Test(testName = "TC-9", priority = 9, groups = {"smoke", "regression"})
    public void verifyUser_clickOn_CreditDebitCard_payment_method() {
        LandingPage.ClickOnBuyNowButton();
        LandingPage.ClickCOButton();
        //driver.switchTo().frame("snap-midtrans");
        LandingPage.paymentSection();
        LandingPage.ClickOnCreditCardPayment();
        Assert.assertTrue(LandingPage.Verify_CardDetailScreen_IsDisplayed());
    }
    @Test(testName = "TC-10", priority = 10, groups = {"regression"})
    public void verifyOrderAmountAfterApplyingCoupon(){
        LandingPage.ClickOnBuyNowButton();
        LandingPage.ClickCOButton();
        //driver.switchTo().frame("snap-midtrans");
        LandingPage.paymentSection();
        LandingPage.ClickOnCreditCardPayment();
        LandingPage.Verify_orderAmountBeforeAddingCart_is_Displayed();
        LandingPage.clickClearAndType(LandingPage.cardNumberInputField.get(0), prop.getProperty("card.number"));
        LandingPage.amountUpdateAfterApplyingCoupon();
    }
    @Test(testName = "TC-11", priority = 11, groups = {"smoke","regression"})
    public void enterValidCardDetailsAndClickingOnPayButton(){
        LandingPage.ClickOnBuyNowButton();
        LandingPage.ClickCOButton();
        LandingPage.paymentSection();
        LandingPage.ClickOnCreditCardPayment();
        LandingPage.clickClearAndType(LandingPage.cardNumberInputField.get(0), prop.getProperty("card.number"));
        LandingPage.clickClearAndType(LandingPage.expirationDateInputField, prop.getProperty("card.expiry.date"));
        LandingPage.clickClearAndType(LandingPage.cvvInputField, prop.getProperty("card.cvv"));
        LandingPage.PayNow();
    }
    @Test(testName = "TC-12", priority = 12, groups = {"regression"})
    public void clickingOnPayButtonAndVerifyVariousDetails()  {
        wait = new WebDriverWait(driver, 30);
        enterValidCardDetailsAndClickingOnPayButton();
        wait.until(ExpectedConditions.visibilityOf(LandingPage.iFrame2));
        driver.switchTo().frame(LandingPage.iFrame2);
        wait.until(ExpectedConditions.visibilityOf(LandingPage.merchantName));
        Assert.assertEquals(LandingPage.merchantName.getText(),prop.getProperty("merchant.name"));
        Assert.assertEquals(LandingPage.amount.getText(),prop.getProperty("final.amount"));
        Assert.assertEquals(LandingPage.CardNumber.getText(),prop.getProperty("payment.card.number"));
    }
    @Test(testName = "TC-13", priority = 13, groups = {"smoke", "regression"})
    public void verifyClickingOkWithValidOTPRedirectsToOrderSuccessful(){
        wait = new WebDriverWait(driver, 20);
        enterValidCardDetailsAndClickingOnPayButton();
        wait.until(ExpectedConditions.visibilityOf(LandingPage.iFrame2));
        driver.switchTo().frame(LandingPage.iFrame2);
        wait.until(ExpectedConditions.visibilityOf(LandingPage.OTP));
        LandingPage.clickClearAndType(LandingPage.OTP, prop.getProperty("otp"));
        LandingPage.Click_on_OK_Button();
        Assert.assertTrue(LandingPage.Verify_Payment_Successfill_isDisplayed());
    }
    @Test(testName = "TC-14", priority = 14, groups = {"regression"})
    public void verifyClickingOkWithInvalidOTPRedirectsToOrderDeclined(){
        wait = new WebDriverWait(driver, 20);
        enterValidCardDetailsAndClickingOnPayButton();
        UtilClass.holdExecution(10);
        wait.until(ExpectedConditions.visibilityOf(LandingPage.iFrame2));
        driver.switchTo().frame(LandingPage.iFrame2);
        wait.until(ExpectedConditions.visibilityOf(LandingPage.OTP));
        LandingPage.clickClearAndType(LandingPage.OTP, prop.getProperty("invalid.otp"));
        LandingPage.Click_on_OK_Button();
        driver.switchTo().parentFrame();
        Assert.assertTrue(LandingPage.Verify_Payment_Declined_isDisplayed());
    }
    @Test(testName = "TC-15", priority = 15, groups = {"regression"})
    public void verifyClickingCancelRedirectsToOrderDeclined(){
        wait = new WebDriverWait(driver, 20);
        enterValidCardDetailsAndClickingOnPayButton();
        wait.until(ExpectedConditions.visibilityOf(LandingPage.iFrame2));
        driver.switchTo().frame(LandingPage.iFrame2);
        LandingPage.Click_Cancel_Button();
        driver.switchTo().parentFrame();
        Assert.assertTrue(LandingPage.Verify_Payment_Declined_isDisplayed());
    }
}

