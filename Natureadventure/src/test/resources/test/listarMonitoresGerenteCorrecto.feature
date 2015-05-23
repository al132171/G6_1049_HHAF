@get
Feature: Gestionar monitores
  In order to mantener monitores
  As a gerente 
  I want poder consultar, añadir, editar y dar de baja monitores en el sistema
  
  Scenario: Listar monitores
    Given Hay monitores registrados en el sistema
    When Se consultan los monitores del sistema
    Then Se muestra una lista con los monitores existentes en el sistema
