# Implementaçãoo de microsserviços - Exemplo

## Descrição

Implementaçãoo de uma aplicação de Campanha e Sócio Torcedor.
O detalhamento está em documento fornecido.

Cada camanha possui um período de vigência indicado por duas datas:
- data início de vigência
- data fim de vigência

Não ficou muito claro o que diferencia um período de vigência entre duas campanhas. 
Para  o desenvolvimento foi considerada a apenas a data de fim de vigência. Ou seja, duas campanhas não podem ter a mesma data de fim de vigência.

## Tecnologias utilizadas

- Springboot
- Lombok, para geração de *getters* , *setters* e padrão *builder*
- EntityListener, para notificação/auditoria. Quando uma campanha for alterada, uma notificação será enviada no *PostUpdate*
- Feign, substituindo o uso do RestTemplate
- Hystrix, para implementação do padrão *CircuitBreaker* e *Callback*
- EurekaServer, para implementação do *ServiceDiscoverer*
- Swagger, para documentação 

## Funcionamento

Foram desenvolvidas duas *APIs*:
- api-socio
- api-campanha

A *api-campanha* é responsável pela criação, alteração, deleção e listagem das campanhas. Como também, pela associação das camapanhas aos sócios.

A *api-socio* é repsonsável pela criação dos sócios e associação as campanhas.

Para o exemplo, a aplicação já carrega 4 times para utilização


``` json
    [
  {
    "id": 1,
    "nome": "Corinthians"
  },
  {
    "id": 2,
    "nome": "Palmeiras"
  },
  {
    "id": 3,
    "nome": "Santos"
  },
  {
    "id": 4,
    "nome": "Sao Paulo"
  }
]
```
E duas campanhas:

``` json
    [
  {
    "id": 5,
    "nomeCampanha": "Campanha 1",
    "dataIniVigencia": "2019-09-06",
    "dataFimVigencia": "2019-11-04",
    "timeId": 2,
    "nomeTime": "Palmeiras"
  },
  {
    "id": 6,
    "nomeCampanha": "Campanha 2",
    "dataIniVigencia": "2019-09-06",
    "dataFimVigencia": "2019-11-05",
    "timeId": 3,
    "nomeTime": "Santos"
  }
]
```

Também foi criada uma aplicação para execução do EurekaServer.

## APIs

### api-campanha

JSON da Campanha:
```json
{
    "id": ,
    "nomeCampanha": "Campanha 1",
    "dataIniVigencia": "2019-09-06",
    "dataFimVigencia": "2019-11-04",
    "timeId": ,
    "nomeTime": "Nome Time"
  }
```
OBS: Formato da data *YYYY-MM-dd*


##### 1) Lista todas as campanhas (GET)
```
http://localhost:9090/api/v1/campanhas
```
##### 2) Cria uma campanha (POST) 
Neste caso é necessário saber o *id* do time para enviá-lo na requisição. Deve obter o id listando os times pela requisição 6)

```
http://localhost:9090/api/v1/campanhas
```
##### 3) Busca uma campanha específica (GET)
É necessário saber o *id* da campanha  
```
http://localhost:9090/api/v1/campanhas/{id}
```
##### 4) Atualiza uma campanha (PUT)
É necessário saber o *id* da campanha
```
http://localhost:9090/api/v1/campanhas
```
##### 5) Deleta uma campanha (DELETE)
É necessário saber o *id* da campanha
```
http://localhost:9090/api/v1/campanhas/{id}
```

##### 6) Lista todos os times (GET)
```
http://localhost:9090/api/v1/times
```
##### 7) Associa um socio as campanhas e retorna a lista de campanhas (POST)
```
http://localhost:9090/api/v1/sociocampanhas
```

### api-socio

JSON do Socio:
```json
{
  "dataNascimento": "YYYY-MM-dd",
  "email": "teste4@teste.com",
  "idSocio": 0,
  "nomeSocio": "Nome do sócio",
  "timeId": 3
}
    
```

OBS: Neste caso o e-mail é utilizado como chave, ou seja, identifica unicamente o sócio.

##### 1) Cria um socio e associa as campanhas
É necessário saber o *id* do time
```
http://localhost:8080/api/v1/socios
```

## Executando o exemplo

Após clonar o diretório microsserviços, acesse a pasta *eureka-server* e execute o comando:
 ```
mvn spring-boot:run
```
Isso subirá o servidor Eureka. este servidor executa na porta: 8761. O endereço:
- http://localhost:8761

Acesse agora a pasta *api-campanha* e execute o comando 
```
mvn spring-boot:run
```

Este serviço executa executa na porta 9090. Para visualizar a página do swagger e realizar testes, acesse o endereço:
- http://localhost:9090/swagger-ui.html

E por fim, na pasta *api-socio*:
```
mvn spring-boot:run
```
Esta api executa na porta 8080. Para visualizar a página do swagger e realizar testes, acesse o endereço:
- http://localhost:8080/swagger-ui.html


A orden de execução deve ser exatamente essa. Ou seja, o servidor Eureka deve subir primeiro.
A *api-socio* acessa serviços da *api-campanha* via *FEIGN*. Caso o serviço da *api-campanha* não esteja executando, o pattern *CircuitBreaker*, implementado pelo Hystrix, percebe e chama um método de *CallBack*. Nesta implementação em específico, este método esta retornando uma lista com uma campanha *fake*. Porém, uma alternativa seria utilizar um cache (REDIS, por exemplo), para retornar valores que estão em cache enquanto a api de campanhas está offline.

Também foi utilizado o *EntityListener*. Quando uma campanha qualquer sofre uma atualização, o *EntityListener* percebe e aciona o método anotado com PostUpdate. Neste caso específico, apenas uma mensagem é apresentada no console. Porém, o correto seria implementar um mecanismo de WebHook para notificar outras dependencias.

## Melhorias

##### Implementação de cache com Redis;

##### Implamentação de mecanismo de WebHook;

##### Utilização fo Ribbon para load balance;

##### Melhoria nos testes;

##### Utilização do MongoDB (neste caso bastaria utilizar MongoDBRepository) 

##### Elastisearch com implementação da pilha completa ELK (ElastichSearch, LogStash, Kibana)

##### Utilização de Docker
