                                                                    English bellow

                                                           Micro-Scrum-Jira – Proyecto Portfolio
Idea general:
Micro-Scrum-Jira es un backend de microservicios que gestiona proyectos, sprints y tareas al estilo Scrum/Jira.
Su arquitectura desacoplada permite escalar cada servicio según la necesidad, centralizando accesos y rutas a través de un gateway con autenticación segura basada en tokens y login encriptado.
El sistema está diseñado para mantener claridad, organización y control total, mostrando mi capacidad para estructurar microservicios eficientes, escalables y mantenibles.
Incluye tests unitarios que cubren lo más relevante de cada microservicio y tests de integración realizados solo en msvc-project.
Fue desarrollado en 10 semanas como proyecto de portfolio, con unas 8.000 líneas de código, suficiente para mostrar funcionalidad completa sin ser una aplicación a escala real tipo Atlassian.
El proyecto está en continua evolución. Cualquier sugerencia será más que bienvenida.

Documentación y repositorios:                                                                
Instrucciones técnicas y guía de ejecución:
https://docs.google.com/document/d/1UTwRGD18WAeIJ0sS4-FN5FSXGm1__N0t8S0YQBSuR_Q/edit?tab=t.0

Configuraciones y archivos Docker/BD comprimidos, para uso con imagenes publicadas:
https://github.com/arielliffschitz/docker-setup-mscrum-jira

Imágenes Docker publicadas: https://hub.docker.com/repository/docker/aliffschitz/mscrumjira/tags

Documentación de endpoints : https://docs.google.com/document/d/1KDAECAmoGt9KdEl6zFSWx-yUWjdf-Ur7fQB6gbo_TQU

Tests y resultados: https://docs.google.com/spreadsheets/d/1m5KRascTl62w08mZkUSUscqvskn4TFI2TlAQrmn8cuM/edit?gid=0#gid=0

Especificaciones técnicas:  
Lenguaje y entorno: Java 21, Eclipse, VS
Framework y módulos Spring: Spring Boot 3.5.9, Spring Security 6.2, Spring Data JPA, Spring Cloud Gateway, Feign Clients
Base de datos: MySQL 8.0.45 con persistencia en volumen Docker
Seguridad: OAuth2 / JWT con login encriptado y roles, integrado con el gateway
Buenas prácticas: Clean Code, desacoplamiento, separación de responsabilidades
Tests: JUnit5, Mockito, test de integración para msvc-project (Project y Sprint)
Arquitectura y Funcionamiento

Flujo general:
 Login con OAuth2 Authorization Code → emisión de JWT → consumo de endpoints vía Gateway → validación de JWT y roles → enrutamiento al microservicio correspondiente → comunicación interna entre servicios mediante Feign cuando es necesario.
Microservicios
msvc-oauth
Authorization Server OAuth2/OIDC. Gestiona login y emite access tokens firmados (JWT) utilizados por el resto del sistema.
msvc-gateway-server
Spring Cloud Gateway. Punto único de acceso. Valida JWT, aplica autorización por roles y enruta peticiones a cada microservicio.
msvc-user
Gestiona usuarios, roles y teams (relaciones many-to-many). Valida restricciones de equipos asociados a sprints.
msvc-project
Gestiona proyectos y sprints. Controla transiciones de estado, impide eliminar proyectos con sprints activas y, al archivar, genera snapshot en audit.
msvc-task
Gestiona Product-Backlog y SprintBacklog, creación y movimiento de tasks entre backlogs. Valida Project, Sprint y Team vía Feign antes de cada operación. Controla estados y orquesta auditoría.
msvc-product-backlog / msvc-sprint-backlog Responsables de la persistencia y lógica específica de cada backlog.
msvc-audit
Centraliza trazabilidad: tasks archivadas, histórico de movimientos y snapshots de Project y Sprint.
mscrum-lib-jira (librería compartida)
Contiene DTOs/records, enums de dominio y clases base (BaseEntity, BaseCreateEntity) con auditoría, para que todos los microservicios usen lo mismo y no se repita código.
Relaciones entre servicios
 Gateway centraliza acceso y seguridad; OAuth emite los JWT consumidos por todos.
 Task consulta Project/Sprint/audit vía Feign y notifica a Audit cualquier movimiento.
 Project notifica a Audit al archivar.
 User valida equipos utilizados por Sprint.La arquitectura mantiene separación estricta de responsabilidades, comunicación explícita entre servicios y control de estados en cada agregado.

ENGLISH
                                                                Micro-Scrum-Jira – Portfolio Project
General Idea:
Micro-Scrum-Jira is a microservices backend that manages projects, sprints, and tasks in a Scrum/Jira style.
Its decoupled architecture allows each service to scale according to demand, centralizing access and routing through a gateway with secure token-based authentication and encrypted login.
The system is designed to maintain clarity, organization, and full control, demonstrating my ability to structure efficient, scalable, and maintainable microservices.
It includes unit tests covering the most relevant parts of each microservice and integration tests performed only in msvc-project.
It was developed over 10 weeks as a portfolio project, with approximately 8,000 lines of code—enough to demonstrate full functionality without being a large-scale enterprise application like Atlassian.

Documentation and Repositories:
Technical guide and execution instructions:
https://docs.google.com/document/d/1W1b3Xv23ONioh3TEFAy0VqbTiChldj4JW0wzbjhg2-A/edit?tab=t.0

Compressed Docker/DB configuration files for use with Docker Hub images: 
https://github.com/arielliffschitz/docker-setup-mscrum-jira

Published Docker images: https://hub.docker.com/repository/docker/aliffschitz/mscrumjira/tags

Endpoints documentation: https://docs.google.com/document/d/1KDAECAmoGt9KdEl6zFSWx-yUWjdf-Ur7fQB6gbo_TQU

Tests and results : https://docs.google.com/spreadsheets/d/1m5KRascTl62w08mZkUSUscqvskn4TFI2TlAQrmn8cuM/edit?gid=0#gid=0

Technical Specifications
Language & Environment: Java 21, Eclipse, Visual Studio
Spring Ecosystem: Spring Boot 3.5.9, Spring Security 6.2, Spring Data JPA, Spring Cloud Gateway, Feign Clients
Database: MySQL 8.0.45, Docker volume persistence
Security: OAuth2 / JWT, encrypted login with roles, gateway integration
Architecture & Principles: Clean Code, decoupling, separation of responsibilities
Testing: JUnit 5, Mockito; integration tests for msvc-project (Project and Sprint)

