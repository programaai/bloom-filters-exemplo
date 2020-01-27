# Login no sistema

## API

* `POST /api/v1/users`
    - Status `200`
    - Adiciona um username
    
* `GET /api/v1/users`
    - Status `200`
    - Retorna todos os usernames
    
* `GET /api/v1/users/{username}`
    - Status `200` quando o usuário existe
    - Status `404` quando o usuário não existe