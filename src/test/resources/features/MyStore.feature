Feature: Product - Store

  Scenario Outline: Validación del precio de un producto y la autenticación
    Given estoy en la página de la tienda
    And me logueo con mi usuario "<usuario>" y clave "<clave>"
    When navego a la categoria "<categoria>" y subcategoria "Men"
    And agrego <cantidad> unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito

    Examples:
      | usuario                 | clave     | categoria | cantidad |
      | andreacondori@gmail.com | Qalab2024 | CLOTHES   | 2        |
      |                         |           |           |          |
      |                         |           |           |          |


