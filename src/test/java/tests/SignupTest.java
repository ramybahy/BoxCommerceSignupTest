package tests;

import tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignupPage;
import java.util.Random;

public class SignupTest extends BaseTest {

    // Helper to generate a unique email per test run
    private String generateUniqueEmail() {
        long timestamp = System.currentTimeMillis();
        return "ramy.test+" + timestamp + "@gmail.com";
    }

    // Helper to generate a unique phone number per test run
    private String generateUniquePhone() {
        Random random = new Random();
        return "87" + (1000000 + random.nextInt(8999999));
    }

    @Test(description = "Verify user can sign up successfully with valid details")
    public void testSignupWithEmailPhone() {
        driver.get("https://dashboard-uat.boxcommerce.com/en-GB/auth/sign-up");
        System.out.println("Navigated to signup page.");

        SignupPage signupPage = new SignupPage(driver);

        signupPage.closePopupIfVisible();

        signupPage.clickSignUpWithEmail();

        String uniqueEmail = generateUniqueEmail();
        String uniquePhone = generateUniquePhone();
        System.out.println("Generated unique email and phone for the test.");

        // Fill form with valid data
        signupPage.signUp(
                "John",                 // First name
                "Smith",                // Last name
                "South Africa",         // Country
                uniquePhone,            // Phone
                uniqueEmail,            // Email
                "Md@05121999",          // Password
                "Md@05121999",          // Confirm Password
                ""                      // Coupon (optional)
        );

        // Verify success message
        String actualMessage = signupPage.getSuccessMessage();
        String expectedMessage = "Welcome to the BoxCommerce website wizard";

        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Signup success message did not match! Actual: " + actualMessage
        );
        System.out.println("Test completed successfully. Verified success message.");
    }
}
