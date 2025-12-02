Feature: Add Record testi

  Scenario: Yeni kayıt eklenip düzenlenmeli
    Given demoqa webtables sayfasına gidilir
    When "ADD" ile yeni kayıt eklenir
      | firstName | lastName | email               | age | salary | department |
      | Bilge       | Test     | bilge@test.com| 30  | 5000   | TestDept   |
    And eklenen kayıt "bilge@test.com" ile bulunup düzenlenir
      | firstName |
      | Mert   |
    Then kayıtta değişiklik "Mert" olarak görünmelidir
