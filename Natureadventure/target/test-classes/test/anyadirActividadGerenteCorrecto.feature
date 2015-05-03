@put
Feature:  Mantener información actividades
  In order to poder controlar fácilmente las actividades
  As a gerente
  I want to poder consultar, añadir, editar y dar de baja actividades en el sistema
  
  Scenario: Añadir una actividad al sistema 
    Given No tengo actividades repetidas en el sistema 
    When Añado una actividad al sistema 
    Then El sistema añade la actividad y muestra las existentes
    
    