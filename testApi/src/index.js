// se importa la librería necesaria para la creación del API
const express = require("express")

// Modulos de la aplicación 
const user = require("./controller/usercontroller")
const db = require("./connection/dbconnection")

const app = express()

// Se usa para leer el "body" de la solicitud del cliente
app.use(express.json())

const port = 5000

////////////////////////////////////////////////////////////////////////
//                    Creación de los métodos/verbos GET              //
////////////////////////////////////////////////////////////////////////

// se define la ruta del método GET
// necesita ruta: /db y una función (req, res) => {}
// 
app.get("/db", (req, res) => {

    res.status(200).send("Here")
    
    // uso de un modulo propio para leer un archivo
    const data = db.readDB()

    console.log(data.name)
    console.log(data.age)
})

// uso de un modulo propio para gestionar usuarios
app.get("/users", user.list )

// :id -> string = 23, LLL, nombre, ABFBBC
app.get("/users/:id", user.get )

app.post("/users/", user.create)

app.put("/users/:id", user.update)

app.delete("/users/:id", user.delete)


app.get("/", (req, res) => {
    
    // 200 -> OK 
    res.status(200).send("Hola desde /")
    console.log("Hola")
    console.log(req)
})

app.post("/", (req, res) => {
    // 201 -> Ok -> Created
    res.status(201).send('Mensaje tipo POST')
})

app.put("/", (req, res) => {
    // 204 -> Ok -> No Content
    console.log(req.method)
    res.status(204).send("Mensaje desde el PUT")
})

app.patch("/", (req, res) => {
    console.log(req.method)
    res.status(204).send("Pathc")
})

app.delete("/", (req, res) => {
    console.log(req.method)
    res.status(204).send("Deleted ")
})

app.listen(port)
