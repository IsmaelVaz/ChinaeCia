/*create table categoria(
cod_categoria int not null auto_increment primary key,
nome varchar(50) not null);

/*create table produto(
cod_produto int auto_increment primary key,
nome varchar(100) not null,
valor double(7,2) not null,
cod_categoria int not null,
foreign key (cod_categoria) references categoria(cod_categoria));

/*create table mesa(
cod_mesa int not null auto_increment primary key,
situacao varchar(15) not null
);

/*create table cliente(
cod_cliente int not null auto_increment primary key,
nome varchar (100) not null,
telefone varchar(20),
cpf varchar(15),
rg varchar(20)
);

/*create table mesa_produto(
cod_mesa int not null,
cod_produto int not null,
foreign key (cod_mesa) references mesa(cod_mesa),
foreign key (cod_produto) references produto(cod_produto)
);

/*create table cliente_produto(
cod_cliente int not null,
cod_produto int not null,
foreign key (cod_cliente) references cliente(cod_cliente),
foreign key (cod_produto) references produto(cod_produto)
);

/*create table usuario(
cod_usuario int not null auto_increment primary key,
nome varchar(100) not null,
cpf varchar(15),
ultimo_login date,
subcargo varchar(30),
cod_cargo int,
nome_user varchar(50) unique,
senha varchar(200),
foreign key(cod_cargo) references cargo(cod_cargo)
);
Alter table usuario add column dt_nasc date;
/*create table cargo(
cod_cargo int auto_increment primary key,
nome varchar(50)
);

/*create table tipo_pagamento(
cod_tipopagamento int primary key auto_increment,
nome varchar(40) not null,
tipo int 
);

/*create table pagamento(
cod_tipopagamento int not null,
valor double(7,2),
foreign key (cod_tipopagamento) references tipo_pagamento(cod_tipopagamento)
);

insert into tipo_pagamento(nome, tipo) values ('CARTAO', 1);
update tipo_pagamento set tipo = 0 where cod_tipopagamento = 1;


insert into mesa(situacao) values('DISPONIVEL');

select * from mesa;
select * from produto;
select * from mesa_produto;

insert into mesa_produto(cod_mesa, cod_produto) values (1,1);

SELECT m.situacao, m.cod_mesa, SUM(p.valor) AS valor 
FROM mesa_produto AS mp 
RIGHT JOIN mesa AS m ON (mp.cod_mesa=m.cod_mesa) 
left JOIN produto AS p ON (mp.cod_produto = p.cod_produto) GROUP BY m.cod_mesa;

INSERT INTO produto_vendido(cod_produto, data) values (1, '2016-06-26 14:16:16');

create table produto_vendido(
cod_produto int not null,
data date not null
);


select * from mesa;


/*create table produto_peso(
cod_produto int not null,
valor_peso float(7,2),
foreign key(cod_produto) references produto(cod_produto)
);

select c.*,
max(cp.data) as ultima_data,
sum(p.valor) as valor_total
from cliente as c
left join cliente_produto as cp
on (c.cod_cliente=cp.cod_cliente)
left join produto as p 
on (cp.cod_produto=p.cod_produto) group by c.nome;



create table estoque(
cod_produto int not null,
qtd_min int not null,
qtd_atual int not null,
foreign key(cod_produto) references produto(cod_produto)
);


/*create table empresa (
cod_empresa int auto_increment primary key not null,
cnpf varchar(30),
nome varchar(100)
);
insert into empresa (cnpf, nome)  values
('12376557', 'SADIA'),
('67865567', 'PERDIGAO'),
('1331213131', 'JOSE');


/*CREATE TABLE despesa(
cod_despesa int not null auto_increment primary key,
valor double(7,2),
data date,
cod_empresa int,
foreign key(cod_empresa) references empresa(cod_empresa)
);

alter table despesa add column cod_usuario int not null references usuario(cod_usuario);

select * from despesa;

/*create table despesa_tipo(
cod_despesa int,
vlr_metade float(7,2),
vlr_saida float(7,2),
foreign key(cod_despesa) references despesa(cod_despesa)
);

select * from usuario;

desc usuario;

update usuario set ultimo_login = now() where cod_usuario =1;

create table login_acessado(
cod_usuario int,
data datetime,
foreign key(cod_usuario) references usuario(cod_usuario)
);

SELECT cod_usuario, data FROM login_acessado order by data desc limit 1;

select * from despesa;

select d.cod_despesa, d.data, e.nome as nomeEmpresa, d.valor as valorTotal
from despesa as d 
left join despesa_tipo as dt 
on (dt.cod_despesa = d.cod_despesa) 
inner join empresa as e 
on (d.cod_empresa = e.cod_empresa) ;

select sum(valor) as valorTotal from despesa ;

select * from produto_vendido where cod_produto = cod_produto group by cod_produto and data ;


select * from produto;
select pv.cod_produto, p.nome, p.valor, sum(pv.cod_produto) as soma
from produto_vendido as pv 
inner join produto as p 
on pv.cod_produto = p.cod_produto 
where pv.data = '2016-06-26' group by pv.cod_produto;

select sum(p.valor) as valorTotal
from produto_vendido as pv 
inner join produto as p 
on pv.cod_produto = p.cod_produto ;

create table fechamento (
cod_fechamento int not null auto_increment primary key,
total_saida float  ,
total_despesa float  ,
fundo float  ,
verif int(1) not null
);

alter table fechamento add column data datetime;
alter table fechamento add column usr int;



select * from produto_vendido;

select pv.*, p.* from produto_vendido as pv inner join produto as p on pv.cod_produto = p.cod_produto where data = current_date;

select current_date;

select * from despesa;

alter table produto_vendido add column fechado int references fechamento(cod_fechamento);

select d.cod_despesa, e.nome as nomeEmpresa, d.valor as valorTotal, dt.vlr_metade, dt.vlr_saida, d.data
from despesa as d 
left join despesa_tipo as dt 
on (dt.cod_despesa = d.cod_despesa) 
inner join empresa as e 
on (d.cod_empresa = e.cod_empresa) 
where d.data between concat(current_date, ' ', '00:00:00') and concat(current_date, ' ', '23:59:59');

select cod_tipopagamento, sum(valor) as total from pagamento where data between concat(current_date-1, ' ', '00:00:00') and concat(current_date, ' ', '23:59:59') group by cod_tipopagamento;


insert into pagamento (cod_tipopagamento, valor) values (5, 1200), (6, 102);

select * from tipo_pagamento;
select * from pagamento;


insert into tipo_pagamento(nome, tipo) values
('DINHEIRO',1),
('DÉBITO', 0),
('CRÉDITO', 0),
('VOUCHER', 0),
('VISA-VALE', 0);

select * from despesa_tipo;

select sum(valor)-3542 from despesa 
where data between concat(current_date-1, ' ', '00:00:00') 
and concat(current_date, ' ', '23:59:59');

select d.*, dt.* from despesa as d left join despesa_tipo as dt on d.cod_despesa = dt.cod_despesa where vlr_saida is not null and data between concat(current_date-1, ' ', '00:00:00') and concat(current_date, ' ', '23:59:59');

select * from fechamento;

insert into fechamento (fundo, verif, data) values (10.00, 1, now());

delete from fechamento where cod_fechamento =2;

delimiter //
create trigger apos_fechamento before update on fechamento 
for each row
begin 
	set @novo = new.cod_fechamento;
    set @velho= old.cod_fechamento;
end //
delimiter ;

delimiter //
create procedure apos_fechamento(cod int)
begin
	update produto_vendido set fechado = cod where fechado = null or fechado is null;
end //
delimiter ;

select cod_fechamento as cod from fechamento order by cod_fechamento desc limit 1;

update produto_vendido set fechado = null where fechado = 1;

select @novo, @velho;
drop trigger apos_fechamento;
select * from produto_vendido;

select * from despesa;

update fechamento set total_saida = null;

INSERT INTO pagamento(cod_tipopagamento, valor, data) VALUES (2, 150.00, now());

select * from pagamento;

select cod_tipopagamento, sum(valor) as total
from pagamento where fechado is null
group by cod_tipopagamento;

select * from fechamento;

select * from pagamento;
select * from fechamento where data between concat(current_date, ' ', '00:00:00') and concat(current_date, ' ', '23:59:59');
*/

