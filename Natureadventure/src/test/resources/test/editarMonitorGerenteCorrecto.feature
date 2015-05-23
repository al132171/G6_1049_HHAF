@put
Feature: Gestionar monitores
  In order to mantener monitores
  As a gerente 
  I want poder consultar, añadir, editar y dar de baja monitores en el sistema

  Scenario: Editar monitores
    Given Hay monitores registrados en el sistema
    When Se editan los datos de un monitor
    Then Se muestra en el listado de monitores del sistema
