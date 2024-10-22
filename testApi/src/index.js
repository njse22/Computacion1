const express = require("express")
const app = express()
const port = 5000

app.get("/", (res, req) => {

    console.log("Hola")
    console.log(req)
})


app.listen(port)
