Feature: [API] Gestion de usuarios por API Reqres
  Como cliente de la API de Reqres
  Quiero listar, crear y actualizar usuarios
  Para validar las operaciones CRUD del endpoint /api/users

  Background:
    Given el cliente API tiene acceso a Reqres

  @api @users
  Scenario: Listar usuarios devuelve datos paginados validos
    When solicita el listado de usuarios en la pagina 2
    Then la respuesta tiene codigo 200
    And la respuesta contiene una lista con al menos un usuario
    And la respuesta contiene los campos de paginacion:
      | page        |
      | per_page    |
      | total       |
      | total_pages |
    And cada usuario contiene los campos:
      | id         |
      | email      |
      | first_name |
      | last_name  |
      | avatar     |
    And todos los emails terminan en "@reqres.in"
    And todos los avatares son URLs con "reqres.in"

  @api @users
  Scenario: Crear un usuario devuelve 201 con los datos enviados e ID generado
    When crea un usuario con el nombre "Luis" y trabajo "QA Senior"
    Then la respuesta tiene codigo 201
    And la respuesta contiene el nombre "Luis" y el trabajo "QA Senior"
    And la respuesta contiene un id generado

  @api @users
  Scenario: Actualizar un usuario devuelve 200 con los datos actualizados
    When actualiza el usuario con id 2 cambiando el nombre a "Luis" y el trabajo a "Lead QA"
    Then la respuesta tiene codigo 200
    And la respuesta contiene el nombre "Luis" y el trabajo "Lead QA"
    And la respuesta contiene un updatedAt