@get
Feature: Editar perfil del gerente
  In order to administrar mi perfil de usuario 
  As a gerente
  I want to poder editar mi perfil de usuario
  

  Scenario: Campos vacíos
    Given Un gerente logueado en el sistema en editar perfil
    When Modifico mis datos y dejo campos vacíos en el formulario
    Then El sistema me muestra un error
   