# 🎯 API de Gestão de Eventos

API REST construída com **Java 21** e **Spring Boot**, permitindo o cadastro de usuários, criação de eventos e
inscrições de participantes. Ideal para organizar e gerenciar eventos de forma simples e segura.

## 📌 Funcionalidades

- 👤 **Cadastro de usuários**: permite registrar organizadores e participantes com validações.
- 🗓️ **Criação e listagem de eventos**: organizadores podem cadastrar eventos com data e local.
- ✅ **Inscrição de participantes**: participantes podem se inscrever em eventos abertos.
- 🔍 **Consulta de inscrições**: permite listar quem está inscrito em cada evento.
- 📎 **Validações com mensagens personalizadas**: regras de negócio aplicadas com mensagens amigáveis.
- 🌐 **Perfis de ambiente**: configuração separada para desenvolvimento e produção.

## 🚀 Como executar o projeto

### ✅ Pré-requisitos
- Java 21
- Maven
- PostgreSQL
- Docker (opcional)

---

### 📦 Executar com Docker

```bash
cd api-de-eventos
docker-compose up -d
java -jar ./target/Api-de-eventos-0.0.1-SNAPSHOT.jar
````
### 🔧 Sem Docker
1. Certifique-se de que o PostgreSQL está rodando localmente.
2. Crie um banco com o nome eventos_db
3. Ajuste as credenciais no application.yml
4. Compile o projeto:
````
./mvnw clean package -DskipTests
java -jar ./target/Api-de-eventos-0.0.1-SNAPSHOT.jar
````

### 🧪 Exemplos de requisição
### Criar usuário
`POST /users`
```json
{
  "id": "0fe2d82b-e295-4f12-ac0a-907982dad80c",
  "name": "teste",
  "email": "test@email.com",
  "role": "Organizer",
  "createdAt": "2025-06-27T18:45:46.414209"
}
```
`POST /events`
```json
{
  "title": "Workshop Spring Boot",
  "description": "Curso Java na prática",
  "eventDate": "2025-08-10T10:00:00",
  "local": "Sala 7",
  "organizerUserId": "769a143b-6a4c-44da-a739-50e61a012151"
}
```
### Registrar usuario no evento
`POST /event/{eventId}/registration` 
```http
http://localhost:8080/events/{eventId}/registration
Content-Type: application/json
````
No body da requisição
````json
{
  "userId": "029620b7-1a46-44df-b11f-df192b8d3176"
}
````