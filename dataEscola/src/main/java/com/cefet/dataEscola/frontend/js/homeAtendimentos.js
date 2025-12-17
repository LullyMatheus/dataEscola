// URL base da API
const API_BASE_URL = "http://localhost:8888/api";

// Roda quando a página carrega
document.addEventListener("DOMContentLoaded", () => {


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

    // Carrega as situações assim que abre a página
    carregarSituacoesAtendimento();

    // --- CONFIGURAÇÃO DE FILTRO ---

    // Botão "Buscar" (Inteligente: Filtra por nome ou traz todos)
    const btnBuscar = document.getElementById("btnBuscar");
    if (btnBuscar) {
        btnBuscar.addEventListener("click", () => {
            const nomePesquisa = document.getElementById("pesquisaNome").value;
            
            if (nomePesquisa.trim() !== "") {
                // FLUXO DE BUSCA POR NOME:
                // Primeiro buscamos o Aluno para descobrir o ID
                fetch(`${API_BASE_URL}/alunos/nome/${nomePesquisa}`)
                    .then(res => {
                        if(!res.ok) throw new Error("Erro ao buscar aluno");
                        return res.json();
                    })
                    .then(alunos => {
                        if (alunos && alunos.length > 0) {
                            // Pega o ID do primeiro aluno encontrado
                            const idAluno = alunos[0].id;
                            
                            // Agora buscamos os atendimentos pelo ID
                            carregarListaAtendimentos(`${API_BASE_URL}/atendimento/por-aluno/${idAluno}`);
                        } else {
                            alert("Nenhum aluno encontrado com esse nome.");
                            // Limpa a tabela
                            document.getElementById("tabela-atendimentos").innerHTML = "";
                        }
                    })
                    .catch(err => {
                        console.error(err);
                        alert("Erro ao pesquisar aluno: " + err.message);
                    });

            } else {
                // Se estiver vazio, traz todos
                carregarListaAtendimentos(`${API_BASE_URL}/atendimento`);
            }
        });
    }

    // Botão "Minha Semana"
    const btnMinhaSemana = document.getElementById("btnMinhaSemana");
    if (btnMinhaSemana) {
        btnMinhaSemana.addEventListener("click", () => {
            // Chama o endpoint da semana atual
            carregarListaAtendimentos(`${API_BASE_URL}/atendimento/semana-atual`);
        });
    }
    
    // Detecta quando o modal está prestes a abrir para carregar os selects
    const modalAtendimento = document.getElementById("modalAtendimento");
    if (modalAtendimento) {
        modalAtendimento.addEventListener("show.bs.modal", () => {
            carregarAlunosSelect();
            carregarResponsaveisSelect();
        });
    }
});


// 2. Função Genérica para Buscar e Preencher Tabela
function carregarListaAtendimentos(url) {
    fetch(url)
        .then(res => {
            if (!res.ok) throw new Error("Erro ao buscar atendimentos");
            return res.json();
        })
        .then(atendimentos => {
            preencherTabelaAtendimentos(atendimentos);
            
            if (atendimentos.length === 0) {
                alert("Nenhum atendimento encontrado.");
            }
        })
        .catch(err => {
            console.error(err);
            alert("Erro ao carregar lista: " + err.message);
        });
}


// 3. Preencher a Tabela
function preencherTabelaAtendimentos(atendimentos) {
    const tabela = document.getElementById("tabela-atendimentos");
    tabela.innerHTML = "";

    atendimentos.forEach(atendimento => {

        let dataFormatada = atendimento.dataAtendimento; 

        tabela.innerHTML += `
            <tr>
                <td>${atendimento.alunoNome || 'N/A'}</td>
                <td>${atendimento.descricao}</td>
                <td>${dataFormatada}</td>
                <td>${atendimento.situacao}</td>
                <td>${atendimento.usuarioNome || 'N/A'}</td>
            </tr>
        `;
    });
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


// --- FUNÇÕES AUXILIARES DE CARREGAMENTO (DROPDOWNS) ---

function carregarSituacoesAtendimento() {
    fetch(`${API_BASE_URL}/atendimento/situacoes`)
        .then(res => res.json())
        .then(situacoes => {
            const select = document.getElementById("situacaoAtendimento");
            if(select) {
                select.innerHTML = '<option value="">Selecione a situação</option>';
                situacoes.forEach(s => {
                    select.innerHTML += `<option value="${s}">${s}</option>`;
                });
            }
        })
        .catch(err => console.error("Erro situacoes", err));
}

function carregarAlunosSelect() {
    // Busca todos os alunos para preencher o select do modal
    fetch(`${API_BASE_URL}/aluno`)
        .then(res => res.json())
        .then(alunos => {
            const select = document.getElementById("alunoAtendimento");
            select.innerHTML = '<option value="">Selecione o aluno</option>';
            alunos.forEach(aluno => {
                select.innerHTML += `<option value="${aluno.id}">${aluno.nome}</option>`;
            });
        })
        .catch(err => console.error("Erro alunos", err));
}

function carregarResponsaveisSelect() {
    // Busca todos os usuários/servidores para preencher o select
    fetch(`${API_BASE_URL}/usuario`)
        .then(res => res.json())
        .then(usuarios => {
            const select = document.getElementById("responsavelAtendimento");
            select.innerHTML = '<option value="">Selecione o responsável</option>';
            usuarios.forEach(usuario => {
                select.innerHTML += `<option value="${usuario.id}">${usuario.nome}</option>`;
            });
        })
        .catch(err => console.error("Erro usuarios", err));
}