create table produto(
	id bigint not null auto_increment,
	nome varchar(50) not null,
	descricao varchar(255) not null,
	preco decimal(10,2) not null,
	ativo boolean not null default(true),
		restaurante_id bigint not null,
    
    primary key (id),
    foreign key (restaurante_id) references restaurante(id)
)engine=InnoDB, default charset=utf8;


create table forma_pagamento(
	id bigint not null auto_increment,
    descricao varchar(255) not null,
    
    primary key (id)
)engine=InnoDB, default charset=utf8;


create table restaurante_forma_pagamento (
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,
	
	primary key (restaurante_id, forma_pagamento_id)
) engine=InnoDB default charset=utf8;