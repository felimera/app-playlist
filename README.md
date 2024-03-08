# app-playlist

## Description

Development of a microservice for music playlists with the ability to create a record and query it by name. This project was developed with the Spring framework with:

* The Java programming language version 17. 
* Maven.
* Spring boot version 3.2.3
* H2 in-memory database 
* Spring JPA for data persistence. 
* Spring Security for authentication and access control framework. 
* Validation for validation of input data.

## Settings
1. You must clone the repository https://github.com/felimera/app-playlist.git
2. You can use the IntelliJ IDEA to open the project.
3. After you load all the project dependencies you can run the project.

## How to use

In order to use the different app Endpoints, you must first obtain the Token that will allow you access to the system. This is achieved in the following way:

### User creation

1. Open your preferred REST Client (Postman).
2. Change the request option to a _Post_.
3. Add the url to the patch http://localhost:81/signup , then go to the body option in raw segment and add the following body:

   ` {
"name":"User test",
"email":"user-test@test.com",
"password":"password"
}`

4. In the body you can modify the name, email and password data to your preference.
5. Then execute the request.
6. Next, you should see the response of the request with a **201 Created** with the body:
   
    `{
   "code": "201",
   "message": "User created successfully."
   }`

### Get the Token

After creating the user you can do the following:
1. Open another tab of the _REST Client_ (_PostMan_) and enter this url http://localhost:81/login
2. Change the request option to a Post.
3. Then go to the body option in raw segment and add the following body:

   `{
"email":"andres@andres.com",
"password":"password"
}`

4. In the body you can modify the name, email and password data to your preference.
5. Then execute the request.
6. Next, you should see the response of the request with a **200 Ok** with the body:

   `{
"jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZXNAYW5kcmVzLmNvbSIsImlhdCI6MTcwOTg1NzU3NCwiZXhwIjoxNzEyNDQ5NTc0fQ.CtJLViMFyxhvnTIr1ZDTzqWa-NdV542Vlkp8Cx2HJS0"
}`

### Playlist creation

With the Token generated we can now access the other requests without any problem. In order to create a playlist you must do:

1. Open a tab of the REST Client (PostMan) and the patch enter this url http://localhost:81/lists
2. Change the request option to Post.
3. Now in the authentication option choose the Bearer Token authentication type.
4. Then in the displayed field enter the characters of the previous generated token result (only the characters, do not enter the body attribute “jwtToken”).
5. Finally, go to the Body option and in the raw option enter this body:

   `{
"nombre": "Merengue",
"descripcion": "Merengue clasica",
"canciones": [
{
"titulo": "A Dormir Juntitos",
"artista": "Eddy Herrera",
"album": "Pegame Tu Vicio",
"anno": "2001",
"genero": "Música tropical, Bachata, Regional Colombian"
},
{
"titulo": "Guayando",
"artista": "Fulanito",
"album": "Guayando (Chan Remix Chan's Remix)",
"anno": "2003",
"genero": "Merengue, Reggaeton"
}
]
}`

6. Like the previous options you can modify the body data.
7. Send the request.
8. A body must arrive in response to the request:

   `{
"nombre": "Merengue",
"descripcion": "Merengue clasica",
"canciones": [
{
"titulo": "A Dormir Juntitos",
"artista": "Eddy Herrera",
"album": "Pegame Tu Vicio",
"anno": "2001",
"genero": "Música tropical, Bachata, Regional Colombian"
},
{
"titulo": "Guayando",
"artista": "Fulanito",
"album": "Guayando (Chan Remix Chan's Remix)",
"anno": "2003",
"genero": "Merengue, Reggaeton"
}
]
}`

9. With status **201 Created**.

### Check playlists.
To consult the playlists we have two options: consult by list name and consult all existing records.

To consult all the records you must do:

1. Open a tab of the REST Client (PostMan) and the patch enter this url http://localhost:81/lists
2. Change the request option to Get.
3. Now in the authentication option choose the Bearer Token authentication type.
4. Then in the displayed field enter the characters of the previous generated token result (only the characters, do not enter the body attribute “jwtToken”).
5. Send the request.
6. This body must arrive as a response to the request:

   `[
{
"nombre": "Salsa",
"descripcion": "Salsa clasica",
"canciones": [
{
"titulo": "Lluvia",
"artista": "Eddie Santiago",
"album": "Sigo atrevido",
"anno": "1994",
"genero": "Salsa"
},
{
"titulo": "casi te envidio",
"artista": "Andy Montañez",
"album": "Desconocido",
"anno": "1999",
"genero": "Salsa"
},
{
"titulo": "aquel viejo motel",
"artista": "David Pabón",
"album": "Desconocido",
"anno": "1989",
"genero": "Salsa"
}
]
},
{
"nombre": "Pop rock",
"descripcion": "Pop rock",
"canciones": [
{
"titulo": "DARK ARIA <LV2>",
"artista": "Hiroyuki Sawano",
"album": "LEveL",
"anno": "2024",
"genero": "Pop"
},
{
"titulo": "Youngblood",
"artista": "5 Seconds of Summer",
"album": "Desconocido",
"anno": "2018",
"genero": "Pop Rock"
}
]
},
{
"nombre": "Merengue",
"descripcion": "Merengue clasica",
"canciones": [
{
"titulo": "A Dormir Juntitos",
"artista": "Eddy Herrera",
"album": "Pegame Tu Vicio",
"anno": "2001",
"genero": "Música tropical, Bachata, Regional Colombian"
},
{
"titulo": "Guayando",
"artista": "Fulanito",
"album": "Guayando (Chan Remix Chan's Remix)",
"anno": "2003",
"genero": "Merengue, Reggaeton"
}
]
}
]`

7. With status **200 Ok**.


Now, to check by name you must do the following:

1. Open a tab of the REST Client (PostMan) and the patch enter this url http://localhost:81/lists/Merengue (the word Merengue is the option to consult, if you wish you can change this option to your preference.)
2. Change the request option to Get.
3. Now in the authentication option choose the Bearer Token authentication type.
4. Then in the displayed field enter the characters of the previous generated token result (only the characters, do not enter the body attribute “jwtToken”).
5. Send the request.
6. This body must arrive as a response to the request:

   `{
"nombre": "Merengue",
"descripcion": "Merengue clasica",
"canciones": [
{
"titulo": "A Dormir Juntitos",
"artista": "Eddy Herrera",
"album": "Pegame Tu Vicio",
"anno": "2001",
"genero": "Música tropical, Bachata, Regional Colombian"
},
{
"titulo": "Guayando",
"artista": "Fulanito",
"album": "Guayando (Chan Remix Chan's Remix)",
"anno": "2003",
"genero": "Merengue, Reggaeton"
}
]
}`

7. With status 200 Ok.

### Deleting a playlist

To delete the registry you must follow the following steps:

1. Open a tab of the REST Client (PostMan) and the patch enter this url http://localhost:81/lists/Merengue (the word Merengue is the option to consult, if you wish you can change this option to your preference.)
2. Change the request option to Delete.
3. Now in the authentication option choose the Bearer Token authentication type.
4. Then in the displayed field enter the characters of the previous generated token result (only the characters, do not enter the body attribute “jwtToken”).
5. Send the request.
6. As a response to the request, the response status is **204 No Content** without response body.