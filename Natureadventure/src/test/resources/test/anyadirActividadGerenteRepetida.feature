@put
Feature:  Mantener información actividades
  In order to poder controlar fácilmente las actividades
  As a gerente
  I want to poder consultar, añadir, editar y dar de baja actividades en el sistema
  
  Scenario: Añadir una actividad repetida 
    Given Tengo actividades repetidas en el sistema
    When Añado una actividad repetida al sistema
    Then El sistema muestra un mensaje de error