# MICROSERVICES-EXAM-API

# Chalenge
    Projeto aberto - JAVA ou Kotlin 
    Pode usar qualquer framework spring,  quarkus, micronaut etc

    Criação de 2 microsservicos trocando informações
    Esses microsservicos pode ser:
    microsservico 1 - CRUD
    microsservico 2 - Consulta dados do microsservico 1
    A comunicação para ser feita por REST, gRPC, mensagem (o que preferir)

# Description
* O Service CRUD - tem por finalidade prover simples crud de produto. Ele faz envio de mensagem com RabbitMQ. 
* O Service PAGAMENTO - tem por finalidade prover informações de pagamento. Ele recebe mensagens com RabbitMQ. 

* Ao criar um produto no Service CRUD, uma mensagem é enviada para o Service Pagamento. Esta mensagem faz com que o módulo de pagamento tenha informações (id do produto e qtd em estoque) dos produtos disponíveis. 

* OBS: Devido ás condições de admnistração de tempo, Foi criado apenas uma fila para exemplificar a rotina.

# Build API
* Configure sua base de dados.
    Crie dois schemas num database mysql: 
        crud
        pagamento

* 1 - Execute o docker-compose (docker-compose up -d), contido neste diretório. Ele vai subir um container com imagem do rabbitMQ.

* 2 - Configure o RabbitMQ (http://localhost:15672/, user/pass: admin)

        QUEUES -> ADD
            NAME : crud.product.queue

        EXCHANGES -> ADD 
            NAME: crud.exchange

        EXCHANGES -> crud.exchange
            Add binding from this exchange
                TO QUEUE : crud.product.queue
                Routing key: crud.product.routingkey

* 3 - Execute os microservices.

* OBS: No diretório do projeto, Execute o comando : mvn clean package -DskipTests (Caso possua mvn instalado) e um .jar será gerado na pasta ./target
* OU, importe o projeto em sua IDE e execute.

## TESTES

## SWAGGER:
* Ele foi adicionado ao projeto com intuito de trazer uma breve documentação e testar a aplicação
* CRUD Service - Acesse : http://localhost:8081/crud/swagger-ui/index.html
* PAGAMENTO Service - Acesse : http://localhost:8082/pagamento/swagger-ui/index.html
* Execute os teste com auxilio da interface

## POSTMAN ou OUTROS
* É possível também realizar testes dos endpoints chamando via POSTMAN.
