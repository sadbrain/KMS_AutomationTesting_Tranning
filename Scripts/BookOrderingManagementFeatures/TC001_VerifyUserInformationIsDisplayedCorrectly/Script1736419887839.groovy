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

import authentication.LoginInCustomerAccount

"Step: 1 Login in customer account"
LoginInCustomerAccount.login()

'Step 2 navigate to cart page'
WebUI.click(findTestObject('Header/icon_Cart'))

'Step 3 navigate to place order page'
WebUI.click(findTestObject('CartPage/a_Summary'))

'Verify user information matches with expected values'
String userName = WebUI.getAttribute(findTestObject('PlaceOrderPage/txt_UserName'), 'value')
String phone = WebUI.getAttribute(findTestObject('PlaceOrderPage/txt_Phone'), 'value')
String streetAddress = WebUI.getAttribute(findTestObject('PlaceOrderPage/txt_StreetAddress'), 'value')
String city = WebUI.getAttribute(findTestObject('PlaceOrderPage/txt_City'), 'value')
String state = WebUI.getAttribute(findTestObject('PlaceOrderPage/txt_State'), 'value')

'Verify the extracted values match the expected ones'
WebUI.verifyMatch(userName, 'Customer1', false)
WebUI.verifyMatch(phone, '123456789', false)
WebUI.verifyMatch(streetAddress, '99 Tô Hiến Thành', false)
WebUI.verifyMatch(city, 'Đà Nẵng', false)
WebUI.verifyMatch(state, 'Đà Nẵng', false)