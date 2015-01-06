# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table classe_chimique (
  id                        bigint auto_increment not null,
  label                     varchar(255),
  pere                      integer,
  fils                      integer,
  constraint pk_classe_chimique primary key (id))
;

create table classe_pharmaco (
  id                        bigint auto_increment not null,
  label                     varchar(255),
  pere                      integer,
  fils                      integer,
  constraint pk_classe_pharmaco primary key (id))
;

create table dispo_medical (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_dispo_medical primary key (id))
;

create table effet_indesirable (
  id                        bigint auto_increment not null,
  label                     varchar(255),
  pere                      integer,
  fils                      integer,
  constraint pk_effet_indesirable primary key (id))
;

create table medicament (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_medicament primary key (id))
;

create table substance (
  id                        bigint auto_increment not null,
  label                     varchar(255),
  constraint pk_substance primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create table utilisateur (
  id                        bigint auto_increment not null,
  login                     varchar(255),
  mdp                       varchar(255),
  role                      varchar(255),
  constraint pk_utilisateur primary key (id))
;


create table effet_indesirable_classe_pharmaco (
  effet_indesirable_id           bigint not null,
  classe_pharmaco_id             bigint not null,
  constraint pk_effet_indesirable_classe_pharmaco primary key (effet_indesirable_id, classe_pharmaco_id))
;

create table effet_indesirable_classe_chimique (
  effet_indesirable_id           bigint not null,
  classe_chimique_id             bigint not null,
  constraint pk_effet_indesirable_classe_chimique primary key (effet_indesirable_id, classe_chimique_id))
;

create table substance_medicament (
  substance_id                   bigint not null,
  medicament_id                  bigint not null,
  constraint pk_substance_medicament primary key (substance_id, medicament_id))
;

create table substance_classe_pharmaco (
  substance_id                   bigint not null,
  classe_pharmaco_id             bigint not null,
  constraint pk_substance_classe_pharmaco primary key (substance_id, classe_pharmaco_id))
;

create table substance_classe_chimique (
  substance_id                   bigint not null,
  classe_chimique_id             bigint not null,
  constraint pk_substance_classe_chimique primary key (substance_id, classe_chimique_id))
;



alter table effet_indesirable_classe_pharmaco add constraint fk_effet_indesirable_classe_pharmaco_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_classe_pharmaco add constraint fk_effet_indesirable_classe_pharmaco_classe_pharmaco_02 foreign key (classe_pharmaco_id) references classe_pharmaco (id) on delete restrict on update restrict;

alter table effet_indesirable_classe_chimique add constraint fk_effet_indesirable_classe_chimique_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_classe_chimique add constraint fk_effet_indesirable_classe_chimique_classe_chimique_02 foreign key (classe_chimique_id) references classe_chimique (id) on delete restrict on update restrict;

alter table substance_medicament add constraint fk_substance_medicament_substance_01 foreign key (substance_id) references substance (id) on delete restrict on update restrict;

alter table substance_medicament add constraint fk_substance_medicament_medicament_02 foreign key (medicament_id) references medicament (id) on delete restrict on update restrict;

alter table substance_classe_pharmaco add constraint fk_substance_classe_pharmaco_substance_01 foreign key (substance_id) references substance (id) on delete restrict on update restrict;

alter table substance_classe_pharmaco add constraint fk_substance_classe_pharmaco_classe_pharmaco_02 foreign key (classe_pharmaco_id) references classe_pharmaco (id) on delete restrict on update restrict;

alter table substance_classe_chimique add constraint fk_substance_classe_chimique_substance_01 foreign key (substance_id) references substance (id) on delete restrict on update restrict;

alter table substance_classe_chimique add constraint fk_substance_classe_chimique_classe_chimique_02 foreign key (classe_chimique_id) references classe_chimique (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table classe_chimique;

drop table substance_classe_chimique;

drop table effet_indesirable_classe_chimique;

drop table classe_pharmaco;

drop table substance_classe_pharmaco;

drop table effet_indesirable_classe_pharmaco;

drop table dispo_medical;

drop table effet_indesirable;

drop table medicament;

drop table substance_medicament;

drop table substance;

drop table user;

drop table utilisateur;

SET FOREIGN_KEY_CHECKS=1;

