@get
Feature: Loguearse utilizando usuario y contraseña
  In order to loguearse
  As a gerente
  I want to acceder a la zona de gerencia

  Scenario: Datos erróneos 
    Given Un gerente no registrado en el sistema
    When Se identifica en el sistema utilizando un usuario y/o contraseña incorrectos 
    Then Se muestra un mensaje advirtiendo que los datos son erróneos
