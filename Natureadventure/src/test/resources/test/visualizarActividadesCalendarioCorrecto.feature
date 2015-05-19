@get
Feature: Persistencia de los datos en el calendario.
  In order to Consultar fechas ocupadas con actividades
  As a Monitor
  I want visualizar un calendario que muestre interactivamente en qué fechas
    hay programadas actividades supervisadas por mí.
  
  Scenario: Visualización general de días con actividades programadas
    Given Hay una serie de reservas realizadas
    When Se consultan el calendario de reservas
    Then Se muestran marcados los días con alguna actividad programada
