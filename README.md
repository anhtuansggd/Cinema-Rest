# Cinema-Rest
A simple ticket booking system for a movie theater with a 9x9 seating arrangement. Providing
functionalities such as viewing available seats, purchasing a seat, returning a purchased seat, and
viewing statistics of the room, all of which are exposed as RESTful APIs.

#### API Documentation

##### Endpoints

<details>
 <summary><code>POST</code> <code><b>/purchase</b></code></summary>
 
##### Parameters

> | name      |  type     | data type  | description       |
> |-----------|-----------|------------|-------------------|
> | row       |  required | int        | from 1 to 9       |
> | column    |  required | int        | from 1 to 9       |
##### Request Body

```json
{
    "row": int,
    "column": int
}
```

##### URL

http://localhost:28852/purchase

##### Responses

> | Code      |  Description     | 
> |-----------|------------------|
> | 200       |  OK              |


```json
{
    "token": uuid
    "ticket": {
     "row": int
     "column": int
     "price": int
  }
}
```

</details>

<details>
 <summary><code>POST</code> <code><b>/return</b></code></summary>

##### Request Body

```json
{
    "token": "uuid"
}
```

##### URL

http://localhost:28852/return

##### Responses
> | Code      |  Description     | 
> |-----------|------------------|
> | 200       |  OK              |
> | 400       |  Bad Request     |

When status 200:
```json
{
  "returned_ticket": {
    "row": int,
    "column": int,
    "price": int
  }
}
```
When status 400:
```json
{
    "error": "Wrong token!"
}
```

</details>

<details>
 <summary><code>GET</code> <code><b>/stats?password=super_secret</b></code></summary>

##### URL

http://localhost:28852/stat

##### Params
> | Key       |  Value           | 
> |-----------|------------------|
> | password  |  super_secret    |
> 
##### Responses
> | Code      |  Description     | 
> |-----------|------------------|
> | 200       |  OK              |
> | 401       |  Unauthorized     |

When status 200:
```json
{
  "current_income": int,
  "number_of_available_seats": int,
  "number_of_purchased_tickets": int
}
```
When status 401:
```json
{
    "error": "The password is wrong!"
}
```
</details>
