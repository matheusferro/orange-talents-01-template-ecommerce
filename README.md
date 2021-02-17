# E-commerce - API

## Descrição
API construida para simular um e-commerce. Com as funcionalidades de criar categorias, produtos, perguntas, avaliações e realizar compra.

## Setup
```
> git clone https://github.com/matheusferro/orange-talents-01-template-casa-do-codigo.git
> mvn dependency:resolve dependency:resolve-plugins
> mvn clean package
> java -jar .\target\ecommerce-1.jar
```

## Restrições
- Não utilizar bibliotecas que geram código. (EX: Lombok, Model mapper, Map Struct ...)
- Somente dois setters, por classe dominante.
- Não utilizar classes services.

## Endpoints

### Usuário

- Login (usuário e senha criados ao iniciar aplicação):
```
curl localhost:8080/login -i -X POST \
-H 'Content-Type: application/json' \
-d '{"email":"email@teste.com", "senha":"123456"}'
```

Resultado:

```
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: 30 Feb 3099 06:42:12 GMT

{"tipo":"Barrer","token":"eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIxIiwiaWF0IjoxNjEzNDMxNzA1LCJleHAiOjEwMjUzNDMxNzA1fQ.W5mO9F0Cb_UxuiJJl4euLHGCSLQGt6_y43FnWr_y8uo"}
```

Utilizar o token do resultado no Header das próximas chamadas.

- Criar ```USUARIO```:
```
curl localhost:8080/usuario -i -X POST \
-H 'Content-Type: application/json' \
-H 'Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIxIiwiaWF0IjoxNjEzNDMwODg1LCJleHAiOjEwMjUzNDMwODg1fQ.yeb3tdyg96rN9mHa6TSDLVYOTmpBJb3VxvR30xSkjzE' \
-d '{"email":"teste@gmail.com","senha":"123456"}'
```

Resultado:
```
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: 30 Feb 3099 06:42:12 GMT
```

### Categoria

- Criar ```CATEGORIA``` (sem categoria mãe):
```
curl localhost:8080/categoria -i -X POST \
-H 'Content-Type: application/json' \
-H 'Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIxIiwiaWF0IjoxNjEzNDMwODg1LCJleHAiOjEwMjUzNDMwODg1fQ.yeb3tdyg96rN9mHa6TSDLVYOTmpBJb3VxvR30xSkjzE' \
-d '{"nome":"Tecnologia","idCategoriaMae":null}'
```
Resultado:
```
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: 30 Feb 3099 06:42:12 GMT

{"id":1,"nome":"Tecnologia","idCategoriaMae":null}
```

- Criar ```CATEGORIA``` (com categoria mãe):
```
curl localhost:8080/categoria -i -X POST \
-H 'Content-Type: application/json' \
-H 'Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIxIiwiaWF0IjoxNjEzNDMwODg1LCJleHAiOjEwMjUzNDMwODg1fQ.yeb3tdyg96rN9mHa6TSDLVYOTmpBJb3VxvR30xSkjzE' \
-d '{"nome":"Smartphone","idCategoriaMae":1}'
```
Resultado:
```
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: 30 Feb 3099 06:42:12 GMT

{"id":2,"nome":"Smartphone","idCategoriaMae":{"id":1,"nome":"Tecnologia","idCategoriaMae":null}}
```

### Produto

- Criar ```PRODUTO```:
```
curl localhost:8080/produto -i -X POST \
-H 'Content-Type: application/json' \
-H 'Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIxIiwiaWF0IjoxNjEzNDMwODg1LCJleHAiOjEwMjUzNDMwODg1fQ.yeb3tdyg96rN9mHa6TSDLVYOTmpBJb3VxvR30xSkjzE' \
-d '{"nome":"Produto teste", "valor":800, "quantidade":2, "descricao":"Descricao", "idCategoria":1, "caracteristicas":[{"nome":"marca","descricao":"a melhor"},	{"nome":"tamanho","descricao":"suficiente"},{"nome":"originalidade","descricao":"china"}]}'
```
Resultado:
```
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: 30 Feb 3099 06:42:12 GMT

{"nome":"Produto teste","valor":800,"quantidade":2,"caracteristicas":[{"nome":"tamanho","descricao":"suficiente"},{"nome":"marca","descricao":"a melhor"},{"nome":"originalidade","descricao":"china"}],"descricao":"Descricao","categoria":{"id":1,"nome":"Tecnologia","categoriaMae":{"nome":null}}}
```

#### Imagem

- Adicionar ```IMAGEM``` para o ```PRODUTO```: <br>
 Certifique-se que o terminal está no diretório do projeto.
```
curl localhost:8080/produto/1/imagem -i -X POST \
-H 'Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIxIiwiaWF0IjoxNjEzNDMwODg1LCJleHAiOjEwMjUzNDMwODg1fQ.yeb3tdyg96rN9mHa6TSDLVYOTmpBJb3VxvR30xSkjzE' \
-F imagens=@./produto.png
```
Resultado:
```
HTTP/1.1 100

HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: 30 Feb 3099 06:42:12 GMT

{"produto":"Produto teste","imagens":[{"link":"http://localhost:8080/images/produto.png"}]}
```

#### Opinião

- Adicionar ```AVALIACAO``` para o ```PRODUTO```:<br>
O produto avaliado não pode pertencer ao usuário logado.
```
curl localhost:8080/produto/1/avaliacao -i -X POST \
-H 'Content-Type: application/json' \
-H 'Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIyIiwiaWF0IjoxNjEzNDM2ODExLCJleHAiOjEwMjUzNDM2ODExfQ.S_EJQSqWAqccCwX1K3zacc77vJAZCYydeVNNEX7DaKw' \
-d '{"nota":5,"titulo":"Produto bom!","descricao":"Gostei muito do produto!"}'
```
Resultado:
```
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: 30 Feb 3099 06:42:12 GMT

{"nota":5,"titulo":"Produto bom!","descricao":"Gostei muito do produto!","nomeProduto":"Produto teste","usuario":"emailteste@gmail.com"}
```

#### Pergunta

- Adicionar ```IMAGEM``` para o ```PRODUTO```:
  A pergunta não pode ser direcionada ao produto do usuário logado.
```
curl localhost:8080/produto/1/pergunta -i -X POST \
-H 'Content-Type: application/json' \
-H 'Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIyIiwiaWF0IjoxNjEzNDM2ODExLCJleHAiOjEwMjUzNDM2ODExfQ.S_EJQSqWAqccCwX1K3zacc77vJAZCYydeVNNEX7DaKw' \
-d '{"titulo":"É emitido nota fiscal?"}'
```
Resultado:
```
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: 30 Feb 3099 06:42:12 GMT

[{"titulo":"É emitido nota fiscal?"}]
```

#### Detalhes

- Adiciona ```IMAGEM``` para o ```PRODUTO```:
```
curl localhost:8080/produto/1 -i -X GET \
-H 'Content-Type: application/json' \
-H 'Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTUwiLCJzdWIiOiIxIiwiaWF0IjoxNjEzNDMwODg1LCJleHAiOjEwMjUzNDMwODg1fQ.yeb3tdyg96rN9mHa6TSDLVYOTmpBJb3VxvR30xSkjzE'
```
Resultado:
```
HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: 30 Feb 3099 06:42:12 GMT

{"nome":"Produto teste","preco":800.00,"descricao":"Descricao","totalDeAvaliacoes":1,"mediaNota":5.0,"avaliacoes":[{"nota":5,"titulo":"Produto bom!","descricao":"Gostei muito do produto!","nomeProduto":"Produto teste","usuario":"emailteste@gmail.com"}],"perguntas":[{"titulo":"É emitido nota fiscal?"}],"caracteristicas":[{"nome":"marca","descricao":"a melhor"},{"nome":"originalidade","descricao":"china"},{"nome":"tamanho","descricao":"suficiente"}],"imagens":[{"link":"http://localhost:8080/images/produto.png"}]}
```
