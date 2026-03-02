Feature: Starbucks Header Navigation and Functionality

  Background:
    Given User is on the Starbucks homepage

  @Navigation
  Scenario Outline: Verify navigation and URL for each category
    When User clicks on "<category_name>" category link
    Then The page URL should contain "<expected_url>"

    Examples:
      | category_name    | expected_url       |
      | Kahve            | starbucks-kahveler |
      | Kupa & Termos    | kupa-termoslar     |
      | Aksesuar         | aksesuarlar        |
      | Demleme & Öğütme | demleme-ogutme     |
      | Son Kalanlar     | son-kalanlar       |
      | Kurumsal Satış   | kurumsal-satis     |

  @Search
  Scenario Outline: Verify search functionality
    When User types "<product_name>" in the search bar
    And User clicks the search icon
    Then User should see results related to "<product_name>"

    Examples:
      | product_name |
      | Termos       |
      | Anahtarl     |

  @Cart
  Scenario: Check shopping cart status
    Given User is on the Starbucks homepage
    Then The shopping cart icon should display "0" items
    When User clicks on Sepetim button
    Then User should see "Sepetin Boş" text
    And User clicks Alışverişe Başla button
    Then User should be redirected to the homepage