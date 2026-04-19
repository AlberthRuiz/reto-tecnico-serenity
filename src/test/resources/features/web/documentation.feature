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

  @web @navigation @negative
  Scenario: Acceso directo a URL inexistente muestra pagina 404 de Selenium
    Given Luis quiere acceder a una pagina de documentacion especifica
    When navega directamente a la ruta "/documentation/pagina_inexistente_xyz/"
    Then el titulo de la pagina debe contener "404"
    And el encabezado principal muestra "404"