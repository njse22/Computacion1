// librería para leer rutas 
// https://nodejs.org/en/learn/manipulating-files/nodejs-file-paths
const path = require("path")

// librería para manipular archivos 
const fs = require("fs")

// __dirname = ruta actual del modulo 
// ../.. = de la ruta actual del modulo dos carpetas atras
// data = carpeta data
// database.json = el archivo 
const dbPath = path.join(__dirname, "../..", 'data', 'database.json')


const DBConnection = {
    readDB: () => {
	const data = fs.readFileSync(dbPath, 'utf-8')
	return JSON.parse(data)
    }, 

    writeDB: (data) => {
	fs.writeFileSync(dbPath, JSON.stringify(data, null, 2))
    }
}

module.exports = DBConnection


