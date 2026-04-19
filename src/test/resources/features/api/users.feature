Feature: [API] Gestion de usuarios por API Reqres
  Como cliente de la API de Reqres
  Quiero listar, crear y actualizar usuarios
  Para validar las operaciones CRUD del endpoint /api/users

  Background:
    Given el cliente API tiene acceso a Reqres

  @api @users @list
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

  @api @users @create
  Scenario: Crear un usuario devuelve 201 con los datos enviados e ID generado
    When crea un usuario con el nombre "Luis" y trabajo "QA Senior"
    Then la respuesta tiene codigo 201
    And la respuesta contiene el nombre "Luis" y el trabajo "QA Senior"
    And la respuesta contiene un id generado

  @api @users @update
  Scenario: Actualizar un usuario devuelve 200 con los datos actualizados
    When actualiza el usuario con id 2 cambiando el nombre a "Luis" y el trabajo a "Lead QA"
    Then la respuesta tiene codigo 200
    And la respuesta contiene el nombre "Luis" y el trabajo "Lead QA"
    And la respuesta contiene un updatedAt

  @api @users @create @timing
  Scenario: El createdAt devuelto refleja el momento de la creacion
    When crea un usuario con el nombre "Maria" y trabajo "Head IT"
    Then la respuesta tiene codigo 201
    And el createdAt esta dentro de los ultimos 60 segundos

  @api @users @list @data-integrity
  Scenario: Los avatares de los usuarios son URLs accesibles
    When solicita el listado de usuarios en la pagina 2
    Then la respuesta tiene codigo 200
    And todas las URLs de avatar responden 200

  @api @users @create @idempotency
  Scenario: Crear el mismo usuario dos veces genera IDs distintos
    When crea un usuario con el nombre "Esau" y trabajo "QA Junior"
    And crea otro usuario con el nombre "Renato" y trabajo "Lead RPA" otra vez
    Then los dos ids generados son distintos