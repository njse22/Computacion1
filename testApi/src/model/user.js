class User {

    constructor(name, email){

	if(!name || !email){
	    throw new Error("Name or Email are Empty")
	}
	this.name = name;
	this.email = email; 
    }
}

module.exports = User;
