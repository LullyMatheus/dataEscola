// URL base da API
const API_BASE_URL = "http://localhost:8888/api";

// Roda quando a página carrega
document.addEventListener("DOMContentLoaded", () => {
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

    // A parte de cadastro
    
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
        // Formatação simples da data (yyyy-mm-dd -> dd/mm/yyyy)
        // Se a data vier com hora, use new Date(data).toLocaleDateString()
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


// 4. Salvar Atendimento
// Nota: Essa função precisa ser global (window) se for chamada via onclick no HTML, 
// mas idealmente deveria ser via addEventListener no botão "Salvar" do modal.
// Como no seu HTML está onclick="salvarAtendimento()", mantemos assim:
window.salvarAtendimento = function() {
    const alunoSelect = document.getElementById("alunoAtendimento");
    const responsavelSelect = document.getElementById("responsavelAtendimento");
    const dataInput = document.getElementById("dataAtendimento");
    const descricaoInput = document.getElementById("descricaoAtendimento");
    const situacaoSelect = document.getElementById("situacaoAtendimento");

    if (!alunoSelect.value) return alert("Selecione um aluno");
    if (!responsavelSelect.value) return alert("Selecione um responsável");
    if (!dataInput.value) return alert("Informe a data");

    const atendimento = {
        descricao: descricaoInput.value,
        dataAtendimento: dataInput.value, // O backend espera yyyy-MM-dd (formato padrão do input date)
        situacao: situacaoSelect.value,
        idAluno: Number(alunoSelect.value),
        idUsuario: Number(responsavelSelect.value),
        dataLembrete: null
    };

    fetch(`${API_BASE_URL}/atendimento`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(atendimento)
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao salvar");
        return res.json();
    })
    .then(() => {
        alert("Sucesso!");
        // Fecha modal
        const modalEl = document.getElementById("modalAtendimento");
        const modal = bootstrap.Modal.getInstance(modalEl);
        modal.hide();
        // Atualiza a lista
        carregarListaAtendimentos(`${API_BASE_URL}/atendimento`);
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
    fetch(`${API_BASE_URL}/aluno`) // Supondo que exista essa rota GET /aluno
        .then(res => res.json())
        .then(alunos => {
            const select = document.getElementById("alunoAtendimento");
            // Mantém a primeira opção "Selecione..."
            select.innerHTML = '<option value="">Selecione o aluno</option>';
            alunos.forEach(aluno => {
                select.innerHTML += `<option value="${aluno.id}">${aluno.nome}</option>`;
            });
        })
        .catch(err => console.error("Erro alunos", err));
}

function carregarResponsaveisSelect() {
    // Busca todos os usuários/servidores para preencher o select
    fetch(`${API_BASE_URL}/usuario`) // Supondo que exista essa rota GET /usuario
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