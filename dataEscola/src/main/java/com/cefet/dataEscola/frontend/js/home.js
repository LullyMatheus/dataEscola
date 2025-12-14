function salvarAluno() {
    fetch("http://localhost:8888/api/aluno", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            nome: nomeAluno.value,
            matricula: matriculaAluno.value,
            email: emailAluno.value,
            contato: contatoAluno.value,
            dataNascimento: dataNascimentoAluno.value
        })
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao salvar aluno");
        return res.json();
    })
    .then(() => {
        carregarAlunos();
        bootstrap.Modal.getInstance(
            document.getElementById("modalAluno")
        ).hide();
    });

    function buscarAluno() {
        const nome = document.getElementById("pesquisaNome").value.trim();
                document.getElementById("btnBuscar")
            .addEventListener("click", buscarAluno);


        if (nome === "") {
            alert("Digite um nome para buscar.");
            return;
        }

        fetch(`http://localhost:8888/api/aluno/nome/${nome}`)
            .then(res => {
                if (!res.ok) {
                    throw new Error("Erro ao buscar alunos");
                }
                return res.json();
            })
            .then(alunos => preencherTabelaAlunos(alunos))
            .catch(err => alert(err.message));
    }

    function carregarAlunos() {
    fetch("http://localhost:8888/api/aluno")
        .then(res => res.json())
        .then(alunos => preencherTabelaAlunos(alunos));
    }

    function preencherTabelaAlunos(alunos) {
        const tabela = document.getElementById("tabela-alunos");
        tabela.innerHTML = "";

        alunos.forEach(aluno => {
            tabela.innerHTML += `
                <tr>
                    <td>${aluno.matricula}</td>
                    <td>${aluno.nome}</td>
                    <td>${aluno.dataNascimento}</td>
                    <td>${aluno.email}</td>
                    <td>${aluno.contato}</td>
                </tr>
            `;
        });
    }
}