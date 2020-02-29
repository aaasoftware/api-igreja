create table historia (
	id bigint(20) primary key auto_increment,
	descricao text,
	igreja_id bigint(20),
	pastoral_id bigint(20),
	FOREIGN KEY (igreja_id) REFERENCES igreja(id),
	FOREIGN KEY (pastoral_id) REFERENCES pastoral(id)
) engine=InnoDB default charset=utf8;