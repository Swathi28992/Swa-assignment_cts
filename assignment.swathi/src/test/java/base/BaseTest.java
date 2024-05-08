package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader fr;
	public static FileReader frl;
	WebElement checkbox;
	WebElement itemsLeft;
	WebElement jogging;
	WebElement joggingAfterClick;
	WebElement activebutton;
	WebElement activeItems;
	WebElement completeButton;
	WebElement completedItems;
	WebElement clearButton;
	WebElement allButton;
	WebElement itemsAll;
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		fr = new FileReader("C:\\Automation\\assignment.swathi\\src\\test\\resources\\configfiles\\config.properties");
		frl = new FileReader("C:\\Automation\\assignment.swathi\\src\\test\\resources\\configfiles\\locators.properties");
		prop.load(fr);
		loc.load(frl);
		
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(prop.getProperty("testurl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
		System.out.println("Teardown successful");
	}
	
	public void EnterToDo() throws InterruptedException {

		
		WebElement ToDoLists = driver.findElement(By.xpath(loc.getProperty("toDo_Lists")));
		ToDoLists.sendKeys("Shopping");
		ToDoLists.sendKeys(Keys.ENTER);
		
		ToDoLists.sendKeys("Jogging");
		ToDoLists.sendKeys(Keys.ENTER);
		
		ToDoLists.sendKeys("Pay Bills");
		ToDoLists.sendKeys(Keys.ENTER);
		
		ToDoLists.sendKeys("Cooking");
		ToDoLists.sendKeys(Keys.ENTER);
	}
	
	public void ClickCheckbox()
	{
		checkbox = driver.findElement(By.xpath(loc.getProperty("check_box")));	
		checkbox.click();			
	}
	
	public void IsCheckboxSelected()
		
	{
		Assert.assertEquals(true, checkbox.isSelected());
		
		if(checkbox.isSelected())
		{
						System.out.println("Shopping task is displayed as completed");
		}
		else
		{
			System.out.println("Shopping task is not displayed as completed");
		}
	}
	
	public void FooterMsg()
	{
		itemsLeft = driver.findElement(By.xpath(loc.getProperty("items_Left")));
		String itemsMsg = itemsLeft.getText(); // get the text of the message displayed in the footer				
		System.out.println(itemsMsg);		
		Assert.assertEquals(itemsMsg, "3 items left!");		
	}
	
	public void DoubleClickToEdit() throws InterruptedException
	{	
		jogging = driver.findElement(By.xpath(loc.getProperty("jogging_task")));
		//WebElement jogging = driver.findElement(By.xpath("jogging_task"));
		Actions action = new Actions(driver);
		action.moveToElement(jogging).doubleClick().build().perform();
		Thread.sleep(3000);
	}
	
	public void EditTheTaskName() 
	{
		joggingAfterClick = driver.findElement(By.xpath(loc.getProperty("jogging_task_Double_Click")));	
		joggingAfterClick.sendKeys(Keys.CONTROL + "a");				
		joggingAfterClick.sendKeys(Keys.DELETE);		
		joggingAfterClick.sendKeys("Exercise" + Keys.ENTER);
		
	}
	public void ClickOnActiveButton() 
	{
		activebutton = driver.findElement(By.xpath(loc.getProperty("active_button")));
		activebutton.click();
		
	}
	
	public void ActiveItems() 
	{
		activeItems = driver.findElement(By.xpath(loc.getProperty("active_items")));
		System.out.println(activeItems.getText());				
		String items = activeItems.getText();
		
		if (items.contains("Jogging") && items.contains("Cooking")
				&& items.contains("Pay Bills") && !items.contains("Shopping"))
		{
			System.out.println("Only Active tasks are displayed");					
		}
		else
		{	
			Assert.fail("Active tasks are not displayed as expected");
		}
	}
	
		public void ClickOnCompleteButton() 
		{	//driver.findElement(By.xpath("//a[normalize-space()='Completed']")).click();
			completeButton = driver.findElement(By.xpath(loc.getProperty("complete_button")));
			completeButton.click();
			
		}
		
		public void CompletedItems() throws InterruptedException 
		{
			Thread.sleep(1000);
			WebElement completedItems = driver.findElement(By.xpath(loc.getProperty("completed_items")));						
			String completed = completedItems.getText();
			System.out.println(completed);	
			if (!completed.contains("Exercise") && !completed.contains("Cooking") && !completed.contains("Pay Bills") && completed.contains("Shopping"))
			{
				System.out.println("Only 1 item displayed that is completed");					
			}
			else
			{
				Assert.fail("Completed items are not displayed as expected");
			}
		}
		
		public void ClickOnClearCompletedButton() throws InterruptedException 
		
		{	
			clearButton = driver.findElement(By.xpath(loc.getProperty("clear_button")));
			clearButton.click();
			Thread.sleep(1000);
			
		}
		
		public void ClickOnAllButton() 
		{	
			allButton = driver.findElement(By.xpath(loc.getProperty("all_button")));
			allButton.click();
			
		}
		
		public void VerifyCompletedItemsIsCleared() 
		{	
			allButton = driver.findElement(By.xpath(loc.getProperty("all_button")));
			allButton.click();			
			itemsAll = driver.findElement(By.xpath(loc.getProperty("items_displayed_all")));				
			String AllDisplay = itemsAll.getText();
			System.out.println(AllDisplay);
			if (!AllDisplay.contains("Shopping")) 
			{				
				System.out.println("Completed task is cleared from the list");	
			}
			else
			{
				Assert.fail("Completed task is not cleared from the list");
			}			
		}	
}

