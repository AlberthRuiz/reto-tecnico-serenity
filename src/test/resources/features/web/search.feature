Feature: [WEB] Busqueda en el sitio de Selenium
  Como usuario del sitio de Selenium
  Quiero buscar contenido específico
  Para encontrar rápidamente la informacion que necesito

  @web @search
  Scenario Outline: Busqueda de "<termino>" devuelve resultados relevantes
    Given Luis se encuentra en la página de Documentacion de Selenium
    When busca el termino "<termino>"
    Then los resultados de busqueda contienen "<termino>"
    Examples:
      | termino   |
      | WebDriver |
      | Grid      |