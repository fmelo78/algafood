create table pedido (
	id bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    taxa_frete decimal(10,2) not null,
    valor_total decimal(10,2) not null,
    
    status varchar(10) not null,
    data_criacao datetime not null,
    data_confirmacao datetime,
    data_cancelamento datetime,
    data_entrega datetime,
    
    end_logradouro varchar(50) not null,
    end_numero varchar(10) not null,
    end_complemento varchar(50),
    end_bairro varchar(50) not null,
    end_cep varchar(10) not null,
		end_cidade_id bigint not null,
		forma_pagamento_id bigint not null,
		restaurante_id bigint not null,
        cliente_id bigint not null,
                
    primary key (id),
    foreign key (end_cidade_id) references cidade(id),
    foreign key (forma_pagamento_id) references forma_pagamento(id),
    foreign key (restaurante_id) references restaurante(id),
    foreign key (cliente_id) references usuario(id)
)engine=InnoDB, default charset=utf8;


create table item_pedido (
	id bigint not null auto_increment,
    quantidade int not null,
    preco_unitario decimal(10,2) not null,
    preco_total decimal(10,2) not null,
    observacao varchar(255),
		produto_id bigint not null,
        pedido_id bigint not null,
    
	primary key (id),
    foreign key (produto_id) references produto(id),
    foreign key (pedido_id) references pedido(id)
)engine=InnoDB, default charset=utf8;