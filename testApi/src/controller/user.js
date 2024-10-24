// definición de un objeto de javascript 
// compuesto de funciones
const User = {
    // GET -> Listar
    list: (req, res) => {
	res.status(200).send("Lista de usuarios")
    }, 
    get: (req, res) => {
	res.status(200).send("Get unique element")
	console.log(req.params)
    },
    // POST -> Modificar
    modify: (req, res) => {
	res.status(201).send("modify user")
	console.log(req.body)  
    }
}

function echo(){
    console.log("Hola")
}

// exportar los elementos del modulo
module.exports = User
