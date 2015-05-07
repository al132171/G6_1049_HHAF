@put
Feature:  Mantener información actividades
  In order to poder controlar fácilmente las actividades
  As a gerente
  I want to poder consultar, añadir, editar y dar de baja actividades en el sistema
  
  Scenario: Editar una actividad
    Given Tengo actividades en el sistema
    When Modifico una actividad existente en el sistema
    Then Se muestra el listado de actividades en el sistema
    