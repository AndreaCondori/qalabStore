@MyStore

Feature: Product - Store
  Background:
    Given estoy en la página de la tienda

  Scenario Outline: Validación del precio de un producto y la autenticación
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
      | andreacondori@gmail.com     | Qalab2024 | AUTOS     | 2        |
@ProductoTalla
Scenario Outline: Agregar diferentes tallas de un producto y validar precios
  And me logueo con mi usuario "<usuario>" y clave "<clave>"
  When navego a la categoría "<categoria>"
  And agrego la talla "<talla>" del primer producto al carrito
  Then valido en el popup la confirmación del producto agregado
  And valido que el precio para la talla "<talla>" sea correcto
  When visualizo el carrito
  Then verifico que cada talla del producto se liste como un ítem separado
  And valido que el precio total en el carrito sea la suma de los precios por talla

  Examples:
    | usuario                 | clave     | categoria | talla      |
    | andreacondori@gmail.com | Qalab2024 | Art       | 40x60cm    |
    | andreacondori@gmail.com | Qalab2024 | Art       | 40x90cm    |
    | andreacondori@gmail.com | Qalab2024 | Art       | 40x120cm   |




