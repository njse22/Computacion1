const msgText = {}

window.onload = () => {

    const msgText = document.getElementById("messageText")
    console.log(msgText.innerText)
    //msgText.innerHTML = "<h1> Hola </h1>"
    const input = document.getElementById("inputText")
    console.log(input.innerHTML)

    const formElement = document.getElementById("formElement")

    console.log(formElement)

    const listElement = document.getElementById("formList")

    console.log(listElement)

    const elements = document.querySelectorAll("#formList li")
    console.log(elements)

    elements.forEach( (element, i) => {
	console.log(element, i)
	element.innerHTML = "<li> Nuevo </li>"
    })
}

function fun2(){
    console.log("Hola función 2")

}

const fun = () => {
    console.log(msgText.innerHTML)
}
