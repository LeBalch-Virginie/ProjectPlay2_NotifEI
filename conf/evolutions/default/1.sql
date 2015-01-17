# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table classe_chimique (
  code                      varchar(255) not null,
  label                     varchar(255),
  constraint pk_classe_chimique primary key (code))
;

create table classe_pharmaco (
  code                      varchar(255) not null,
  label                     varchar(255),
  constraint pk_classe_pharmaco primary key (code))
;

create table conservateur (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_conservateur primary key (id))
;

create table dispo_medical (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_dispo_medical primary key (id))
;

create table effet_indesirable (
  id                        bigint auto_increment not null,
  label                     varchar(255),
  constraint pk_effet_indesirable primary key (id))
;

create table excipient (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_excipient primary key (id))
;

create table medicament (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_medicament primary key (id))
;

create table parabene (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_parabene primary key (id))
;

create table principe_actif (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_principe_actif primary key (id))
;

create table produit_cosmetique (
  id                        bigint auto_increment not null,
  nom                       varchar(255),
  constraint pk_produit_cosmetique primary key (id))
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
  is_admin                  tinyint(1) default 0,
  type_user                 varchar(255),
  region                    varchar(255),
  constraint pk_user primary key (email))
;


create table hierarchie_classe_chimique (
  pere_id                        varchar(255) not null,
  fils_id                        varchar(255) not null,
  constraint pk_hierarchie_classe_chimique primary key (pere_id, fils_id))
;

create table hierarchie_classe_pharmaco (
  pere_id                        varchar(255) not null,
  fils_id                        varchar(255) not null,
  constraint pk_hierarchie_classe_pharmaco primary key (pere_id, fils_id))
;

create table conservateur_produit_cosmetique (
  conservateur_id                bigint not null,
  produit_cosmetique_id          bigint not null,
  constraint pk_conservateur_produit_cosmetique primary key (conservateur_id, produit_cosmetique_id))
;

create table effet_indesirable_classe_pharmaco (
  effet_indesirable_id           bigint not null,
  classe_pharmaco_code           varchar(255) not null,
  constraint pk_effet_indesirable_classe_pharmaco primary key (effet_indesirable_id, classe_pharmaco_code))
;

create table effet_indesirable_classe_chimique (
  effet_indesirable_id           bigint not null,
  classe_chimique_code           varchar(255) not null,
  constraint pk_effet_indesirable_classe_chimique primary key (effet_indesirable_id, classe_chimique_code))
;

create table effet_indesirable_dispo_medical (
  effet_indesirable_id           bigint not null,
  dispo_medical_id               bigint not null,
  constraint pk_effet_indesirable_dispo_medical primary key (effet_indesirable_id, dispo_medical_id))
;

create table effet_indesirable_conservateur (
  effet_indesirable_id           bigint not null,
  conservateur_id                bigint not null,
  constraint pk_effet_indesirable_conservateur primary key (effet_indesirable_id, conservateur_id))
;

create table effet_indesirable_excipient (
  effet_indesirable_id           bigint not null,
  excipient_id                   bigint not null,
  constraint pk_effet_indesirable_excipient primary key (effet_indesirable_id, excipient_id))
;

create table effet_indesirable_parabene (
  effet_indesirable_id           bigint not null,
  parabene_id                    bigint not null,
  constraint pk_effet_indesirable_parabene primary key (effet_indesirable_id, parabene_id))
;

create table effet_indesirable_medicament (
  effet_indesirable_id           bigint not null,
  medicament_id                  bigint not null,
  constraint pk_effet_indesirable_medicament primary key (effet_indesirable_id, medicament_id))
;

create table effet_indesirable_produit_cosmetique (
  effet_indesirable_id           bigint not null,
  produit_cosmetique_id          bigint not null,
  constraint pk_effet_indesirable_produit_cosmetique primary key (effet_indesirable_id, produit_cosmetique_id))
;

create table effet_indesirable_principe_actif (
  effet_indesirable_id           bigint not null,
  principe_actif_id              bigint not null,
  constraint pk_effet_indesirable_principe_actif primary key (effet_indesirable_id, principe_actif_id))
;

create table hierarchie_effet_indesirable (
  pere_id                        bigint not null,
  fils_id                        bigint not null,
  constraint pk_hierarchie_effet_indesirable primary key (pere_id, fils_id))
;

create table excipient_produit_cosmetique (
  excipient_id                   bigint not null,
  produit_cosmetique_id          bigint not null,
  constraint pk_excipient_produit_cosmetique primary key (excipient_id, produit_cosmetique_id))
;

create table parabene_produit_cosmetique (
  parabene_id                    bigint not null,
  produit_cosmetique_id          bigint not null,
  constraint pk_parabene_produit_cosmetique primary key (parabene_id, produit_cosmetique_id))
;

create table principe_actif_produit_cosmetique (
  principe_actif_id              bigint not null,
  produit_cosmetique_id          bigint not null,
  constraint pk_principe_actif_produit_cosmetique primary key (principe_actif_id, produit_cosmetique_id))
;

create table substance_medicament (
  substance_id                   bigint not null,
  medicament_id                  bigint not null,
  constraint pk_substance_medicament primary key (substance_id, medicament_id))
;

create table substance_classe_pharmaco (
  substance_id                   bigint not null,
  classe_pharmaco_code           varchar(255) not null,
  constraint pk_substance_classe_pharmaco primary key (substance_id, classe_pharmaco_code))
;

create table substance_classe_chimique (
  substance_id                   bigint not null,
  classe_chimique_code           varchar(255) not null,
  constraint pk_substance_classe_chimique primary key (substance_id, classe_chimique_code))
;



alter table hierarchie_classe_chimique add constraint fk_hierarchie_classe_chimique_classe_chimique_01 foreign key (pere_id) references classe_chimique (code) on delete restrict on update restrict;

alter table hierarchie_classe_chimique add constraint fk_hierarchie_classe_chimique_classe_chimique_02 foreign key (fils_id) references classe_chimique (code) on delete restrict on update restrict;

alter table hierarchie_classe_pharmaco add constraint fk_hierarchie_classe_pharmaco_classe_pharmaco_01 foreign key (pere_id) references classe_pharmaco (code) on delete restrict on update restrict;

alter table hierarchie_classe_pharmaco add constraint fk_hierarchie_classe_pharmaco_classe_pharmaco_02 foreign key (fils_id) references classe_pharmaco (code) on delete restrict on update restrict;

alter table conservateur_produit_cosmetique add constraint fk_conservateur_produit_cosmetique_conservateur_01 foreign key (conservateur_id) references conservateur (id) on delete restrict on update restrict;

alter table conservateur_produit_cosmetique add constraint fk_conservateur_produit_cosmetique_produit_cosmetique_02 foreign key (produit_cosmetique_id) references produit_cosmetique (id) on delete restrict on update restrict;

alter table effet_indesirable_classe_pharmaco add constraint fk_effet_indesirable_classe_pharmaco_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_classe_pharmaco add constraint fk_effet_indesirable_classe_pharmaco_classe_pharmaco_02 foreign key (classe_pharmaco_code) references classe_pharmaco (code) on delete restrict on update restrict;

alter table effet_indesirable_classe_chimique add constraint fk_effet_indesirable_classe_chimique_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_classe_chimique add constraint fk_effet_indesirable_classe_chimique_classe_chimique_02 foreign key (classe_chimique_code) references classe_chimique (code) on delete restrict on update restrict;

alter table effet_indesirable_dispo_medical add constraint fk_effet_indesirable_dispo_medical_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_dispo_medical add constraint fk_effet_indesirable_dispo_medical_dispo_medical_02 foreign key (dispo_medical_id) references dispo_medical (id) on delete restrict on update restrict;

alter table effet_indesirable_conservateur add constraint fk_effet_indesirable_conservateur_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_conservateur add constraint fk_effet_indesirable_conservateur_conservateur_02 foreign key (conservateur_id) references conservateur (id) on delete restrict on update restrict;

alter table effet_indesirable_excipient add constraint fk_effet_indesirable_excipient_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_excipient add constraint fk_effet_indesirable_excipient_excipient_02 foreign key (excipient_id) references excipient (id) on delete restrict on update restrict;

alter table effet_indesirable_parabene add constraint fk_effet_indesirable_parabene_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_parabene add constraint fk_effet_indesirable_parabene_parabene_02 foreign key (parabene_id) references parabene (id) on delete restrict on update restrict;

alter table effet_indesirable_medicament add constraint fk_effet_indesirable_medicament_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_medicament add constraint fk_effet_indesirable_medicament_medicament_02 foreign key (medicament_id) references medicament (id) on delete restrict on update restrict;

alter table effet_indesirable_produit_cosmetique add constraint fk_effet_indesirable_produit_cosmetique_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_produit_cosmetique add constraint fk_effet_indesirable_produit_cosmetique_produit_cosmetique_02 foreign key (produit_cosmetique_id) references produit_cosmetique (id) on delete restrict on update restrict;

alter table effet_indesirable_principe_actif add constraint fk_effet_indesirable_principe_actif_effet_indesirable_01 foreign key (effet_indesirable_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table effet_indesirable_principe_actif add constraint fk_effet_indesirable_principe_actif_principe_actif_02 foreign key (principe_actif_id) references principe_actif (id) on delete restrict on update restrict;

alter table hierarchie_effet_indesirable add constraint fk_hierarchie_effet_indesirable_effet_indesirable_01 foreign key (pere_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table hierarchie_effet_indesirable add constraint fk_hierarchie_effet_indesirable_effet_indesirable_02 foreign key (fils_id) references effet_indesirable (id) on delete restrict on update restrict;

alter table excipient_produit_cosmetique add constraint fk_excipient_produit_cosmetique_excipient_01 foreign key (excipient_id) references excipient (id) on delete restrict on update restrict;

alter table excipient_produit_cosmetique add constraint fk_excipient_produit_cosmetique_produit_cosmetique_02 foreign key (produit_cosmetique_id) references produit_cosmetique (id) on delete restrict on update restrict;

alter table parabene_produit_cosmetique add constraint fk_parabene_produit_cosmetique_parabene_01 foreign key (parabene_id) references parabene (id) on delete restrict on update restrict;

alter table parabene_produit_cosmetique add constraint fk_parabene_produit_cosmetique_produit_cosmetique_02 foreign key (produit_cosmetique_id) references produit_cosmetique (id) on delete restrict on update restrict;

alter table principe_actif_produit_cosmetique add constraint fk_principe_actif_produit_cosmetique_principe_actif_01 foreign key (principe_actif_id) references principe_actif (id) on delete restrict on update restrict;

alter table principe_actif_produit_cosmetique add constraint fk_principe_actif_produit_cosmetique_produit_cosmetique_02 foreign key (produit_cosmetique_id) references produit_cosmetique (id) on delete restrict on update restrict;

alter table substance_medicament add constraint fk_substance_medicament_substance_01 foreign key (substance_id) references substance (id) on delete restrict on update restrict;

alter table substance_medicament add constraint fk_substance_medicament_medicament_02 foreign key (medicament_id) references medicament (id) on delete restrict on update restrict;

alter table substance_classe_pharmaco add constraint fk_substance_classe_pharmaco_substance_01 foreign key (substance_id) references substance (id) on delete restrict on update restrict;

alter table substance_classe_pharmaco add constraint fk_substance_classe_pharmaco_classe_pharmaco_02 foreign key (classe_pharmaco_code) references classe_pharmaco (code) on delete restrict on update restrict;

alter table substance_classe_chimique add constraint fk_substance_classe_chimique_substance_01 foreign key (substance_id) references substance (id) on delete restrict on update restrict;

alter table substance_classe_chimique add constraint fk_substance_classe_chimique_classe_chimique_02 foreign key (classe_chimique_code) references classe_chimique (code) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table classe_chimique;

drop table substance_classe_chimique;

drop table effet_indesirable_classe_chimique;

drop table hierarchie_classe_chimique;

drop table classe_pharmaco;

drop table substance_classe_pharmaco;

drop table effet_indesirable_classe_pharmaco;

drop table hierarchie_classe_pharmaco;

drop table conservateur;

drop table conservateur_produit_cosmetique;

drop table dispo_medical;

drop table effet_indesirable_dispo_medical;

drop table effet_indesirable;

drop table effet_indesirable_conservateur;

drop table effet_indesirable_excipient;

drop table effet_indesirable_parabene;

drop table effet_indesirable_medicament;

drop table effet_indesirable_produit_cosmetique;

drop table effet_indesirable_principe_actif;

drop table hierarchie_effet_indesirable;

drop table excipient;

drop table excipient_produit_cosmetique;

drop table medicament;

drop table substance_medicament;

drop table parabene;

drop table parabene_produit_cosmetique;

drop table principe_actif;

drop table principe_actif_produit_cosmetique;

drop table produit_cosmetique;

drop table substance;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

