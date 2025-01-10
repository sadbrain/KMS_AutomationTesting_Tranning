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

import authentication.LoginInCompanyAccount

"Step: 1 Login in customer account"
LoginInCompanyAccount.login()

'Step 2 add first product to cart and navigate to cart page'
WebUI.click(findTestObject('HomePage/btn_FirstProductDetail'))
WebUI.click(findTestObject('DetailPage/btn_AddToCart'))
WebUI.click(findTestObject('Header/icon_Cart'))

'Step 3 navigate to place order page'
WebUI.click(findTestObject('CartPage/a_Summary'))

'step 4 place order'
WebUI.click(findTestObject('PlaceOrderPage/btn_PlaceOrder'))

'step 5 verify the test present'
WebUI.verifyTextPresent('Order Placed Successfully!', false)
String orderNumberText = WebUI.getText(findTestObject('OrderConfirmation/h1_OrderNumberLabel'))
orderId=orderNumberText.replaceAll("[^\\d]", "")
'step 6 navigate to order management page'
WebUI.click(findTestObject('Header/a_ManageOrder'))

'step 7 search newest order'
WebUI.setText(findTestObject('OrderManagement/txt_Search'), orderId)

'step 8 verify status'
String statusXpath = "//td[text()='${orderId}']/../td[5]"
TestObject statusElement = new TestObject('dynamicQuantityElement')
statusElement.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, statusXpath)
WebUI.verifyElementPresent(statusElement, 5)
WebUI.verifyMatch(WebUI.getText(statusElement), 'Approved', false)
