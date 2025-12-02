Feature: Click Button testi

  Scenario: Click Me butonuna tıklanıp mesaj doğrulanmalı
    Given demoqa elements sayfasına gidilir
    When "Buttons" menüsü açılır
    And "Click Me" butonuna tıklanır
    Then görünen mesaj doğrulanır
