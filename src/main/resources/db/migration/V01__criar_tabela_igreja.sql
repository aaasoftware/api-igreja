create table igreja (
	id bigint(20) primary key auto_increment,
	nome varchar(50) not null,
	data_cadastro datetime,
	cep varchar(15) not null,
	cidade varchar(50) not null,
	logradouro varchar(50),
	bairro varchar(20),
	estado varchar(15)
) engine=InnoDB default charset=utf8;