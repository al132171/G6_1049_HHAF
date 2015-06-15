@put
Feature: Gestionar monitores
  In order to mantener monitores
  As a gerente 
  I want poder consultar, añadir, editar y dar de baja monitores en el sistema
  
  Scenario: Dar de baja un monitor existente
    Given Tenemos monitores registrados en el sistema
    When Se da de baja un monitor del sistema
    Then Su clave y contraseña de usuario ya no son válidas
