Feature: [WEB] Busqueda en el sitio de Selenium
  Como usuario del sitio de Selenium
  Quiero buscar contenido específico
  Para encontrar rápidamente la informacion que necesito

  @web @search
  Scenario Outline: Busqueda de "<termino>" devuelve resultados relevantes
    Given Luis se encuentra en la pagina de Documentacion de Selenium
    When busca el termino "<termino>"
    Then los resultados de busqueda contienen "<termino>"
    Examples:
      | termino   |
      | WebDriver |
      | Grid      |

  @web @search @negative
  Scenario: Busqueda de un termino inexistente muestra mensaje "No results"
    Given Luis se encuentra en la pagina de Documentacion de Selenium
    When busca el termino "@#$Q$AxasdINVALID"
    Then aparece el mensaje "No results for" en el buscador