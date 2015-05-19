@put
Feature: Gestionar monitores
  In order to mantener monitores
  As a gerente 
  I want poder consultar, añadir, editar y dar de baja monitores en el sistema
  
  Scenario: Registrar un monitor repetido
    Given Existen monitores en el sistema
    When Se introducen los datos de un monitor que ya existe
    Then Se muestra mensaje de avisando de que ese monitor ya existe
