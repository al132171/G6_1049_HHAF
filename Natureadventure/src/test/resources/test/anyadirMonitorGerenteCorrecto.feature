@put
Feature: Gestionar monitores
  In order to mantener monitores
  As a gerente 
  I want poder consultar, añadir, editar y dar de baja monitores en el sistema
  
  Scenario: Registrar un nuevo monitor
    Given No existe el monitor en el sistema
    When Se introduce un monitor nuevo
    Then se genera automáticamente un nombre de usuario y contraseña y se de de alta a ese monitor en el sistema
