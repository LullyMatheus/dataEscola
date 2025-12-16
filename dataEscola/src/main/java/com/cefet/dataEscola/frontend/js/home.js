function salvarAluno() {
    fetch("http://localhost:8888/api/alunos", {
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
}

function salvarAtendimento() {
  const alunoId = document.getElementById("alunoAtendimento").value;

  if (!alunoId) {
    alert("Selecione um aluno");
    return;
  }

  const atendimento = {
    alunoId: alunoId,
    descricao: document.getElementById("descricaoAtendimento").value,
    data: document.getElementById("dataAtendimento").value,
    situacao: document.getElementById("situacaoAtendimento").value,
    responsavel: document.getElementById("responsavelAtendimento").value
  };

  fetch("http://localhost:8888/api/atendimento", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(atendimento)
  })
  .then(res => {
    if (!res.ok) throw new Error("Erro ao salvar atendimento");
    alert("Atendimento cadastrado com sucesso!");
  })
  .catch(err => alert(err.message));
}



function buscarAluno() {
    const nome = document.getElementById("pesquisaNome").value.trim();

    if (!nome) {
        alert("Digite um nome");
        return;
    }

    fetch(`http://localhost:8888/api/alunos/nome/${nome}`)
        .then(res => res.json())
        .then(alunos => {
            preencherTabelaAlunos(alunos);

            if (alunos.length > 0) {
                const alunoId = alunos[0].id;
                carregarAtendimentos(alunoId);
                carregarAtividades(alunoId);
            }
        });
}

function carregarAtendimentos(alunoId) {
    fetch(`http://localhost:8888/api/atendimento/por-aluno/${alunoId}`)
        .then(res => res.json())
        .then(atendimentos => preencherTabelaAtendimentos(atendimentos))
        .catch(() => limparAtendimentos());
}


function carregarAtividades(alunoId) {
    fetch(`http://localhost:8888/api/atividades/por-aluno/${alunoId}`)
        .then(res => res.json())
        .then(atividades => preencherTabelaAtividades(atividades))
        .catch(() => limparAtividades());
}


function carregarAlunos() {
    fetch("http://localhost:8888/api/alunos")
        .then(res => res.json())
        .then(alunos => {
            preencherTabelaAlunos(alunos);
            limparTabelasRelacionadas(); 
        })
        .catch(err => alert("Erro ao carregar alunos"));
}

function carregarAlunosSelect() {
  fetch("http://localhost:8888/api/alunos")
    .then(res => res.json())
    .then(alunos => {
      const select = document.getElementById("alunoAtendimento");
      select.innerHTML = '<option value="">Selecione o aluno</option>';

      alunos.forEach(aluno => {
        const option = document.createElement("option");
        option.value = aluno.id;       
        option.textContent = aluno.nome; 
        select.appendChild(option);
      });
    });
}


function limparTabelasRelacionadas() {
    document.getElementById("tabela-atendimentos").innerHTML = "";
    document.getElementById("tabela-atividades").innerHTML = "";
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
                <td>${aluno.contatos}</td>
            </tr>
        `;
    });
}

function preencherTabelaAtendimentos(atendimentos) {
    const tabela = document.getElementById("tabela-atendimentos");
    tabela.innerHTML = "";

    atendimentos.forEach(atendimento => {
        tabela.innerHTML += `
            <tr>
                <td>${atendimento.alunoNome}</td>
                <td>${atendimento.descricao}</td>
                <td>${atendimento.dataAtendimento}</td>
                <td>${atendimento.situacao}</td>
                <td>${atendimento.usuarioNome}</td>
                
            </tr>
        `;
    });
}

function preencherTabelaAtividades(atividades) {
    const tabela = document.getElementById("tabela-atividades");
    tabela.innerHTML = "";

    atividades.forEach(atividade => {
        tabela.innerHTML += `
            <tr>
                <td>${atividade.alunoNome}</td>
                <td>${atividade.descricao}</td>
                <td>${atividade.observacao}</td>
                <td>${atividade.status}</td>
            </tr>
        `;
    });
}