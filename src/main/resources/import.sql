insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into estado (id, nome) values (1, 'São Paulo');
insert into estado (id, nome) values (2, 'Rio de Janeiro');
insert into estado (id, nome) values (3, 'Espírito Santo');
insert into estado (id, nome) values (4, 'Minas Gerais');

insert into cidade (id, nome, estado_id) values (1, 'São Paulo', 1);
insert into cidade (id, nome, estado_id) values (2, 'Santos', 1);
insert into cidade (id, nome, estado_id) values (3, 'Rio de Janeiro', 2);
insert into cidade (id, nome, estado_id) values (4, 'Niterói', 2);
insert into cidade (id, nome, estado_id) values (5, 'Vitória', 3);
insert into cidade (id, nome, estado_id) values (6, 'Vila Velha', 3);
insert into cidade (id, nome, estado_id) values (7, 'Belo Horizonte', 4);
insert into cidade (id, nome, estado_id) values (8, 'Uberaba', 4);

insert into restaurante (nome, taxa_frete, cozinha_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro, end_cidade_id, data_criacao, data_atualizacao)	values ('ThaiFood1', 5.11, 1, '11111-000', 'Rua Azul', '30', 'sobrado', 'Santana', 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro, end_cidade_id, data_criacao, data_atualizacao) values ('ThaiFood2', 5.22, 1, '22222-000', 'Rua Verde', '42', null, 'Boqueirão', 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro, end_cidade_id, data_criacao, data_atualizacao) values ('IndiFood1', 8.11, 2, '33333-000', 'Rua Amarela', '123', 'fundos', 'Vila Maria', 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, end_cep, end_logradouro, end_numero, end_complemento, end_bairro, end_cidade_id, data_criacao, data_atualizacao) values ('IndiiFood2', 8.221, 2, '44444-000', 'Rua Vermelha', '789', null, 'Lagoa', 4, utc_timestamp, utc_timestamp);

insert into forma_pagamento (id, descricao) values (1, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (2, 'Crédito');
insert into forma_pagamento (id, descricao) values (3, 'Débito');

insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Hamburger', 'Pão com carne', 10, true, 1);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (2, 'X-Burger', 'Pão com carne e queijo', 13, true, 1);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (3, 'X-Salada', 'Pão com carne e alface', 12, false, 1);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (4, 'Mussarela', 'Queijo mussarela', 30, true, 2);
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (5, 'Atum', 'Atum e cebola', 35, true, 2);

insert into permissao (id, nome, descricao) values (1, 'Consulta Produto', 'Permite somente a consulta de produtos');
insert into permissao (id, nome, descricao) values (2, 'Cadastra Produto', 'Permite consulta, criação e alteração de produtos');
insert into permissao (id, nome, descricao) values (3, 'Consulta Pedidos', 'Permite somente a consulta de pedidos');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 1), (2, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 2), (4, 3);