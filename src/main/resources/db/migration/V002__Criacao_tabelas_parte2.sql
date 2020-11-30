create table usuario(
	id bigint not null auto_increment,
	nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(20) not null,
    data_cadastro datetime not null,

	primary key (id)
)engine=InnoDB, default charset=utf8;


create table grupo(
	id bigint not null auto_increment,
	nome varchar(50) not null,

	primary key (id)
)engine=InnoDB, default charset=utf8;


create table permissao(
	id bigint not null auto_increment,
	nome varchar(50) not null,
	descricao varchar(255) not null,

	primary key (id)
)engine=InnoDB, default charset=utf8;


create table usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null,
	
	primary key (usuario_id, grupo_id)
) engine=InnoDB default charset=utf8;


create table grupo_permissao (
	grupo_id bigint not null,
	permissao_id bigint not null,
	
	primary key (grupo_id, permissao_id)
) engine=InnoDB default charset=utf8;