import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.WebElement

// Open the order management page
WebUI.openBrowser('')
WebUI.navigateToUrl('http://localhost:5064/Identity/Account/Login')

// Login
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Email'), 'hueh30@gmail.com')  
WebUI.setEncryptedText(findTestObject('Object Repository/LoginPage/txt_Password'), '5+xlL+YL/79ln+aPFCPk+A==')  
WebUI.click(findTestObject('Object Repository/LoginPage/btn_Login'))

// Navigate to the order management page
WebUI.click(findTestObject('Object Repository/HomePage/lnk_ManageOrder'))
WebUI.verifyElementPresent(findTestObject('Object Repository/OrderListPage/txt_OrderListTitle'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/OrderListPage/dbl_Data'), 10)

// Array to store "Total" values
List<Double> totalPrices = []

// --------------------- Check sorting in descending order ---------------------
WebUI.click(findTestObject('Object Repository/OrderListPage/btn_Total'))
WebUI.click(findTestObject('Object Repository/OrderListPage/icon_ascending'))

// Clear the list and retrieve the updated "Total" values
List<WebElement> rowsDesc = WebUI.findWebElements(findTestObject('Object Repository/OrderListPage/rows_Data'), 10)
for (int i = 0; i < rowsDesc.size(); i++) {
	String totalXpath = "//table[@id='tblData']//tr[" + (i + 1) + "]//td[6]" // Assuming "Total" is in the 6th column
	TestObject dynamicTotalObject = new TestObject().addProperty("xpath", ConditionType.EQUALS, totalXpath)
	String totalText = WebUI.getText(dynamicTotalObject)
	double totalValue = Double.parseDouble(totalText.replaceAll("[^0-9.]", ""))
	totalPrices.add(totalValue)
}

// Check if the list is sorted in descending order
println("The 'Total' values sorted in descending order: " + totalPrices)
boolean isSortedDescending = true
for (int i = 1; i < totalPrices.size(); i++) {
	if (totalPrices.get(i) > totalPrices.get(i - 1)) {
		isSortedDescending = false
		break
	}
}

if (isSortedDescending) {
	println("The table is sorted in descending order.")
} else {
	println("The table is not sorted correctly in descending order.")
}

// Close the browser
WebUI.closeBrowser()
