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

WebUI.openBrowser('')  
WebUI.navigateToUrl('http://localhost:5064/Identity/Account/Login')  

// Enter email and password to login
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Email'), 'hueh30@gmail.com')  
WebUI.setEncryptedText(findTestObject('Object Repository/LoginPage/txt_Password'), '5+xlL+YL/79ln+aPFCPk+A==')  
WebUI.click(findTestObject('Object Repository/LoginPage/btn_Login'))  

// Navigate to the "Manage Order" page
WebUI.click(findTestObject('Object Repository/HomePage/lnk_ManageOrder'))  
WebUI.verifyElementPresent(findTestObject('Object Repository/OrderListPage/txt_OrderListTitle'), 10)  
WebUI.verifyElementPresent(findTestObject('Object Repository/OrderListPage/dbl_Data'), 10)  

// Check the total number of orders in the table
List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/OrderListPage/rows_Data'), 10)  
println("Total number of orders: " + rows.size())  

// Close the browser
WebUI.closeBrowser()
