# desafio-topaz

## Como rodar o projeto.

### Manualmente:
- Comando para executar o backend: Executar dentro da pasta encurtador-url
```
    mvn clean package
    cp target/encurtador-url.war $WILDFLY_HOME/standalone/deployments/
    $WILDFLY_HOME/bin/standalone.sh
```
  
- Comando para executar o frontend: Executar dentro da pasta encurtador-url-frontend
```
    npm install
    ng serve
  ```
Abra no browser: http://localhost:4200


### Com Docker:
- Comando para executar o backend: Executar dentro da pasta encurtador-url
```
        docker build -t encurtador-url .
        docker run -d -p 8080:8080 encurtador-url
```

- Comando para executar o frontend: Executar dentro da pasta encurtador-url-frontend
```
    npm install
    ng serve
```
Abra no browser: http://localhost:4200

## Suas escolhas de design.

 1. O motor de geração de códigos usa uma sequence controlada pelo banco de dados e é executada de forma sincrona evitando colisão, em grande volume de requisições isso seria um ponto negativo, pois, geraria lentidão e possiveis timeout.
 2. Eu separei a aplicação backend em camadas, api -> service .> repository, e as configurações necessária que precisam rodar quando o app iniciar foram separadas em classe e pacotes de configuração, isolando elas do fluxo de negócio.
 3. Persistência via JPA/Hibernate, com datasource declarado pela própria aplicação para abistrair as configurações e dependencias de banco de dados.
 4. Frontend em angular separando a interface do usuário e a lógica do servidor, economizando trafego de rede e processamento do servidor.

## O que faria diferente com mais tempo:
- Eu adicionaria teste integrado com wiremock e teste de performance utilizando jmeter

