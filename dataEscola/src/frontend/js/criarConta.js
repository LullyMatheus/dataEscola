//mascara do cpf
const cpfInput = document.getElementById("cpf");

cpfInput.addEventListener("input", function () {
    let cpf = cpfInput.value.replace(/\D/g, "");

    if (cpf.length > 11) cpf = cpf.slice(0, 11);

    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

    cpfInput.value = cpf;
});

document.addEventListener('DOMContentLoaded', function() {
    
    const formulario = document.getElementById('formulario');

    formulario.addEventListener('submit', function(event) {
        // Impede que a página recarregue
        event.preventDefault();

        const nome = document.getElementById('nome').value;
        const email = document.getElementById('email').value;
        const senha = document.getElementById('senha').value;
        const cpf = document.getElementById('cpf').value;

        // verifica o valor marcado em radio button
        const tipoSelecionado = document.querySelector('input[name="tipo"]:checked');

        // Monta o objeto JSON
        const dadosUsuario = {
            nome: nome,
            cpf: cpf,
            email: email,
            senha: senha,
            tipoUsuario: tipoSelecionado.value // IMPORTANTE: Deve bater com o nome no DTO Java
        };

        // Envia para a API
        fetch("http://localhost:8888/api/usuario", {
            method: "POST",
            headers: { 
                "Content-Type": "application/json" 
            },
            body: JSON.stringify(dadosUsuario)
        })
        .then(res => {
            // lançamos exceção caso o backend de erro
            if (!res.ok) {
                return res.text().then(text => { throw new Error(text) });
            }
            return res.json();
        })
        .then(data => {
            console.log("Sucesso:", data);
            alert("Usuário cadastrado com sucesso!");
            window.location.href = "index.html"; // Redireciona para o login
        })
        .catch(err => {
            console.error("Erro:", err);
            alert("Erro ao cadastrar: " + err.message);
        });
    });

});