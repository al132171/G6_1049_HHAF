@put
Feature:  Disponibilidad de los monitores
  In order to conocer la disponibilidad de los monitores
  As a gerente
  I want to asignar un monitor a cada reserva que esté pendiente
  
  Scenario: Consultar la disponibilidad para una reserva
    Given Reservas sin asignar monitor
    When Pulso de botón de asignar monitor y se muestran los monitores disponibles para esa actividad y selecciono uno
    Then La reserva se muestra en el apartado de aceptadas
    
    
