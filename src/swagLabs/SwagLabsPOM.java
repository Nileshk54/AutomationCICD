package swagLabs;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class SwagLabsPOM extends BaseTestSwagLabs{

	@Test
	public void swagLabsAddToCart() {
		LoginPage loginPage=new LoginPage(driver);
		ProductPage productPage=loginPage.login(userName,password);
		CartPage cartPage=productPage.addProduct(productName);
		InformationPage info=cartPage.goToCheckOut(productName);
		OverViewPage overview=info.details(firstName, lastName, pinCode);
		overview.placeOrder(productName);
	}
	
	@Test
	public void swagLabsMultipleProducts() {
		List<String> listOfProduts=new ArrayList<String>();
		//listOfProduts.add(productNameOne);
		listOfProduts.add(BaseTestSwagLabs.productNameTwo);
		listOfProduts.add(productNameThree);
		LoginPage loginPage=new LoginPage(driver);
		ProductPage productPage=loginPage.login(userName,password);
		CartPage cartPage=productPage.addMultipleProducts(listOfProduts);
		InformationPage info=cartPage.multipleGoToCheckOut(listOfProduts);
		OverViewPage overview=info.details(firstName, lastName, pinCode);
		overview.multipleProductOrder(listOfProduts);
	}
	
}
