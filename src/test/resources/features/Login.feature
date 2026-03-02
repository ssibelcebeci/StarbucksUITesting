@Login
Feature: Login Functionality
  As a user, I want to log in to my account so that I can access my favorites and order history.

  Background:
    Given User is on the Starbucks homepage
    When User clicks on "Giriş Yap" button

  @Positive
  Scenario: Successful login with valid credentials
    When User enters valid email and password from config
    And User clicks the "Giriş" submit button
    And User clicks the "Hesabım" Option
    Then User should see "MERHABA" text or their account name

  @Negative
  Scenario Outline: Unsuccessful login with invalid credentials
    When User enters invalid email "<email>" and password "<password>"
    And User clicks the "Giriş" submit button
    Then User should see error message "<error_message>"

    Examples:
      | email                  | password      | error_message                                             |
      | wrong@gmail.com        | Test12.Test12 | Hatalı kullanıcı adı veya şifre, lütfen tekrar deneyiniz! |
      | 1testestuser@gmail.com | wrongPass     | Hatalı kullanıcı adı veya şifre, lütfen tekrar deneyiniz! |
      |                        | Test12.Test12 | Lütfen E-posta Adresinizi Kontrol Ediniz.                 |
      | 1testestuser@gmail.com |               | Bu Alan Zorunludur                                        |
      | invalid-email          | Test12.Test12 | Lütfen E-posta Adresinizi Kontrol Ediniz.                 |

  @ForgotPassword
  Scenario: Verify Forgot Password redirection
    When User clicks on "Şifremi Unuttum" link
    Then The page URL should contain "SifremiUnuttum"