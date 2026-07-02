# Pruebas en Postman - API Cajero

Base URL:

```text
http://localhost:8080
```

Datos iniciales cargados automaticamente:

```text
Usuario: admin
Password: 12345
Cliente ID: 1
Cuenta ahorro ID: 1
Cuenta corriente ID: 2
Cajero ID: 1
```

Primero haces login. Luego copias el token y lo usas en los endpoints protegidos:

```text
Authorization: Bearer TU_TOKEN_AQUI
```

## 1. Login y crear sesion

Metodo: POST

```text
http://localhost:8080/auth/login
```

Body:

```json
{
  "username": "admin",
  "password": "12345"
}
```

Este endpoint genera el JWT y guarda un registro en la tabla sesiones.

## 2. Listar clientes

Metodo: GET

```text
http://localhost:8080/api/clientes
```

## 3. Listar cuentas del cliente

Metodo: GET

```text
http://localhost:8080/api/clientes/1/cuentas
```

## 4. Listar cajeros

Metodo: GET

```text
http://localhost:8080/api/cajeros
```

## 5. Depositar en una cuenta

Metodo: POST

```text
http://localhost:8080/api/cajero/depositos
```

Body:

```json
{
  "cuentaId": 1,
  "cajeroId": 1,
  "monto": 100.00
}
```

## 6. Retirar de una cuenta

Metodo: POST

```text
http://localhost:8080/api/cajero/retiros
```

Body:

```json
{
  "cuentaId": 1,
  "cajeroId": 1,
  "monto": 25.00
}
```

## 7. Consultar saldo de una cuenta

Metodo: GET

```text
http://localhost:8080/api/cajero/saldo?cuentaId=1&cajeroId=1
```

Este endpoint tambien registra una transaccion tipo CONSULTA.

## 8. Listar todas las transacciones

Metodo: GET

```text
http://localhost:8080/api/cajero/transacciones
```

## 9. Listar transacciones de una cuenta

Metodo: GET

```text
http://localhost:8080/api/cuentas/1/transacciones
```

## 10. Estadisticas generales

Metodo: GET

```text
http://localhost:8080/api/cajero/estadisticas
```

## 11. Estadisticas por mes y año

Metodo: GET

```text
http://localhost:8080/api/cajero/estadisticas/periodo?mes=7&year=2026
```

## Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

## H2 Console

```text
http://localhost:8080/h2-console
```

```text
JDBC URL: jdbc:h2:mem:atmdb
User: sa
Password: dejar vacio
```

## Orden recomendado segun el flujo

1. Login
2. Listar clientes
3. Listar cuentas del cliente
4. Listar cajeros
5. Depositar
6. Retirar
7. Consultar saldo
8. Ver transacciones
9. Ver reportes
