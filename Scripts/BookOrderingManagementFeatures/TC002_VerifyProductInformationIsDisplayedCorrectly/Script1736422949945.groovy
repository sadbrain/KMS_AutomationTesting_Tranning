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

'Step 2 add first product to cart and navigate to cart page'
WebUI.click(findTestObject('HomePage/btn_FirstProductDetail'))
productName = WebUI.getText(findTestObject('DetailPage/h3_ProductName')).toLowerCase()
WebUI.click(findTestObject('DetailPage/btn_AddToCart'))
WebUI.click(findTestObject('Header/icon_Cart'))

'setp 3 get unit price, quantity and total price'
String quantityXPath = "//strong[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + productName + "']/ancestor::div[contains(@class,'row border-bottom pb-3')]//h6"
TestObject quantityElement = new TestObject('dynamicQuantityElement')
quantityElement.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, quantityXPath)
'verify the element is present'
WebUI.verifyElementPresent(quantityElement, 5)
'get unit price by h6Text'
String h6Text = WebUI.getText(quantityElement)
String[] parts = h6Text.split("x")
String unitPriceCart = parts[0].replaceAll("[^\\d.]", "").trim()
String quantityCart = h6Text.split('x')[1].trim()
totalPriceCart = WebUI.getText(findTestObject('CartPage/h4_TotalPrice'))
'Step 4 navigate to place order page'
WebUI.click(findTestObject('CartPage/a_Summary'))

'get text for quantity, unitprice, name product and total price'
String productNameOnPlaceOrderPage = WebUI.getText(findTestObject('PlaceOrderPage/h6_NameProduct'))
String quantityOnPlaceOrderPage = WebUI.getText(findTestObject('PlaceOrderPage/small_Quantity')).replaceAll("[^\\d]", "").trim() 
String unitPriceOnPlaceOrderPage = WebUI.getText(findTestObject('PlaceOrderPage/span_UnitPrice'))
String totalPriceOnPlaceOrderPage = WebUI.getText(findTestObject('PlaceOrderPage/strong_TotalPrice'))

'step 4 verify quantity, unitprice, name product and total pri'
WebUI.verifyMatch(productNameOnPlaceOrderPage.toLowerCase(), productName, false) 
WebUI.verifyMatch(unitPriceOnPlaceOrderPage.replaceAll("[^\\d.]", "").trim(), unitPriceCart, false)
WebUI.verifyMatch(totalPriceCart.replaceAll("[^\\d.]", "").trim(), totalPriceOnPlaceOrderPage.replaceAll("[^\\d.]", "").trim(), false)
WebUI.verifyMatch(quantityOnPlaceOrderPage, quantityCart, false)

'step 5 navigate to the cart and get the trash icon'
WebUI.click(findTestObject('Header/icon_Cart'))
String trashIconXPath = "//strong[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + productName + "']/ancestor::div[contains(@class,'row border-bottom pb-3')]//a[contains(@href,'/Customer/Cart/remove')]"
TestObject trashIcon = new TestObject('dynamicQuantityElement')
trashIcon.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, trashIconXPath)
'verify the element is present'
WebUI.verifyElementPresent(trashIcon, 5)

'delete the cart'
WebUI.click(trashIcon)