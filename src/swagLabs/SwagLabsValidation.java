package swagLabs;

import org.testng.annotations.Test;

public class SwagLabsValidation extends BaseTestSwagLabs{

	@Test
	public void userNameValidation() throws InterruptedException {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.userNameValidation();
	}
	
	@Test
	public void passwordValidation() throws InterruptedException {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.passwordValidation(userName);
	}
	
	@Test
	public void invalidUserNameAndPaswordValidaiton() throws InterruptedException {
		LoginPage loginPage=new LoginPage(driver);
		loginPage.invalidUserNameAndPassword("nilesh","Khalate");
	}
	
	@Test
	public void firstNameValidation() throws InterruptedException {
		LoginPage loginPage=new LoginPage(driver);
		ProductPage productPage=loginPage.login(userName,password);
		CartPage cartPage=productPage.addProduct(productName);
		InformationPage info=cartPage.goToCheckOut(productName);
		info.details();
		Thread.sleep(5000);
	}
	
	@Test
	public void LastNameValidation() throws InterruptedException {
		LoginPage loginPage=new LoginPage(driver);
		ProductPage productPage=loginPage.login(userName,password);
		CartPage cartPage=productPage.addProduct(productName);
		InformationPage info=cartPage.goToCheckOut(productName);
		info.detailsFirstNameOnly("Nilesh");
		Thread.sleep(5000);
	}
	
	@Test
	public void pinCodeValidation() throws InterruptedException {
		LoginPage loginPage=new LoginPage(driver);
		ProductPage productPage=loginPage.login(userName,password);
		CartPage cartPage=productPage.addProduct(productName);
		InformationPage info=cartPage.goToCheckOut(productName);
		info.detailsFirstNameLastName("Nilesh","Khalate");
		Thread.sleep(5000);
	}
	
	
}
