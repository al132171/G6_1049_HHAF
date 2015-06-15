@get
Feature: Caja búsqueda
  In order to  buscar actividades fácilmente
  As a cliente 
  I want disponer de una forma de buscar una actividad concreta
  
  Scenario Consultar una actividad no coincidencia
  Given Hay actividades en el sistema
  When Uso la búsqueda avanzada
  And No hay actividades activas relacionadas con los parametros de búsqueda
  Then No se muestra ninguna actividad como resultado de la búsqueda
