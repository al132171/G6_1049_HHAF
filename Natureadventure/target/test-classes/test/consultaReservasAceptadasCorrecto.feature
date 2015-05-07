@get
Feature: Consultar reservas activas
  In order to asignar monitores a las reservas realizadas
  As a gerente
  I want to poder consultar las reservas activas a las que no se les ha asignado un monitor y las que han sido aceptadas
  
  Scenario: Consultar correctamente las reservas aceptadas
    Given Existen reservas que han sido asignadas a un monitor 
    When Voy al apartado de reservas aceptadas 
    Then El sistema muestra las reservas que han sido aceptadas
