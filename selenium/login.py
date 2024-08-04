from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import unittest


class LoginTests(unittest.TestCase):

    def setUp(self):
        # Set up ChromeDriver with headless mode
        options = Options()
        options.headless = True # Run Chrome in headless mode
        options.add_argument('--no-sandbox') # Disables the sandbox security feature, useful for running tests in Github Action
        options.add_argument('--disable-dev-shm-usage') # Disable the use of /dev/shm (shared memory) to reduce memory usage
        options.add_argument('--disable-gpu') # Disables GPU hardware acceleration, useful for headless mode
        self.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
        self.driver.get("https://practicetestautomation.com/practice-test-login/")

    def test_valid_login(self):
        driver = self.driver
        driver.find_element(By.ID, "username").send_keys("student")
        driver.find_element(By.ID, "password").send_keys("Password123")
        driver.find_element(By.ID, "submit").click()

        # Assertion for successful login
        login_message = driver.find_element(By.CLASS_NAME, "post-title")
        self.assertTrue(login_message.is_displayed(), "Login message is displayed")

        captured_login_message = login_message.text
        expected_login_message = "Logged In Successfully"
        self.assertEqual(expected_login_message, captured_login_message,
                         "Expected login message matches captured login message")

        print("Positive test case passed - Should login successfully with valid credentials")

    def test_invalid_login(self):
        driver = self.driver
        driver.find_element(By.ID, "username").send_keys("student1")
        driver.find_element(By.ID, "password").send_keys("invalidPassword")
        driver.find_element(By.ID, "submit").click()

        # Assertion for error message with explicit wait
        error_message = WebDriverWait(driver, 5).until(
            EC.visibility_of_element_located((By.XPATH, "//div[@id='error']"))
        )

        self.assertTrue(error_message.is_displayed(), "Error message is displayed")

        captured_error_message = error_message.text
        expected_error_message = "Your username is invalid!"
        self.assertEqual(expected_error_message,
                         captured_error_message, "Expected error message matches captured error message")

        print("Negative test case passed - Should display an error message with invalid credentials")

    def tearDown(self):
        self.driver.quit()


if __name__ == "__main__":
    unittest.main()
