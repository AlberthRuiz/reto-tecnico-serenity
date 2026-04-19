Feature: [WEB] Navegacion a Documentation
  Como usuario del sitio de Selenium
  Quiero navegar a la seccion de documentation desde la pagina principal
  Para acceder a la ifnormacion tecnica

  @web @documentation
  Scenario: Navegar desde la pagina principal hacia Documentation
    Given Luis se encuentra en la pagina principal de Selenium
    When hace click en el enlace "Documentation"
    Then la URL actual debe contener "/documentation"
    And el titulo de la pagina debe contener "Selenium"
    And la pagina de Documentation debe mostrarse correctamente