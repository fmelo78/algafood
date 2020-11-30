CREATE TABLE cozinha (
	id bigint not null auto_increment,
	nome varchar(50) not null,
    
	primary key (id)
)engine=InnoDB, default charset=utf8;


create table estado(
	id bigint not null auto_increment,
    nome varchar(20) not null,
    
    primary key (id)
)engine=InnoDB, default charset=utf8;


create table cidade (
	id bigint not null auto_increment,
    nome varchar(100) not null,
    	estado_id bigint not null,
    
    primary key (id),
    foreign key (estado_id) references estado(id)
)engine=InnoDB, default charset=utf8;


create table restaurante(
	id bigint not null auto_increment,
    nome varchar(50) not null,
    taxa_frete decimal(6,2) not null,
		cozinha_id bigint not null,
    end_logradouro varchar(100),
    end_numero varchar(10),
    end_complemento varchar(10),
    end_bairro varchar(50),
		end_cidade_id bigint,
    end_cep varchar(10),
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    
    primary key (id),
    FOREIGN KEY (cozinha_id) REFERENCES cozinha(id),
    foreign key (end_cidade_id) references cidade(id)
)engine=InnoDB, default charset=utf8;