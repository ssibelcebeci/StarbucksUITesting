Feature: Favorites func
  As a user, I want to add and delete product/s my favorites so that I can manage my favorites.

  Background:
    Given User is on the Starbucks homepage
    When User clicks on "Giriş Yap" button
    When User enters valid email and password from config
    And User clicks the "Giriş" submit button

  @Favorites
  Scenario: Verify that favorites section is empty
    Given User is logged in and on the dashboard
    When User clicks on the "Favorites" button in the navigation menu
    Then User should see the message "Favori İlk Ürününü Ekle"
    And User clicks on the "Alışverişe Başla" button
    Then User should be redirected to the home page

  @Favorites
  Scenario: Successfully add and delete a random product to a custom favorite group
    Given User is on the home page
    When User selects a random category from the navigation menu
    And User clicks on a random product from the list
    And User clicks on the "Add to Favorites" icon
    And User enters "Favorilerim" into the group name field and clicks save
    Then User should see "Ürün favorilerinize eklenmiştir." notification
    When User clicks on the "Favorilerim" button
    And User expands the "Favorilerim" group using the down arrow
    Then The product name in the favorites list should match the added product name
    And User clicks on the delete icon for the favorite group
    And User clicks on the "Evet" button in the confirmation popup
    Then User should see the success message "İşleminiz başarıyla gerçekleşmiştir."