# 🧁 Cupcake Store

Aplicação web de e-commerce de cupcakes desenvolvida com **Spring Boot**, com foco em regras de negócio, persistência de dados e controle de concorrência.

---

## 🚀 Funcionalidades

- 📋 Listagem de cupcakes
- 🛒 Carrinho de compras com sessão
- ➕ Adição e remoção de itens
- 💳 Finalização de pedidos
- 💾 Persistência com banco de dados
- 📉 Controle de estoque
- ⚠️ Validação de estoque insuficiente
- 🔒 Controle de concorrência (evita vendas simultâneas inconsistentes)

---

## 🛠️ Tecnologias utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **Thymeleaf**
- **Bootstrap**
- **H2 Database (dev)**
- **PostgreSQL (prod)**

---

## 🧠 Arquitetura

O projeto segue uma estrutura em camadas:

- `Controller` → entrada das requisições
- `Service` → regras de negócio
- `Repository` → acesso a dados
- `Model (Entities)` → representação do domínio

---

## 🔒 Regras de negócio implementadas

- Um pedido não pode ser finalizado sem itens
- Não é possível comprar mais do que o estoque disponível
- O estoque é atualizado automaticamente após a compra
- Uso de **transações (`@Transactional`)** para garantir consistência
- Uso de **lock no banco de dados** para evitar concorrência em compras simultâneas

---

## 💾 Banco de dados

O projeto suporta dois ambientes:

### 🔹 Desenvolvimento (H2)

Banco em memória para testes rápidos.

### 🔹 Produção (PostgreSQL)

Banco relacional real.

---

## ⚙️ Configuração

### 🔐 Variáveis de ambiente (recomendado)

Crie um arquivo `application-prod.properties` ou use variáveis:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
```

---

## ▶️ Como executar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/HeloisaFelizardo/CupcakeStore.git
```

---

### 2. Acessar o projeto

```bash
cd CupcakeStore/backend
```

---

### 3. Rodar a aplicação

#### 🔹 Usando Maven Wrapper

```bash
./mvnw spring-boot:run
```

#### 🔹 No Windows

```bash
mvnw.cmd spring-boot:run
```

---

### 4. Acessar no navegador

```
http://localhost:8080
```

---

## 🧪 Testando o fluxo

1. Adicione cupcakes ao carrinho
2. Acesse o carrinho
3. Finalize o pedido
4. Verifique os dados no banco de dados

---

## 📊 Modelagem

O projeto segue princípios básicos de **Domain-Driven Design (DDD)**, com foco em agregados e comportamento no domínio.

Diagrama de classes disponível em:

```
docs/uml/class-diagram.puml
```

---

## 🗺 Roadmap

- [x] Histórias de usuário
- [x] Levantamento de requisitos
- [x] Critérios de aceitação (BDD)
- [x] Diagrama de classes
- [x] Diagrama de casos de uso
- [x] Diagrama de fluxo do cliente
- [x] Diagrama de atividades
- [x] Diagrama de sequência
- [x] Mapa conceitual
- [x] Mapa navegacional
- [x] Wireframes (HTML + CSS)
- [x] Início da implementação com Java + Spring Boot
- [ ] Deploy inicial

---

## ✅ Etapas concluídas

- ✔ Histórias de usuário
- ✔ Testes de aceitação (BDD)
- ✔ Fluxo de trabalho
- ✔ Detalhamento de requisitos
- ✔ Casos de uso expandidos narrados
- ✔ Diagramas (classes, sequência, atividades, etc.)
- ✔ Modelagem completa do sistema

Essa etapa garantiu clareza das regras de negócio antes da implementação.

---

## 🎨 Wireframes

Os wireframes representam a estrutura inicial da interface da aplicação.

Todas as demais telas e diagramas estão na pasta `/docs`.

### Tela Inicial

![Home](docs/wireframes/telainicial.png)

### Login

![Login](docs/wireframes/telalogin.png)

### Carrinho

![Carrinho](docs/wireframes/telacarrinho.png)

### Painel Admin

![Painel Admin](docs/wireframes/telainicialadmin.png)

### Gerenciamento de Cupcakes

![Gerenciamento](docs/wireframes/telagerenciarcupcakes.png)

---

## 💡 Diferenciais do projeto

- Implementação de regras reais de negócio
- Controle de estoque com consistência de dados
- Tratamento de concorrência em nível de banco
- Arquitetura em camadas bem definida
- Suporte a múltiplos ambientes (H2 e PostgreSQL)

---

## 🚀 Próximos passos

- 🔐 Autenticação com Spring Security
- 📊 Dashboard administrativo
- 📦 Status de pedidos (PENDENTE, FINALIZADO)
- 🎨 Melhorias de UI/UX
- 🌐 Deploy em nuvem

---

## 🔒 Destaques técnicos

- Controle de concorrência com lock no banco
- Uso de transações para consistência de dados
- Separação de ambientes (dev/prod)
- Organização em camadas

---

## 📚 Abordagem utilizada

Os critérios de aceitação foram estruturados utilizando **BDD (Behavior-Driven Development)**:

> Dado – Quando – Então

Isso aproxima regras de negócio da implementação e facilita testes futuros.

---

## 🎯 Objetivo

Mais do que desenvolver funcionalidades, o foco deste projeto é praticar:

- Modelagem antes da implementação
- Organização de domínio
- Arquitetura limpa
- Boas práticas de backend

**Planejamento também é engenharia.**

---

## 📐 Arquitetura planejada

Estrutura em camadas:

```
Controller → Service → Repository → Banco de Dados
```

Pacotes:

- `controller` → entrada das requisições
- `service` → regras de negócio
- `repository` → acesso ao banco
- `model` → entidades do domínio
- `dto` → transferência de dados

---

## 👩‍💻 Autora

Desenvolvido por **Heloisa Felizardo** 🚀
