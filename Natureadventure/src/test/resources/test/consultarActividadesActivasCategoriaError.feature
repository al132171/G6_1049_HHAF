@get
Feature: Consultar las actividades
In order to ver las actividades disponibles
As a cliente
I want consultar las actividades actualmente disponibles
  
  Scenario: Consultar las actividades de una categoría
    Given Una cotegoría de actividades en el sistema
    When Consulto dicha categoría
    And No xisten actividades activas en el sistema pertenecientes a esa categoría
    Then No se muestra ninguna actividad
