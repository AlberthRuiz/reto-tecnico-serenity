# QA Automation Challenge

Suite de pruebas automatizadas para prueba tecnica Senior QA.
Pruebas web sobre `https://selenium.dev` y pruebas API sobre `https://reqres.in`.

---

## Enlaces rapidos

| Recurso | URL |
|---|---|
| Repositorio | https://github.com/AlberthRuiz/reto-tecnico-serenity |
| Reporte publico (Serenity BDD) | https://alberthruiz.github.io/reto-tecnico-serenity/ |

---

## Stack tecnico

| Tecnologia | Version |
|---|---|
| Java | 21 |
| Serenity BDD | 5.3.7 |
| Cucumber | 7.34.2 |
| Playwright | 1.58.0 |
| JUnit Platform | 6.0.3 |

---

## Cobertura de pruebas (13 scenarios)

**Web (selenium.dev)** — 5 scenarios
- Caso 1: Carga de la pagina principal con titulo esperado
- Caso 2: Navegacion desde home hacia Documentation
- Caso 3: Busqueda en el sitio (Scenario Outline con 2 terminos)
- Bonus: URL inexistente muestra pagina 404
- Bonus: Busqueda sin resultados muestra mensaje "No results"

**API (reqres.in)** — 8 scenarios
- Caso 4: Listar usuarios y validar contenido paginado
- Caso 5: Crear usuario via POST
- Caso 6: Actualizar usuario via PUT
- Bonus: Timestamp `createdAt` refleja momento de creacion
- Bonus: URLs de avatares responden HTTP 200
- Bonus: Crear mismo usuario 2 veces genera IDs distintos (idempotencia)
- Bonus: Integridad de datos (emails y avatares pertenecen al dominio esperado)
- Bonus: Cada usuario tiene su avatar con su ID en la URL (consistencia data)

---

## Como ejecutar las pruebas — 4 pasos

### Paso 1: Tener JDK 21 instalado

Verificar en terminal:

```cmd
java -version
```

Debe mostrar `21.x`. Si no lo tenes, descargalo de: https://adoptium.net/temurin/releases/?version=21

**NO necesitas Maven instalado.** El proyecto incluye Maven Wrapper.

### Paso 2: Clonar el repositorio

```cmd
git clone https://github.com/AlberthRuiz/reto-tecnico-serenity.git
cd reto-tecnico-serenity
```

### Paso 3: Configurar API key de Reqres

Reqres.in requiere una API key gratuita. Obtenerla asi:

1. Ir a https://app.reqres.in/signup
2. Registrarse (no pide tarjeta de credito)
3. Copiar la API key del dashboard (formato `reqres_xxxxxxxxxx`)
4. Crear un archivo `.env` en la raiz del proyecto con el siguiente contenido:

```
REQRES_API_KEY=reqres_tu_key_aqui
PLAYWRIGHT_HEADLESS=true
```

Reemplazar `reqres_tu_key_aqui` con tu API key real.

El archivo `.env` esta en `.gitignore` — no se commitea al repositorio.

### Paso 4: Ejecutar las pruebas

```cmd
run-tests.cmd
```

El script:
- Carga el `.env` automaticamente
- Descarga Maven la primera vez (unos 30 seg)
- Descarga Playwright Chromium la primera vez (unos 2 min, ~100 MB)
- Ejecuta los 13 scenarios
- Genera el reporte Serenity en `target/site/serenity/index.html`

---

## Ver el reporte localmente

Despues de ejecutar las pruebas, abrir:

```cmd
start target/site/serenity/index.html
```

**O ver el reporte publico directamente:**
https://alberthruiz.github.io/reto-tecnico-serenity/

---

## Ejecucion parcial (opcional)

Correr solo una seccion de las pruebas:

| Comando | Que corre |
|---|---|
| `run-tests.cmd` | Todo (web + API) — 13 scenarios |
| `run-tests.cmd "-Dcucumber.filter.tags=@web"` | Solo web (5 scenarios) |
| `run-tests.cmd "-Dcucumber.filter.tags=@api"` | Solo API (8 scenarios) |
| `run-tests.cmd "-Dcucumber.filter.tags=@homepage"` | Solo caso 1 |
| `run-tests.cmd "-Dcucumber.filter.tags=@search"` | Solo caso 3 (busqueda) |

---

## Abrir el proyecto en IntelliJ IDEA (opcional)

1. `File > Open` > seleccionar carpeta `reto-tecnico-serenity`
2. IntelliJ detecta el `pom.xml` y descarga dependencias
3. Click derecho en `src/test/java/com/prueba/tecnica/CucumberTestSuite.java` > Run
4. En la run configuration agregar Environment variable: `REQRES_API_KEY=tu_key`

---

## Abrir el proyecto en Eclipse (opcional)

1. `File > Import > Maven > Existing Maven Projects`
2. Seleccionar carpeta del proyecto
3. Click derecho en `CucumberTestSuite.java` > `Run As > JUnit Test`
4. Editar run configuration y agregar env var `REQRES_API_KEY`

---

## Arquitectura Screenplay

El proyecto implementa el patron Screenplay en ambos dominios (web y API) con la misma forma:

- **Actor** — `OnStage.theActorCalled(...)` / `OnStage.theActorInTheSpotlight()`
- **Ability** — `BrowseTheWebWithPlaywright` (web), `CallReqresApi` (API)
- **Task** — acciones reutilizables en `tasks/web/` y `tasks/api/` (OpenHomePage, CreateUser, ListUsers, etc.)
- **Question** — consultas sobre el estado en `questions/web/` y `questions/api/`
- **PageObject** — selectores en `ui/`

Para el lado API, la clase `ApiResponse` (`questions/api/ApiResponse.java`) implementa `Question<Response>` y actua como facade unificado: expone atajos (`ApiResponse.statusCode()`, `ApiResponse.field("id")`, `ApiResponse.stringList("data.email")`, `ApiResponse.objectList("data")`, `ApiResponse.headStatusCode(url)`) que delegan a Questions especializadas. De esta forma, los step definitions del API jamas acceden a `RestAssured` directamente — todo pasa por el Actor y los Questions, manteniendo congruencia con el lado web.

---

## Estructura del proyecto

```
reto-tecnico-serenity/
├── pom.xml                              Configuracion Maven + dependencias
├── serenity.conf                        Configuracion Serenity (URLs, browser)
├── run-tests.cmd                        Wrapper que carga .env y ejecuta
├── mvnw / mvnw.cmd                      Maven Wrapper (incluido, no requiere instalar)
├── .env                                 Tu API key (gitignored, se crea manual)
├── .github/workflows/ci.yml             CI/CD (GitHub Actions)
├── src/test/
│   ├── java/com/prueba/tecnica/
│   │   ├── CucumberTestSuite.java       Runner JUnit 5 + Cucumber
│   │   ├── abilities/                   Habilidades Screenplay (API client)
│   │   ├── models/                      DTOs JSON
│   │   ├── questions/                   Preguntas Screenplay (observaciones)
│   │   ├── stepdefinitions/             Glue: Gherkin -> Screenplay
│   │   ├── tasks/                       Acciones reutilizables (navegar, crear, etc)
│   │   ├── ui/                          PageObjects con selectores CSS
│   │   └── utils/                       Helper Config para leer serenity.conf
│   └── resources/
│       ├── features/web/                Features UI (homepage, documentation, search)
│       ├── features/api/                Features API (users)
│       ├── serenity.conf                Config Serenity
│       ├── junit-platform.properties    Config JUnit Platform
│       └── logback-test.xml             Config de logs
└── target/site/serenity/index.html      Reporte generado al ejecutar
```

---

## Problemas comunes

**Error: `403 Forbidden` en tests API**
- Tu `.env` no tiene `REQRES_API_KEY` o la key fue revocada
- Solucion: generar una key nueva en https://app.reqres.in y actualizar el `.env`

**Error: `mvnw.cmd no se reconoce`**
- No estas en la carpeta raiz del proyecto
- Solucion: `cd reto-tecnico-serenity` antes de ejecutar

**Error: `run-tests.cmd no se reconoce`**
- Usar `.\run-tests.cmd` en PowerShell (el prefijo `.\` es obligatorio)

**Primera ejecucion muy lenta (~3 min)**
- Maven descarga dependencias (100+ MB)
- Playwright descarga Chromium (100 MB)
- Las siguientes ejecuciones toman solo 20-30 segundos

---

## CI/CD

El repositorio incluye GitHub Actions (`.github/workflows/ci.yml`) que:
- Ejecuta los 13 scenarios en cada push
- Publica automaticamente el reporte actualizado en GitHub Pages
- URL: https://alberthruiz.github.io/reto-tecnico-serenity/

Para replicar el CI en un fork propio, configurar secret `RE