-- Inserindo alunos
INSERT INTO tb_aluno (id, matricula, nome, data_nascimento, email, contato) VALUES (1,'2023100001', 'Lisa Simpson', '2002-05-09', 'lisa.simpson@email.com', 'Mãe: 2233-4545');
INSERT INTO tb_aluno (id, matricula, nome, data_nascimento, email, contato) VALUES (2,'2023100421', 'Harry Potter', '2002-08-02', 'harry.potter@hogwarts.ac.uk', 'Hagrid: 555-3854');
INSERT INTO tb_aluno (id, matricula, nome, data_nascimento, email, contato) VALUES (3,'2023102721', 'Hermione Granger', '2002-11-07', 'hermione.granger@hogwarts.ac.uk', 'Pai: 777-9367');
INSERT INTO tb_aluno (id, matricula, nome, data_nascimento, email, contato) VALUES (4,'2023103947', 'Peter Parker', '2002-01-12', 'peter.parker@midtown.high', 'Tia: 999-2778');
INSERT INTO tb_aluno (id, matricula, nome, data_nascimento, email, contato) VALUES (5,'2023100120', 'Matilda Wormwood', '2002-05-04', 'matilda@library.org', 'Mãe Adotiva: 0808-3132');

--Preencher tb_usuario
INSERT INTO tb_usuario (id, cpf, email, nome, senha, tipo) VALUES (1,'111.111.111-11', 'ivone@gmail.com', 'Ivone Rosa', '123', 'SERVIDOR');
INSERT INTO tb_usuario (id, cpf, email, nome, senha, tipo) VALUES (2,'222.222.222-22', 'odilon@gmail.com', 'Odilon Correa', '123', 'PROFESSOR');
INSERT INTO tb_usuario (id, cpf, email, nome, senha, tipo) VALUES (3,'333.333.333-33', 'pantuza@gmail.com', 'Lucas Pantuza', '123', 'PROFESSOR');

--Preencher tb_atendimento
INSERT INTO tb_atendimento (id, descricao, data_atendimento, data_lembrete, situacao, aluno_id, usuario_id) VALUES (1,'Atendimento', '2024-03-02', '2024-03-01', 'REALIZADO', 1, 1);
INSERT INTO tb_atendimento (id, descricao, data_atendimento, data_lembrete, situacao, aluno_id, usuario_id) VALUES (2,'Atendimento', '2024-03-05', '2024-03-04', 'AGENDADO', 2, 3);
INSERT INTO tb_atendimento (id, descricao, data_atendimento, data_lembrete, situacao, aluno_id, usuario_id) VALUES (3,'Atendimento', '2024-03-06', '2024-03-05', 'CANCELADO', 4, 3);
INSERT INTO tb_atendimento (id, descricao, data_atendimento, data_lembrete, situacao, aluno_id, usuario_id) VALUES (4,'Atendimento', '2024-03-08', '2024-03-07', 'REALIZADO', 3, 2);

--Preencher tb_atividadeAcademica
INSERT INTO tb_atividade_academica (id, id_aluno, descricao, observacao, status_atividade) VALUES (1,1,'Debate Filosófico','Sem observações','CONCLUIDO');