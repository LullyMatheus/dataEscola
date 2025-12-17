function salvarAluno() {
    fetch("http://localhost:8888/api/alunos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            nome: nomeAluno.value,
            matricula: matriculaAluno.value,
            email: emailAluno.value,
            contatos: contatoAluno.value,
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

function carregarSituacoesAtendimento() {
    fetch("http://localhost:8888/api/atendimento/situacoes")
        .then(res => res.json())
        .then(situacoes => {
            const select = document.getElementById("situacaoAtendimento");
            select.innerHTML = '<option value="">Selecione a situação</option>';

            situacoes.forEach(s => {
                select.innerHTML += `<option value="${s}">${s}</option>`;
            });
        });
}

document
  .getElementById("modalAtendimento")
  .addEventListener("show.bs.modal", () => {
      carregarSituacoesAtendimento();
      carregarAlunosSelect();
      carregarUsuariosSelect();
  });

function carregarUsuariosSelect() {
    fetch("http://localhost:8888/api/usuario")
        .then(res => res.json())
        .then(usuarios => {
            const select = document.getElementById("responsavelAtendimento");
            select.innerHTML = '<option value="">Selecione o responsável</option>';

            usuarios.forEach(usuario => {
                select.innerHTML += `
                    <option value="${usuario.id}">
                        ${usuario.nome}
                    </option>
                `;
            });
        });
}


function carregarAlunosSelect() {
    fetch("http://localhost:8888/api/alunos")
        .then(res => res.json())
        .then(alunos => {
            const select = document.getElementById("alunoAtendimento");
            select.innerHTML = '<option value="">Selecione o aluno</option>';

            alunos.forEach(aluno => {
                select.innerHTML += `
                    <option value="${aluno.id}">
                        ${aluno.nome}
                    </option>
                `;
            });
        });
}
function carregarAlunosAtividade() {
    fetch("http://localhost:8888/api/alunos")
        .then(res => res.json())
        .then(alunos => {
            const select = document.getElementById("alunoAtividade");

            select.innerHTML = '<option value="">Selecione o aluno</option>';

            alunos.forEach(aluno => {
                const option = document.createElement("option");
                option.value = aluno.id;
                option.textContent = aluno.nome;
                select.appendChild(option);
            });
        })
        .catch(err => console.error("Erro ao carregar alunos", err));
}

function carregarStatusAtividade() {
    fetch("http://localhost:8888/api/atividades/status")
        .then(res => res.json())
        .then(statusList => {
            const select = document.getElementById("statusAtividade");

            select.innerHTML = '<option value="">Selecione o status</option>';

            statusList.forEach(status => {
                const option = document.createElement("option");
                option.value = status;
                option.textContent = status;
                select.appendChild(option);
            });
        })
        .catch(err => console.error("Erro ao carregar status", err));
}


function salvarAtendimento() {
    if (!alunoAtendimento.value) {
        alert("Selecione um aluno");
        return;
    }

    if (!responsavelAtendimento.value) {
        alert("Selecione um responsável");
        return;
    }

    if (!dataAtendimento.value) {
        alert("Informe a data do atendimento");
        return;
    }

    const atendimento = {
        descricao: descricaoAtendimento.value,
        dataAtendimento: dataAtendimento.value,
        situacao: situacaoAtendimento.value,
        idAluno: Number(alunoAtendimento.value),
        idUsuario: Number(responsavelAtendimento.value),
        dataLembrete: null
    };

    console.log("Enviando:", atendimento);

    fetch("http://localhost:8888/api/atendimento", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(atendimento)
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao salvar atendimento");
        return res.json();
    })
    .then(() => {
        alert("Atendimento cadastrado com sucesso!");
        bootstrap.Modal.getInstance(
            document.getElementById("modalAtendimento")
        ).hide();
    })
    .catch(err => alert(err.message));
}

document
  .getElementById("modalAtividadeAcademica")
  .addEventListener("show.bs.modal", () => {
      carregarAlunosAtividade();
      carregarStatusAtividade();
  });

function salvarAtividadeAcademica() {

    if (!alunoAtividade.value) {
        alert("Selecione um aluno");
        return;
    }

    if (!descricaoAtividade.value) {
        alert("Informe a descrição da atividade");
        return;
    }

    if (!statusAtividade.value) {
        alert("Selecione o status da atividade");
        return;
    }

    const atividadeAcademica = {
        descricao: descricaoAtividade.value,
        observacao: observacaoAtividade.value || null,
        status: statusAtividade.value,
        idAluno: Number(alunoAtividade.value)
    };

    console.log("Enviando atividade acadêmica:", atividadeAcademica);

    fetch("http://localhost:8888/api/atividades", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(atividadeAcademica)
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao salvar atividade acadêmica");
        return res.json();
    })
    .then(() => {
        alert("Atividade acadêmica cadastrada com sucesso!");

        const modal = bootstrap.Modal.getInstance(
            document.getElementById("modalAtividadeAcademica")
        );

        document.activeElement.blur();
        modal.hide();
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