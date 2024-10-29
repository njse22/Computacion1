const User = require("./../model/user")
const dbConnection = require("./../connection/dbconnection")

// definición de un objeto de javascript 
// compuesto de funciones
const UserController = {
    // GET -> Listar
    list: (req, res) => {
	const db = dbConnection.readDB()
	res.status(200).send(db.users)
    }, 
    get: (req, res) => {
	try{
	    const db = dbConnection.readDB()
	    const id = parseInt(req.params.id)
	    const userGet = db.users.find(user => user.id === id)
	    if(!userGet){
		res.status(404).json({error: "User Not Found"})
	    }
	    else{
		res.status(200).send(userGet)
	    }
	}
	catch(err){
	    res.status(500).json({error: err.message})
	}
    },
    // POST -> Modificar
    create: (req, res) => {
	try{
	    const db =dbConnection.readDB()
	    const userData = req.body
	    const newUser = new User(userData.name, userData.email)
	    newUser.id = db.users.length ? db.users[db.users.length - 1].id + 1 : 1; 

	    db.users.push(newUser)
	    dbConnection.writeDB(db)
	    
	    res.status(201).send("New User Created")
	}
	catch(err){
	    res.status(500).json({ error: err.message })
	}
    }, 
    update: (req, res) => {
	const db = dbConnection.readDB(); 
	const userData = req.body
	const id = parseInt(req.params.id)

	// Validaciones de los tipos de datos
	//console.log(id == 1)
	//console.log(id == '1')
	//console.log(id === 1)
	//console.log(id === '1')

	const index = db.users.findIndex(user => user.id === id)
	db.users[index] = {...userData, id}
	dbConnection.writeDB(db)
	res.status(204).send()
    },

    delete: (req, res) => {

	const db = dbConnection.readDB(); 
	const id = parseInt(req.params.id)

	const index = db.users.findIndex(user => user.id === id)
	db.users.splice(index, 1)
	dbConnection.writeDB(db)

	res.status(204).send()
    }
}

function echo(){
    console.log("Hola")
}

// exportar los elementos del modulo
module.exports = UserController
