-- Inserindo alunos
INSERT INTO tb_aluno (matricula, nome, dataNascimento, email, contatos) VALUES 
('2023100001', 'Lisa Simpson', '2002-05-09', 'lisa.simpson@email.com', 'Mãe: 2233-4545'),
('2023100002', 'Harry Potter', '1980-07-31', 'harry.potter@hogwarts.com', 'Tio Válter: 9999-0001'),
('2023100003', 'Hermione Granger', '1979-09-19', 'mione@magic.com', 'Pais: 8888-7777'),
('2023100004', 'Ron Weasley', '1980-03-01', 'ron.weasley@toca.com', 'Molly: 7777-6666'),
('2023100005', 'Miles Morales', '2005-12-10', 'miles.spidey@web.com', 'Rio: 5555-4444'),
('2023100006', 'Gwen Stacy', '2004-06-15', 'gwen.ghost@web.com', 'Pai: 4444-3333'),
('2023100007', 'Peter Parker', '2001-08-10', 'peter.parker@dailybugle.com', 'Tia May: 3333-2222'),
('2023100008', 'Bruce Wayne', '1995-02-19', 'bruce@wayne.corp', 'Alfred: 2222-1111'),
('2023100009', 'Clark Kent', '1994-06-18', 'clark.kent@planet.com', 'Mãe Martha: 1111-0000'),
('2023100010', 'Diana Prince', '1990-03-22', 'diana@themyscira.com', 'Mãe: 9876-5432'),
('2023100011', 'Tony Stark', '1985-05-29', 'tony@stark.ind', 'Pepper: 8765-4321'),
('2023100012', 'Natasha Romanoff', '1992-11-22', 'natasha@shield.gov', 'Nick Fury: 7654-3210'),
('2023100013', 'Steve Rogers', '1918-07-04', 'cap@avengers.com', 'Bucky: 6543-2109'),
('2023100014', 'Wanda Maximoff', '1999-02-14', 'wanda@magic.hex', 'Visão: 5432-1098'),
('2023100015', 'Shuri Wakanda', '2005-09-05', 'shuri@wakanda.tech', 'Ramonda: 4321-0987');


-- Inserindo Usuarios
-- Inserção de 15 usuários na tabela tb_usuario
-- Atributos: nome, login, senha, email, tipo (ENUM: 'PROFESSOR' ou 'SERVIDOR')
INSERT INTO tb_usuario (nome, login, senha, email, tipo) VALUES
('Carlos Silva', 'carlos.silva', 'senha123', 'carlos.silva@escola.com.br', 'PROFESSOR'),
('Ana Souza', 'ana.souza', 'segredo456', 'ana.souza@admin.com.br', 'SERVIDOR'),
('Roberto Almeida', 'roberto.almeida', 'prof2023', 'roberto.almeida@escola.com.br', 'PROFESSOR'),
('Juliana Costa', 'juliana.costa', 'servidor99', 'juliana.costa@admin.com.br', 'SERVIDOR'),
('Marcos Oliveira', 'marcos.oliveira', 'fisica10', 'marcos.oliveira@escola.com.br', 'PROFESSOR'),
('Fernanda Lima', 'fernanda.lima', 'geo_mundi', 'fernanda.lima@escola.com.br', 'PROFESSOR'),
('Paulo Santos', 'paulo.santos', 'adm_paulo', 'paulo.santos@admin.com.br', 'SERVIDOR'),
('Beatriz Rocha', 'beatriz.rocha', 'historia_b', 'beatriz.rocha@escola.com.br', 'PROFESSOR'),
('Lucas Pereira', 'lucas.pereira', 'lucas_rh', 'lucas.pereira@admin.com.br', 'SERVIDOR'),
('Camila Dias', 'camila.dias', 'bio_vida', 'camila.dias@escola.com.br', 'PROFESSOR'),
('Ricardo Gomes', 'ricardo.gomes', 'finan_ric', 'ricardo.gomes@admin.com.br', 'SERVIDOR'),
('Patrícia Nunes', 'patricia.nunes', 'matematica1', 'patricia.nunes@escola.com.br', 'PROFESSOR'),
('Gustavo Martins', 'gustavo.martins', 'portaria_g', 'gustavo.martins@admin.com.br', 'SERVIDOR'),
('Larissa Mendes', 'larissa.mendes', 'quimica_l', 'larissa.mendes@escola.com.br', 'PROFESSOR'),
('Eduardo Ferreira', 'eduardo.ferreira', 'ti_suporte', 'eduardo.ferreira@admin.com.br', 'SERVIDOR');


-- Inserindo atividades Academicas
INSERT INTO tb_atividadeAcademica (descricao, observacao, statusAtividade, aluno) VALUES
('Seminário de História', 'Grupo de 4 pessoas', 'CONCLUIDO', 12),
('Entrega de Redação', NULL, 'ATIVO', 5),
('Laboratório de Química', 'Trazer jaleco', 'ATIVO', 8),
('Projeto de Física', NULL, 'CONCLUIDO', 1),
('Exercícios de Inglês', NULL, 'ATIVO', 15),
('Apresentação de TCC', 'Auditório Principal', 'ATIVO', 7),
('Estudo Dirigido Bio', NULL, 'CONCLUIDO', 4),
('Trabalho de Artes', 'Uso de tinta óleo', 'CONCLUIDO', 2),
('Revisão de Literatura', NULL, 'ATIVO', 9),
('Debate Filosófico', NULL, 'CONCLUIDO', 6),
('Maratona de Programação', 'Equipes duplas', 'ATIVO', 14),
('Relatório de Estágio', NULL, 'CONCLUIDO', 11),
('Teste de Aptidão Física', 'Quadra externa', 'ATIVO', 13);


--Inserindo atendimentos
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-02 10:00:00', '2024-03-01 10:00:00', 'REALIZADO', 1, 5);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-05 14:30:00', '2024-03-04 14:30:00', 'AGENDADO', 12, 3);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-06 09:00:00', '2024-03-05 09:00:00', 'CANCELADO', 4, 15);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-08 11:00:00', '2024-03-07 11:00:00', 'REALIZADO', 7, 2);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-10 16:00:00', '2024-03-09 16:00:00', 'REMARCADO', 15, 8);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-12 13:00:00', '2024-03-11 13:00:00', 'AGENDADO', 3, 10);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-15 08:30:00', '2024-03-14 08:30:00', 'REALIZADO', 9, 1);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-16 15:45:00', '2024-03-15 15:45:00', 'REALIZADO', 2, 14);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-18 10:15:00', '2024-03-17 10:15:00', 'CANCELADO', 11, 4);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-20 17:00:00', '2024-03-19 17:00:00', 'AGENDADO', 6, 6);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-22 09:30:00', '2024-03-21 09:30:00', 'REMARCADO', 14, 12);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-25 14:00:00', '2024-03-24 14:00:00', 'REALIZADO', 8, 9);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-26 11:30:00', '2024-03-25 11:30:00', 'AGENDADO', 5, 7);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-03-28 16:15:00', '2024-03-27 16:15:00', 'REALIZADO', 13, 11);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-01 10:00:00', '2024-03-31 10:00:00', 'CANCELADO', 10, 13);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-03 13:45:00', '2024-04-02 13:45:00', 'AGENDADO', 1, 5);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-05 09:15:00', '2024-04-04 09:15:00', 'REALIZADO', 15, 2);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-07 15:30:00', '2024-04-06 15:30:00', 'REMARCADO', 3, 15);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-10 11:00:00', '2024-04-09 11:00:00', 'AGENDADO', 7, 8);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-12 14:20:00', '2024-04-11 14:20:00', 'REALIZADO', 12, 1);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-15 08:45:00', '2024-04-14 08:45:00', 'REALIZADO', 4, 10);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-17 16:50:00', '2024-04-16 16:50:00', 'CANCELADO', 9, 3);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-20 10:30:00', '2024-04-19 10:30:00', 'AGENDADO', 2, 14);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-22 13:15:00', '2024-04-21 13:15:00', 'REMARCADO', 11, 7);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-25 09:40:00', '2024-04-24 09:40:00', 'REALIZADO', 6, 6);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-27 15:10:00', '2024-04-26 15:10:00', 'AGENDADO', 14, 12);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-04-30 11:50:00', '2024-04-29 11:50:00', 'REALIZADO', 8, 9);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-05-02 14:00:00', '2024-05-01 14:00:00', 'CANCELADO', 5, 4);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-05-05 10:00:00', '2024-05-04 10:00:00', 'AGENDADO', 13, 11);
INSERT INTO tb_atendimento (descricao, dataAtendimento, dataLembrete, situacao, aluno, usuario) VALUES (NULL, '2024-05-08 16:30:00', '2024-05-07 16:30:00', 'REALIZADO', 10, 13);