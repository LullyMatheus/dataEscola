// MÁSCARA DE CPF (fora do submit)
const cpfInput = document.getElementById("cpf");

cpfInput.addEventListener("input", function () {
    let cpf = cpfInput.value.replace(/\D/g, "");

    if (cpf.length > 11) cpf = cpf.slice(0, 11);

    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

    cpfInput.value = cpf;
});

// SUBMIT DO FORMULÁRIO
document.getElementById("formulario").addEventListener("submit", function (e) {
    e.preventDefault();

    const cpf = document.getElementById("cpf").value;
    const senha = document.getElementById("senha").value;

    fetch("http://localhost:8888/api/usuario/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            cpf: cpf,
            senha: senha
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("CPF ou senha inválidos");
        }
        return response.json();
    })
    .then(data => {
        console.log("Usuário autenticado:", data);
        alert("Login realizado com sucesso!");
        window.location.href = "home.html";
    })
    .catch(error => {
        alert(error.message);
    });
});