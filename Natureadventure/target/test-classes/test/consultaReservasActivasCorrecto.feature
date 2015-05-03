@get
Feature: Consultar reservas activas
  In order to asignar monitores a las reservas realizadas
  As a gerente
  I want to poder consultar las reservas activas a las que no se les ha asignado un monitor y las que han sido aceptadas
  
  Scenario: Consultar correctamente las pendientes activas
    Given Tengo reservas en el sistema 
    When Voy al apartado de reservas activas 
    Then Las reservas activas se muestran en pantalla
    