# 🌐 BFF Agendador de Tarefas API
<img width="1005" height="597" alt="image" src="https://github.com/user-attachments/assets/32b8cb88-5430-4670-a307-20972d0f13c5" />


O **BFF (Backend for Frontend)** é a camada que centraliza as chamadas para as três APIs principais do sistema:  

- 👤 **Usuário API** → gerenciamento de usuários e permissões  
- 📝 **Agendador de Tarefas API** → criação e gerenciamento de tarefas  
- 📢 **Notificação API** → envio de notificações por e-mail  

Através desse BFF, o cliente (frontend ou mobile) acessa todos os recursos do sistema de forma unificada.  

---

## 🛠 Tecnologias Utilizadas

- ☕ **Java 17**  
- 🌱 **Spring Boot**  
- 📦 **Gradle**  
- 📄 **Swagger (OpenAPI)**  
- 🐳 **Docker / Docker Compose**
- 🌐 **OpenFeigh**
- ⏰ **Cron Service**

---

## 🧭 Funcionalidades Principais

- Centraliza as chamadas às três APIs do sistema  
- Fornece endpoints simplificados para o cliente final  
- Executa o **cron job** que dispara notificações a cada 30 segundos, chamando a Notificação API  
- Documenta endpoints via **Swagger**  

---

## 🚀 Uso com Docker Compose

Esse projeto foi pensado para rodar em conjunto com as três APIs e bancos de dados via **Docker Compose**.  

### Arquivo `docker-compose.yml`

```yaml
version: "3.8"

services:
  agendador-tarefas:
    build: ./agendador-tarefas-api
    container_name: agendador-tarefas-api
    ports:
      - "8081:8081"
    environment:
      SPRING_DATA_MONGODB_URI: "mongodb://mongo:27017/db_agendador"
    depends_on:
      - mongo

  bff-agendador-tarefas:
    build: ./bff-agendador-tarefas-api
    container_name: bff-agendador-tarefas-api
    ports:
      - "8083:8083"
    environment:
      USUARIO_URL: "http://usuario:8080/usuario"
      AGENDADOR_TAREFAS_URL: "http://agendador-tarefas:8081/tarefas"
      NOTIFICACAO_URL: "http://notificacao:8082/email"
    depends_on:
      - usuario
      - agendador-tarefas
      - notificacao

  usuario:
    build: ./usuario-api
    container_name: usuario-api
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: "jdbc:postgresql://postgres:5432/db_usuario"
      SPRING_DATASOURCE_USERNAME: "admin"
      SPRING_DATASOURCE_PASSWORD: "admin"
    depends_on:
      - postgres

  notificacao:
    build: ./notificacao
    container_name: notificacao-api
    ports:
      - "8082:8082"
    environment:
      EMAIL: ${EMAIL}
      SENHA: ${SENHA}

  postgres:
    image: postgres:latest
    container_name: postgres
    restart: no
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin
      POSTGRES_DB: db_usuario
    ports:
      - "5433:5432"

  mongo:
    image: mongo:latest
    container_name: mongo
    restart: no
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_DATABASE: db_agendador

networks:
  default:
    driver: bridge
```

---

## ▶️ Subindo a Stack

1. Clone os repositórios necessários:  
   ```bash
   git clone https://github.com/WilkerSampaio/bff-agendador-api.git
   git clone https://github.com/WilkerSampaio/agendador-tarefas-api.git
   git clone https://github.com/WilkerSampaio/usuario-api.git
   git clone https://github.com/WilkerSampaio/notificacao.git
   ```

2. No diretório raiz, onde está o `docker-compose.yml`, rode:  
   ```bash
   docker-compose up --build
   ```

3. A stack sobe com os serviços:  
   - Usuário API → [http://localhost:8080](http://localhost:8080)  
   - Agendador de Tarefas API → [http://localhost:8081](http://localhost:8081)  
   - Notificação API → [http://localhost:8082](http://localhost:8082)  
   - BFF → [http://localhost:8083](http://localhost:8083)  

---

## 🔗 Integração Geral

O fluxo funciona assim:  

1. O **BFF** recebe a requisição do cliente  
2. Ele redireciona a chamada para a API correspondente:  
   - **Usuário API** → cadastro/autenticação  
   - **Agendador de Tarefas API** → manipulação de tarefas  
   - **Notificação API** → disparo de e-mails  
3. O **cron job** (interno ao BFF) chama a Notificação API a cada 30 segundos para avisar sobre tarefas pendentes  

---
