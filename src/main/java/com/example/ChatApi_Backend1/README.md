Chat API Backend

This project is a backend API for a simple chat application. It provides endpoints for user registration, sending chat messages, and fetching chat history between users.

Table of Contents

Installation

Usage

API Endpoints

Authentication

Sample Requests and Responses

Additional Information



Installation:

Clone the repository to your local machine.
Make sure you have Java and Maven installed.
Navigate to the project root directory.

Run the following command to build the project:

mvn clean install

Start the application using the following command:

mvn spring-boot:run

Usage:
After the application is running, you can interact with the API using tools like Postman. Make sure to authenticate using JWT tokens for protected endpoints.

API Endpoints:

1) POST /register
+ Register a new user.

* Method: POST
* URL: http://localhost:8081/api/register
* Request Body:

  {
  "username": "Athulya",
  "password": "athulya"
  }
* Response:
"User registered successfully."

2)POST /login

* Authenticate and obtain a JWT token.

* Method: POST
* URL: http://localhost:8081/api/auth/login
* Request Body:
  {
  "username": "Athulya",
  "password": "athulya"
  }
* Response: JWT token as a string.

3)POST /send-message

 * Send a chat message.

* Method: POST
* URL: http://localhost:8081/api/send-message
* Request Header: Authorization: JWT-Token
* Request Body:
  {
  "sender": "Athulya",
  "receiver": "Anjana",
  "messageContent": "Hello, how are you?"
  }
* Response: "Message sent successfully."

4)GET /chat-history
 * Fetch chat history between two users.

* Method: GET
* URL: http://localhost:8081/api/chat-history?sender=Athulya&reciver=Anjana
* Request Header: Authorization: JWT-Token
* Response:
  [
  {
  "id": 1,
  "sender": "Athulya",
  "receiver": "Anjana",
  "timestamp": "2023-07-26T15:35:00.443304",
  "messageContent": "Hello, how are you?"
  },
  {
  "id": 2,
  "sender": "Athulya",
  "receiver": "Anjana",
  "timestamp": "2023-07-26T15:37:56.144287",
  "messageContent": "I'm good, thank you!"
  }
  ]


Authentication

The API uses JWT (JSON Web Tokens) for authentication. Obtain a token by sending a login request with valid credentials. Include the token in the Authorization header for protected endpoints (e.g., /send-message, /chat-history).

Sample Requests and Responses:

Request:
POST /register

{
"username": "Athulya",
"password": "athulya"
}

Response:

User registered successfully.

Request:

POST /login

{
"username": "Athulya",
"password": "athulya"
}

Response:

your JWT-Token

Request:

POST /send-message

{
"sender": "Athulya",
"receiver": "Anjana",
"messageContent": "Hello, how are you?"
}

Response:

Message sent successfully.

Request:

GET /chat-history?sender=Athulya&receiver=Anjana

Response:

[
{
"id": 1,
"sender": "Athulya",
"receiver": "Anjana",
"timestamp": "2023-07-26T15:35:00.443304",
"messageContent": "Hello, how are you?"
},
{
"id": 2,
"sender": "Athulya",
"receiver": "Anjana",
"timestamp": "2023-07-26T15:37:56.144287",
"messageContent": "I'm good, thank you!"
}
]

Additional Information


* This API is a part of the Chat Application project.
* Known issues: None at the moment.
* Future enhancements: Add user profile management endpoints.
