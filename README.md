# QA network

A question-answer network api where users can ask each other
questions 
and answer them.

<h1>Project is no longer live due to Heroku terminating its free tier accounts!</h1>

## 

Project is built with Spring Boot using Hibernate/Spring Data JPA and Spring Security.

## API Reference

#### User authentication:

##### Register user

```http
  POST hhttps://qanetwork.herokuapp.com/api/auth/registration

```



| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. User email|
| `username` | `string` | **Required**. User unique username|
| `password` | `string` | **Required**. User password|


Sample body:
```http
  {
	"email" : "tony@gmail.com",
	"username" : "user",
	"password" : "user"

}


```

#### Login user:

```http
  POST hhttps://qanetwork.herokuapp.com/api/auth/login

```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. User email|
| `username` | `string` | **Required**. User username|
| `password` | `string` | **Required**. User password|


Sample body:
```http
  {
	"email" : "tony@gmail.com",
	"username" : "user",
	"password" : "user"

}

```

### Questions:

#### Post question to a given user:
Only logged in users can ask eachother questions. Users can also choose to remain Anonymous.



```http
  POST hhttps://qanetwork.herokuapp.com/api/user/user

```

| Path   | Description                |
| :-------- | :------------------------- |
| `/[user]` | where 'user' is a username|

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `questionBody` | `string` | **Required**. Question body|
| `isAnonymous` | `int` | **Required**. [1 or 0] Hide or display username|


Sample body:
```http
  {
	"questionBody" : "Will Charles LeClerc win in Belgium?",
	"isAnonymous" : 1
}

```

#### Post answer to a given question:
Once logged in, users can answer their questions.

```http
  POST https://qanetwork.herokuapp.com/api/user/user/2

```

| Path   | Description                |
| :-------- | :------------------------- |
| `/[id]` | where 'id' is a question id|

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| none | `string` | **Required**. Answer body|


Sample body:
```
  I hope so!

```

#### Get questions for given user:
Everyone can see each user's questions

```http
  GET hhttps://qanetwork.herokuapp.com/api/user/user

```

| Path   | Description                |
| :-------- | :------------------------- |
| `/user` | where 'user' is a username|



Sample response:
```http
  [
    {
        "id": 2,
        "questionBody": "Will Charles LeClerc win in Belgium?",
        "sentBy": "Anonymous",
        "isAnonymous": 1,
        "answerBody": "I hope so!"
    }
]

```

#### Delete question:
Once logged in, users can delete their own questions

```http
  DELETE https://qanetwork.herokuapp.com/api/user/user/2

```

| Path   | Description                |
| :-------- | :------------------------- |
| `/[id]` | where 'id' is a question id|




