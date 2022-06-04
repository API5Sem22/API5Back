--liquibase formatted sql
--changeset Gabriel Ferraz:1

insert into Carteira(crt_descricao) values('Junior');
insert into Carteira(crt_descricao) values('Pleno');
insert into Carteira(crt_descricao) values('Senior');
insert into Carteira(crt_descricao) values('Especialista');

--changeset Gabriel Ferraz:2

insert into Cargo(car_descricao) values('Vendedor');
insert into Cargo(car_descricao) values('Admin');
insert into Cargo(car_descricao) values('Analista de negócios');