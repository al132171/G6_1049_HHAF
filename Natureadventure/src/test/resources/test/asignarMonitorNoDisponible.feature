@put
Feature:  Disponibilidad de los monitores
  In order to conocer la disponibilidad de los monitores
  As a gerente
  I want to asignar un monitor a cada reserva que esté pendiente
  
Scenario: No existen monitores disponibles 
Given Reservas sin asignar monitor y existen monitores 
When Pulso el botón de asignar un monitor 
Then Se muestra un mensaje indicando que no existen monitores y cancelo la reserva


