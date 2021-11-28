# new feature
# Tags: optional

Feature: Sección de empleos Choucair
  Como: Aspirante a una posicione laboral en Choucair
  Quiero: Ingresar a la sección de empleos de la pagina de Choucair
  Para: Conocer las ofertas de empleo y los beneficios de trabajar en Choucair

  Background:
    Given Ingreso a la pagina de Choucair
    When  Ingreso a la seccion Empleos

  @Test_001
  Scenario Outline: Validar iconos ¿Qué es ser Choucair? y Prepararse para aplicar
    And Hago clic en el icono "<arg0>"
    Then Me desplazo a la seccion de "<arg1>"
    Examples:
      | arg0                    | arg1                    |
      | ¿Qué es ser Choucair?   | SER CHOUCAIR            |
      | Prepararse para aplicar | PREPARARSE PARA APLICAR |

  @Test_002
  Scenario: Ingresar al portal de empleos
    And   Hago clic en el boton "Ir al portal de empleos"
    When  Preciono clic en el boton "CONTINUAR"
    Then  Soy redirigido al Portal de Magneto "Choucair-Testing"

  @Test_003
  Scenario Outline: Verificar enlaces de la sección Prepararse para aplicar
    When  Hago clic en el icono "<arg0>"
    Then  se presentan todos los enlaces de la sección
    Examples:
      | arg0                    |
      | Prepararse para aplicar |
