# RestAssured API Automation Activity
Este repositorio contiene el código que permite ejercutar tests automatizados sobre la API de: https://petstore.swagger.io/v2.

Los casos de testeo planteados son pensados a partir las funcionalidades que se mencionan de la API:
1. Crear un usuario.
2. Hacer login con un usuario recién creado.
3. Listar todas las mascotas que tengan el status “disponible”.
4. Consultar los datos de una mascota en específico (cualquiera).
5. Crear una orden (compra) para una mascota.
6. Hacer el logout a la aplicación.

## Casos de prueba propuestos
Se me ocurrieron 9 casos de prueba, divididos en 3 grupos, aquí va un pequeño resumen de ello:
### Pruebas de /user
Definidos en [UserTests.java](src/test/java/com/automation/tests/UserTests.java), contiene 4 casos de prueba:
1. Se puede crear un usuario con los campos correctos
2. El sistema no debe dejar crear un usuario con datos en un formato incorrecto
3. Se puede iniciar sesión con un usuario recién creado
4. Se puede hacer un logout
### Pruebas de /pet
Definidos en [PetsTests.java](src/test/java/com/automation/tests/PetsTests.java), contiene 3 casos de prueba:
1. Obtener todas las mascotas en estado 'Disponible'
2. Consultar los datos de una mascota en específico
3. Consultar los datos de una mascota con un ID inexistente retorna un 404
###
Definidos en [StoreTests.java](src/test/java/com/automation/tests/StoreTests.java), contiene 2 casos de prueba:
1. Crear una orden de compra para una mascota
2. No se debería poder generar una orden con cantidad de 0