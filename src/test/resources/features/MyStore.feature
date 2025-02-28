Feature: Product - Store

  Background:
    Given estoy en la página de la tienda

  @id:1@regression
  Scenario Outline: Validación del precio de un producto y autenticación
    And me logueo con mi usuario "<usuario>" y clave "<clave>"
    When navego a la categoria "<categoria>" y subcategoria "Men"
    And agrego <cantidad> unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito

    Examples:
      | usuario                     | clave     | categoria | cantidad |
      | andreacondori@gmail.com     | Qalab2024 | CLOTHES   | 2        |
      | usuario_incorrecto@test.com | Qalab2024 | CLOTHES   | 2        |

  @id:2@regression
  Scenario Outline: Verificar que los productos se agrupen correctamente por talla y precios
    And me logueo con mi usuario "<usuario>" y clave "<clave>"
    When navego a la categoría "<categoria>"
    And agrego las tallas "<tallas>" del primer producto al carrito
    Then verifico que cada talla "<tallas>" del producto se liste como un ítem separado en el carrito

    Examples:
      | usuario                 | clave     | categoria | tallas                   | precios                          |
      | andreacondori@gmail.com | Qalab2024 | Art       | 80x120cm,40x60cm,60x90cm | 79,00 PEN, 29,00 PEN, 49,00 PEN    |
