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
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType

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

// Click the "Completed" button  
WebUI.click(findTestObject('Object Repository/OrderListPage/btn_Completed'))  

// Verify the status of all rows in the table  
// Determine the total number of rows in the table (excluding the header)  
List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/OrderListPage/rows_Data'), 10)  
println("Total number of rows: " + rows.size())  

print(rows)  
// Iterate through each row and check the status  
for (int i = 0; i < rows.size(); i++) {  
    print(i)  
    // Dynamic XPath to retrieve the value of the "Status" column in row i  
    String statusXpath = "//table[@id='tblData']//tr[" + (i + 1) + "]//td[5]"  
    TestObject dynamicStatusObject = new TestObject().addProperty("xpath", ConditionType.EQUALS, statusXpath)  
    
    // Get the status value from the "Status" column  
    String orderStatus = WebUI.getText(dynamicStatusObject)  
    println("Order status at row " + i + ": " + orderStatus)  
    
    // Verify if the status matches 'Shipped'  
    WebUI.verifyMatch(orderStatus, 'Shipped', false)  
}  

// Close the browser  
WebUI.closeBrowser()

