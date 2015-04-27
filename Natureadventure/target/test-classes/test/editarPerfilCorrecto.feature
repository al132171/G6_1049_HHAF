@get
Feature: Editar perfil del gerente
  In order to administrar mi perfil de usuario 
  As a gerente
  I want to poder editar mi perfil de usuario
  

  Scenario: Loguearse como gerente 
    Given Un gerente logueado en el sistema
    When Modifico mis datos desde el formulario para editar mi perfil 
    Then Se actualizan mis cambios y se muestra mi perfil actualizado