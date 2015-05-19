@get
Feature: Gestionar monitores
  In order to mantener monitores
  As a gerente 
  I want poder consultar, añadir, editar y dar de baja monitores en el sistema
  
  Scenario: Listar monitores
    Given No hay monitores registrados en el sistema
    When Se consultan los monitores del sistema
    Then Se muestra un mensaje indicando que no existen monitores registrados en el sistema
