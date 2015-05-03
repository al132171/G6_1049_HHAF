$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("test/anyadirActividadGerenteCorrecto.feature");
formatter.feature({
  "id": "mantener-información-actividades",
  "tags": [
    {
      "name": "@put",
      "line": 1
    }
  ],
  "description": "In order to poder controlar fácilmente las actividades\nAs a gerente\nI want to poder consultar, añadir, editar y dar de baja actividades en el sistema",
  "name": "Mantener información actividades",
  "keyword": "Feature",
  "line": 2
});
formatter.scenario({
  "id": "mantener-información-actividades;añadir-una-actividad-al-sistema",
  "description": "",
  "name": "Añadir una actividad al sistema",
  "keyword": "Scenario",
  "line": 7,
  "type": "scenario"
});
formatter.step({
  "name": "No tengo actividades repetidas en el sistema",
  "keyword": "Given ",
  "line": 8
});
formatter.step({
  "name": "Añado una actividad al sistema",
  "keyword": "When ",
  "line": 9
});
formatter.step({
  "name": "El sistema añade la actividad y muestra las existentes",
  "keyword": "Then ",
  "line": 10
});
formatter.match({
  "location": "AnyadirActividadGerenteCorrecto.list_displayed_no_rep()"
});
formatter.result({
  "duration": 8502176460,
  "status": "passed"
});
formatter.match({
  "location": "AnyadirActividadGerenteCorrecto.add_actividad()"
});
formatter.result({
  "duration": 6136937491,
  "status": "passed"
});
formatter.match({
  "location": "AnyadirActividadGerenteCorrecto.list_actividades()"
});
formatter.result({
  "duration": 201319908,
  "status": "passed"
});
formatter.uri("test/anyadirActividadGerenteRepetida.feature");
formatter.feature({
  "id": "mantener-información-actividades",
  "tags": [
    {
      "name": "@put",
      "line": 1
    }
  ],
  "description": "In order to poder controlar fácilmente las actividades\nAs a gerente\nI want to poder consultar, añadir, editar y dar de baja actividades en el sistema",
  "name": "Mantener información actividades",
  "keyword": "Feature",
  "line": 2
});
formatter.scenario({
  "id": "mantener-información-actividades;añadir-una-actividad-repetida",
  "description": "",
  "name": "Añadir una actividad repetida",
  "keyword": "Scenario",
  "line": 7,
  "type": "scenario"
});
formatter.step({
  "name": "Tengo actividades repetidas en el sistema",
  "keyword": "Given ",
  "line": 8
});
formatter.step({
  "name": "Añado una actividad repetida al sistema",
  "keyword": "When ",
  "line": 9
});
formatter.step({
  "name": "El sistema muestra un mensaje de error",
  "keyword": "Then ",
  "line": 10
});
formatter.match({
  "location": "AnyadirActividadGerenteRepetida.list_displayed_no_rep()"
});
formatter.result({
  "duration": 6945783865,
  "status": "passed"
});
formatter.match({
  "location": "AnyadirActividadGerenteRepetida.add_actividad_rep()"
});
formatter.result({
  "duration": 6083220360,
  "status": "passed"
});
formatter.match({
  "location": "AnyadirActividadGerenteRepetida.find_error()"
});
formatter.result({
  "duration": 224436243,
  "status": "passed"
});
formatter.uri("test/asignarMonitorNoDisponible.feature");
formatter.feature({
  "id": "disponibilidad-de-los-monitores",
  "tags": [
    {
      "name": "@put",
      "line": 1
    }
  ],
  "description": "In order to conocer la disponibilidad de los monitores\nAs a gerente\nI want to asignar un monitor a cada reserva que esté pendiente",
  "name": "Disponibilidad de los monitores",
  "keyword": "Feature",
  "line": 2
});
formatter.scenario({
  "id": "disponibilidad-de-los-monitores;no-existen-monitores-disponibles",
  "description": "",
  "name": "No existen monitores disponibles",
  "keyword": "Scenario",
  "line": 7,
  "type": "scenario"
});
formatter.step({
  "name": "reservas sin asignar monitor y existen monitores",
  "keyword": "Given ",
  "line": 8
});
formatter.step({
  "name": "Pulso el botón de asignar un monitor",
  "keyword": "When ",
  "line": 9
});
formatter.step({
  "name": "Se muestra un mensaje indicando que no existen monitores y cancelo la reserva",
  "keyword": "Then ",
  "line": 10
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.uri("test/asignarMonitorReserva.feature");
formatter.feature({
  "id": "disponibilidad-de-los-monitores",
  "tags": [
    {
      "name": "@put",
      "line": 1
    }
  ],
  "description": "In order to conocer la disponibilidad de los monitores\nAs a gerente\nI want to asignar un monitor a cada reserva que esté pendiente",
  "name": "Disponibilidad de los monitores",
  "keyword": "Feature",
  "line": 2
});
formatter.scenario({
  "id": "disponibilidad-de-los-monitores;consultar-la-disponibilidad-para-una-reserva",
  "description": "",
  "name": "Consultar la disponibilidad para una reserva",
  "keyword": "Scenario",
  "line": 7,
  "type": "scenario"
});
formatter.step({
  "name": "Reservas sin asignar monitor",
  "keyword": "Given ",
  "line": 8
});
formatter.step({
  "name": "Pulso de botón de asignar monitor y se muestran los monitores disponibles para esa actividad y selecciono uno",
  "keyword": "When ",
  "line": 9
});
formatter.step({
  "name": "La reserva se muestra en el apartado de aceptadas",
  "keyword": "Then ",
  "line": 10
});
formatter.match({
  "location": "AsignarMonitorReserva.list_reservas_pendientes()"
});
formatter.result({
  "duration": 6370966024,
  "status": "passed"
});
formatter.match({
  "location": "AsignarMonitorReserva.click_button_asignar_monitor()"
});
formatter.result({
  "duration": 1770073398,
  "status": "passed"
});
formatter.match({
  "location": "AsignarMonitorReserva.show_in_aceptadas()"
});
formatter.result({
  "duration": 2724003849,
  "status": "failed",
  "error_message": "org.openqa.selenium.remote.UnreachableBrowserException: Error communicating with the remote browser. It may have died.\nBuild info: version: \u00272.45.0\u0027, revision: \u00275017cb8e7ca8e37638dc3091b2440b90a1d8686f\u0027, time: \u00272015-02-27 09:10:26\u0027\nSystem info: host: \u0027fer-HP-ProBook-4520s\u0027, ip: \u0027127.0.1.1\u0027, os.name: \u0027Linux\u0027, os.arch: \u0027amd64\u0027, os.version: \u00273.2.0-79-generic\u0027, java.version: \u00271.7.0_75\u0027\nDriver info: driver.version: RemoteWebDriver\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:593)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:352)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementById(RemoteWebDriver.java:393)\n\tat org.openqa.selenium.By$ById.findElement(By.java:214)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:344)\n\tat test.AsignarMonitorReserva.show_in_aceptadas(AsignarMonitorReserva.java:50)\n\tat ✽.Then La reserva se muestra en el apartado de aceptadas(test/asignarMonitorReserva.feature:10)\nCaused by: org.apache.http.conn.HttpHostConnectException: Connect to 127.0.0.1:7057 [/127.0.0.1] failed: Conexión rehusada\n\tat org.apache.http.impl.conn.HttpClientConnectionOperator.connect(HttpClientConnectionOperator.java:142)\n\tat org.apache.http.impl.conn.PoolingHttpClientConnectionManager.connect(PoolingHttpClientConnectionManager.java:319)\n\tat org.apache.http.impl.execchain.MainClientExec.establishRoute(MainClientExec.java:363)\n\tat org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:219)\n\tat org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:195)\n\tat org.apache.http.impl.execchain.RetryExec.execute(RetryExec.java:86)\n\tat org.apache.http.impl.execchain.RedirectExec.execute(RedirectExec.java:108)\n\tat org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:184)\n\tat org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:72)\n\tat org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:57)\n\tat org.openqa.selenium.remote.internal.ApacheHttpClient.fallBackExecute(ApacheHttpClient.java:144)\n\tat org.openqa.selenium.remote.internal.ApacheHttpClient.execute(ApacheHttpClient.java:72)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:133)\n\tat org.openqa.selenium.firefox.internal.NewProfileExtensionConnection.execute(NewProfileExtensionConnection.java:165)\n\tat org.openqa.selenium.firefox.FirefoxDriver$LazyCommandExecutor.execute(FirefoxDriver.java:362)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:572)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:352)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementById(RemoteWebDriver.java:393)\n\tat org.openqa.selenium.By$ById.findElement(By.java:214)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:344)\n\tat test.AsignarMonitorReserva.show_in_aceptadas(AsignarMonitorReserva.java:50)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:606)\n\tat cucumber.runtime.Utils$1.call(Utils.java:37)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:13)\n\tat cucumber.runtime.Utils.invoke(Utils.java:31)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:37)\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:298)\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:48)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:91)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:93)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:37)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:98)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)\nCaused by: java.net.ConnectException: Conexión rehusada\n\tat java.net.PlainSocketImpl.socketConnect(Native Method)\n\tat java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:339)\n\tat java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:200)\n\tat java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:182)\n\tat java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)\n\tat java.net.Socket.connect(Socket.java:579)\n\tat org.apache.http.conn.socket.PlainConnectionSocketFactory.connectSocket(PlainConnectionSocketFactory.java:72)\n\tat org.apache.http.impl.conn.HttpClientConnectionOperator.connect(HttpClientConnectionOperator.java:125)\n\t... 58 more\n"
});
formatter.uri("test/editarActividadGerenteCorrecto.feature");
formatter.feature({
  "id": "mantener-información-actividades",
  "tags": [
    {
      "name": "@put",
      "line": 1
    }
  ],
  "description": "In order to poder controlar fácilmente las actividades\nAs a gerente\nI want to poder consultar, añadir, editar y dar de baja actividades en el sistema",
  "name": "Mantener información actividades",
  "keyword": "Feature",
  "line": 2
});
formatter.scenario({
  "id": "mantener-información-actividades;editar-una-actividad",
  "description": "",
  "name": "Editar una actividad",
  "keyword": "Scenario",
  "line": 7,
  "type": "scenario"
});
formatter.step({
  "name": "Tengo actividades en el sistema",
  "keyword": "Given ",
  "line": 8
});
formatter.step({
  "name": "Modifico una actividad existente en el sistema",
  "keyword": "When ",
  "line": 9
});
formatter.step({
  "name": "Se muestra el listado de actividades en el sistema",
  "keyword": "Then ",
  "line": 10
});
formatter.match({
  "location": "ListarActividadesGerente.list_displayed()"
});
formatter.result({
  "duration": 6660737816,
  "status": "passed"
});
formatter.match({
  "location": "EditarActividadGerenteCorrecto.edit_actividad()"
});
formatter.result({
  "duration": 609466,
  "status": "failed",
  "error_message": "java.lang.NullPointerException\n\tat test.EditarActividadGerenteCorrecto.edit_actividad(EditarActividadGerenteCorrecto.java:29)\n\tat ✽.When Modifico una actividad existente en el sistema(test/editarActividadGerenteCorrecto.feature:9)\n"
});
formatter.match({
  "location": "EditarActividadGerenteCorrecto.find_error()"
});
formatter.result({
  "status": "skipped"
});
});