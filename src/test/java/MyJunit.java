import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

public class MyJunit {
    static WebDriver driver;
    static final String EXCEL_PATH = "./src/main/resources/Excel.csv";
    static final String SHEET_NAME = " ";

    @BeforeAll
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void Search() throws InterruptedException {
        driver.get("https://www.google.com/");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(" ");
        Thread.sleep(2000);

        List<WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']/li"));
        System.out.println("Searched Elements:");

        if (list.isEmpty()) {
            System.out.println("No search suggestions found.");
            return;
        }

        String longest = "";
        String shortest = null; // Start with null to ensure valid assignment

        for (WebElement elem : list) {
            String text = elem.getText().trim(); // Trim to remove extra spaces
            if (text.isEmpty()) continue; // Ignore empty elements

            System.out.println(text);

            if (longest.length() < text.length()) longest = text;
            if (shortest == null || text.length() < shortest.length()) shortest = text;
        }

        System.out.println("Longest Option: " + longest);
        System.out.println("Shortest Option: " + shortest);

        // Assertions (Modify based on expected results)
        assertNotNull(longest, "Longest option is null!");
        assertNotNull(shortest, "Shortest option is null!");
        assertFalse(shortest.isEmpty(), "Shortest option is empty!");
        assertTrue(longest.length() >= shortest.length(), "Longest option should be longer!");

    }
    }

