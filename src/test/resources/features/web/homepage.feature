Feature: [WEB] HomePage de Selenium.dev
  Como usuario interesado en automatización
  Quiero acceder a la pagina principal de Selenium
  Para confirmar que el sitio está disponible

  @web @homepage
  Scenario: La pagina principal carga con el título correcto
    Given Luis quiere validar la pagina principal de Selenium
    When navega a la pagina principal
    Then el título de la pagina debe ser "Selenium"
    And la URL actual debe contener "selenium.dev"