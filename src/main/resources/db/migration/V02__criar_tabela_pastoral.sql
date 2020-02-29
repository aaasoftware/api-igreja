create table pastoral(
	id bigint(20) primary key auto_increment,
	nome varchar(60) not null,
	data_cadastro datetime,
	cep varchar(15) not null,
	cidade varchar(50) not null,
	logradouro varchar(50),
	bairro varchar(50),
	estado varchar(15)
) engine=InnoDB default charset=utf8;

