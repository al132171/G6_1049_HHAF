@get
Feature: Editar perfil del gerente
  In order to administrar mi perfil de usuario 
  As a gerente
  I want to poder editar mi perfil de usuario
  

  Scenario: Consulta las actividades ofertadas en el sistema
    Given Tengo actividades en el sistema
    When Accedo al catálogo de actividades 
    Then El sistema muestra en pantalla las actividades que se ofrecen
