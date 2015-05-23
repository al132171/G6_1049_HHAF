@get
Feature: Caja búsqueda
  In order to  buscar actividades fácilmente
  As a cliente 
  I want disponer de una forma de buscar una actividad concreta
  
  Scenario Consultar una actividad
  Given Existen actividades en el sistema
  When Utilizo la búsqueda avanzada
  And Hay actividades activas relacionadas con los parametros de búsqueda
  Then Se muestran las actividades activas relacionadas con los parámetros de búsqueda
