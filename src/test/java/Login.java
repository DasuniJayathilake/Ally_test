import com.deque.html.axecore.extensions.WebDriverExtensions;
import com.deque.html.axecore.results.Results;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import javax.naming.OperationNotSupportedException;
import java.io.FileWriter;
import java.io.IOException;

public class Login {
    public WebDriver driver;
    @BeforeMethod
    public void init(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://doktorhemma.azurewebsites.net/test-login");
    }
    @Test
    public void dhAllyTest() throws OperationNotSupportedException, IOException {
        Results axeResults = WebDriverExtensions.analyze(driver);
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonReport = writer.writeValueAsString(axeResults);
        System.out.println(jsonReport);
            try {
                FileWriter myWriter = new FileWriter("Reports.json");
                myWriter.write(jsonReport);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
