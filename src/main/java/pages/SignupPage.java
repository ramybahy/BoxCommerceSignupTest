package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignupPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Global 20s wait
        waitForPageToLoad(); // Ensure page is fully loaded
        System.out.println("SignupPage initialized and page loaded.");
    }

    // Locators
    private By closePopupBtn = By.cssSelector("[aria-label='Close dialog']");
    private By signUpWithEmailBtn = By.xpath("//button//span[normalize-space()='Sign up with email/phone no.']");
    private By firstNameField = By.cssSelector("input[formcontrolname='firstName']");
    private By lastNameField = By.cssSelector("input[formcontrolname='lastName']");
    private By countryDropdown = By.cssSelector("mat-select[formcontrolname='countryCode']");
    private By phoneField = By.cssSelector("input[placeholder='Phone number']");
    private By emailField = By.cssSelector("input[formcontrolname='email']");
    private By passwordField = By.cssSelector("input[formcontrolname='password']");
    private By confirmPasswordField = By.cssSelector("input[formcontrolname='confirmPassword']");
    private By couponCodeField = By.cssSelector("input[formcontrolname='couponCode']");
    private By signUpButton = By.xpath("//button[@type='submit']");
    private By successMessage = By.xpath("//div[@class='title' and contains(text(), 'Welcome to the BoxCommerce website wizard')]");

    // Utility: Wait until page fully loaded
    public void waitForPageToLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        System.out.println("Page fully loaded.");
    }

    // Methods
    public void closePopupIfVisible() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement popup = shortWait.until(ExpectedConditions.presenceOfElementLocated(closePopupBtn));
            if (popup.isDisplayed()) {
                // Wait until fully clickable
                shortWait.until(ExpectedConditions.elementToBeClickable(popup));
                popup.click();
                System.out.println("Popup detected and closed.");
            }
        } catch (Exception e) {
            // no popup present, ignore
            System.out.println("No popup present.");
        }
    }

    public void clickSignUpWithEmail() {
        System.out.println("Attempting to click 'Sign up with email/phone no.' button...");

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpWithEmailBtn));

        // Wait up to 3 seconds for the button to become clickable
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        shortWait.until(ExpectedConditions.elementToBeClickable(btn));

        btn.click();
        System.out.println("'Sign up with email/phone no.' button clicked.");
    }


    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        System.out.println("Entered first name: " + firstName);
    }


    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
        System.out.println("Entered last name: " + lastName);
    }

    public void selectCountry(String country) {
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)).click();
        By countryOption = By.xpath("//mat-option//span[normalize-space()='" + country + "']");
        wait.until(ExpectedConditions.elementToBeClickable(countryOption)).click();
        System.out.println("Selected country: " + country);
    }

    public void enterPhoneNumber(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField)).sendKeys(phone);
        System.out.println("Entered phone number: " + phone);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        System.out.println("Entered email: " + email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        System.out.println("Entered password.");
    }

    public void enterConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField)).sendKeys(confirmPassword);
        System.out.println("Entered confirm password.");
    }

    public void enterCoupon(String coupon) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(couponCodeField)).sendKeys(coupon);
        System.out.println("Entered coupon code: " + coupon);
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
        System.out.println("Signup form submitted.");
    }

    public String getSuccessMessage() {
        String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
        System.out.println("Retrieved success message: " + msg);
        return msg;
    }

    // Main signup method
    public void signUp(String firstName, String lastName, String country,
                       String phone, String email, String password, String confirmPassword, String coupon) {
        enterFirstName(firstName);
        enterLastName(lastName);
        selectCountry(country);
        enterPhoneNumber(phone);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        enterCoupon(coupon);
        submitForm();
        System.out.println("Signup flow completed.");
    }
}
