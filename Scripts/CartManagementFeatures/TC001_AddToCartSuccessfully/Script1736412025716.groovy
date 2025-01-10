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

'Step 2: Navigate to product details'
WebUI.click(findTestObject('HomePage/btn_FirstProductDetail'))

'Check if the "Add to Cart" button exists on the page'
WebUI.verifyElementPresent(findTestObject('DetailPage/btn_AddToCart'), 5)

'Step 3 Update the quantity in the input field'
WebUI.setText(findTestObject('DetailPage/txt_Count'), '1')

'Verify the quantity input is updated correctly'
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('DetailPage/txt_Count'), 'value'), '1', false)

productName = WebUI.getText(findTestObject('DetailPage/h3_ProductName')).toLowerCase()

'Step 4: Add the product to the cart'
WebUI.click(findTestObject('DetailPage/btn_AddToCart'))

'verify navigate to homepage'
WebUI.verifyElementPresent(findTestObject('HomePage/btn_FirstProductDetail'), 5)

'Verify the success toast is displayed'
WebUI.verifyElementPresent(findTestObject('Toastr/ToastSuccess'), 5)
'Verify the toast message text is correct'
WebUI.verifyMatch(WebUI.getText(findTestObject('Toastr/ToastSuccessMessage')), 'Cart updated successfully', false)

'Step 5 naviaget to cart page'
WebUI.click(findTestObject('Header/icon_Cart'))

'get the trash icon'
String trashIconXPath = "//strong[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + productName + "']/ancestor::div[contains(@class,'row border-bottom pb-3')]//a[contains(@href,'/Customer/Cart/remove')]"
TestObject trashIcon = new TestObject('dynamicQuantityElement')
trashIcon.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, trashIconXPath)
'verify the element is present'
WebUI.verifyElementPresent(trashIcon, 5)

'delete the cart'
WebUI.click(trashIcon)