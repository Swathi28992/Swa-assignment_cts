package testcases;

import org.testng.annotations.Test;

import base.BaseTest;

public class Testcases extends BaseTest {
	BaseTest bt = new BaseTest();
	

	@Test(priority =1, description= "Test to check if ToDo lists are getting entered")
	public void EnterAllTheTasks() throws InterruptedException
	
	{		
		bt.EnterToDo();
		System.out.println("To do tasks are entered");			
	}
	
	
	@Test(priority =2, description= "Test to check the Completed task functionality")	
	public void CompletedTask() throws InterruptedException	
	{	
		bt.EnterToDo();
		bt.ClickCheckbox();
		bt.IsCheckboxSelected();	
	}
	
	@Test(priority =3, description= "Test to verify the footer text")	
	public void FooterNoOfItemsDisplayed() throws InterruptedException	
	{	
		bt.EnterToDo();
		bt.ClickCheckbox();
		bt.FooterMsg();		
	}
	
	@Test(priority =4, description= "Test to verify the edit functionality ")		
	public void EditNameOfTheTask() throws InterruptedException	
	{	
		bt.EnterToDo();
		bt.DoubleClickToEdit();
		bt.EditTheTaskName();
	
	}
	@Test(priority =5, description= "Test to check the Active button and display of active items  ")	
	public void ActiveTasksDisplayed() throws InterruptedException	
	{	
		bt.EnterToDo();
		bt.ClickCheckbox();
		bt.ClickOnActiveButton();
		bt.ActiveItems();	
	}
	
	@Test(priority =6, description= "Test to check the Completed button and display of completed items ")
	public void CompletedTasksDisplayed() throws InterruptedException	
	{	
		bt.EnterToDo();
		bt.ClickCheckbox();
		bt.ClickOnCompleteButton();
		bt.CompletedItems();
	}
	
	@Test(priority =7, description= "Test to check the Clear functionality ")	
	public void ClearTheCompletedTask() throws InterruptedException	
	{	
		bt.EnterToDo();
		bt.ClickCheckbox();
		Thread.sleep(3000);
		bt.ClickOnClearCompletedButton();
		bt.ClickOnAllButton();
		bt.VerifyCompletedItemsIsCleared();
	}
}
