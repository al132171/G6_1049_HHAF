@get
Feature: Loguearse utilizando usuario y contraseña
  In order to loguearse
  As a gerente
  I want to acceder a la zona de gerencia

  Scenario: Loguearse como usuario 
    Given Un gerente registrado en el sistema
    When Se identifica en el sistema utilizando su usuario y contraseña 
    Then Se muestra el dashboard de gerencia