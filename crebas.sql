/*==============================================================*/
/* DBMS name:      ORACLE Version 19c                           */
/* Created on:     03.12.2025 15:21:53                          */
/*==============================================================*/


alter table BESTELLUNG
   drop constraint FK_BESTELLU_FILIALE_B_FILIALE;

alter table BEWERTUNG
   drop constraint FK_BEWERTUN_KUNDE_BEW_KUNDE;

alter table FILIALE
   drop constraint FK_FILIALE_KUNDE_BES_KUNDE;

alter table LAGER
   drop constraint FK_LAGER_BESTELLUN_BESTELLU;

alter table LAGERBEWEGUNG
   drop constraint FK_LAGERBEW_LAGERBEWE_LAGER;

alter table LAGERBEWEGUNG
   drop constraint FK_LAGERBEW_RELATIONS_FILIALE;

alter table LIEFERANT_PRODUKT
   drop constraint FK_LIEFERAN_LIEFERT_PRODUKT;

alter table LIEFERANT_PRODUKT
   drop constraint "FK_LIEFERAN_WIRD GELI_LIEFERAN";

alter table PREIS_HISTORIE
   drop constraint FK_PREIS_HI_PRODUKT_P_PRODUKT;

alter table PRODUKT
   drop constraint FK_PRODUKT_BESTELLUN_BESTELLU;

alter table PRODUKT
   drop constraint FK_PRODUKT_BEWERTUNG_BEWERTUN;

alter table PRODUKT
   drop constraint FK_PRODUKT_PRODUKTKA_PRODUKTK;

alter table PRODUKT
   drop constraint FK_PRODUKT_PRODUKT_A_RABATT;

alter table PRODUKT
   drop constraint FK_PRODUKT_PRODUKT_L_LAGER;

alter table PRODUKTVARIANTE
   drop constraint FK_PRODUKTV_FARBE_PRO_FARBE;

alter table PRODUKTVARIANTE
   drop constraint FK_PRODUKTV_GROESSE_P_GROE_E;

alter table PRODUKTVARIANTE
   drop constraint FK_PRODUKTV_PRODUKT_P_PRODUKT;

alter table PRODUKTVARIANTE
   drop constraint FK_PRODUKTV_RELATIONS_MATERIAL;

alter table RUECKGABE
   drop constraint FK_RUECKGAB_KUNDE_RUE_KUNDE;

alter table RUECKGABE
   drop constraint FK_RUECKGAB_RELATIONS_LAGER;

alter table RUECKGABE
   drop constraint FK_RUECKGAB_VERKAUF_R_VERKAUF;

alter table VERKAUF
   drop constraint FK_VERKAUF_FILIALE_V_FILIALE;

alter table VERKAUF
   drop constraint FK_VERKAUF_VERKAUF_K_KUNDE;

alter table VERKAUF
   drop constraint FK_VERKAUF_ZAHLUNGSA_ZAHLUNGS;

drop index FILIALE_BESTELLUNG_FK;

drop table BESTELLUNG cascade constraints;

drop index KUNDE_BEWERTUNG_FK;

drop table BEWERTUNG cascade constraints;

drop table FARBE cascade constraints;

drop index KUNDE_BESTELLUNG_FK;

drop table FILIALE cascade constraints;

drop table GROE_E cascade constraints;

drop table KUNDE cascade constraints;

drop index BESTELLUNG_LAGERBESTAND_FK;

drop table LAGER cascade constraints;

drop index RELATIONSHIP_27_FK;

drop index LAGERBEWEGUNG_LAGERBESTAND_FK;

drop table LAGERBEWEGUNG cascade constraints;

drop table LIEFERANT cascade constraints;

drop index LIEFERT_FK;

drop index WIRD_GELIEFERT_VON_FK;

drop table LIEFERANT_PRODUKT cascade constraints;

drop table MATERIAL cascade constraints;

drop index PRODUKT_PREIS_HISTORY_FK;

drop table PREIS_HISTORIE cascade constraints;

drop index PRODUKT_LAGER_FK;

drop index BESTELLUNG_PRODUKT_FK;

drop index BEWERTUNG_PRODUKT_FK;

drop index PRODUKT_AKTION_FK;

drop index PRODUKTKATEGORIE_PRODUKT_FK;

drop table PRODUKT cascade constraints;

drop table PRODUKTKATEGORIE cascade constraints;

drop index GROESSE_PRODUKT_FK;

drop index RELATIONSHIP_9_FK;

drop index FARBE_PRODUKT_KONFIGURATION_FK;

drop index PRODUKT_PRODUKTVARIANTE_FK;

drop table PRODUKTVARIANTE cascade constraints;

drop table RABATT cascade constraints;

drop index RELATIONSHIP_26_FK;

drop index VERKAUF_RUECKGABE_FK;

drop index KUNDE_RUECKGABE_FK;

drop table RUECKGABE cascade constraints;

drop index ZAHLUNGSART_VERKAUF_FK;

drop index VERKAUF_KUNDE_FK;

drop index FILIALE_VERKAUF_FK;

drop table VERKAUF cascade constraints;

drop table ZAHLUNGSART cascade constraints;

/*==============================================================*/
/* Table: BESTELLUNG                                            */
/*==============================================================*/
create table BESTELLUNG (
   BESTELLUNGID         INTEGER               not null,
   FILIALEID            INTEGER,
   DATUM_BESTELLUNG     INTEGER               not null,
   GESAMTBETRAG         INTEGER               not null,
   MENGE                INTEGER               not null,
   STATUS               SMALLINT              not null,
   constraint PK_BESTELLUNG primary key (BESTELLUNGID)
);

/*==============================================================*/
/* Index: FILIALE_BESTELLUNG_FK                                 */
/*==============================================================*/
create index FILIALE_BESTELLUNG_FK on BESTELLUNG (
   FILIALEID ASC
);

/*==============================================================*/
/* Table: BEWERTUNG                                             */
/*==============================================================*/
create table BEWERTUNG (
   BEWERTUNGSID         INTEGER               not null,
   KUNDEID              INTEGER,
   DATUM                DATE                  not null,
   STERNE               INTEGER               not null,
   KOMMENTAR            VARCHAR2(1000),
   constraint PK_BEWERTUNG primary key (BEWERTUNGSID)
);

/*==============================================================*/
/* Index: KUNDE_BEWERTUNG_FK                                    */
/*==============================================================*/
create index KUNDE_BEWERTUNG_FK on BEWERTUNG (
   KUNDEID ASC
);

/*==============================================================*/
/* Table: FARBE                                                 */
/*==============================================================*/
create table FARBE (
   FARBENID             INTEGER               not null,
   BEZEICHNUNG          VARCHAR2(50),
   HEXCODE              CHAR(6),
   constraint PK_FARBE primary key (FARBENID)
);

/*==============================================================*/
/* Table: FILIALE                                               */
/*==============================================================*/
create table FILIALE (
   FILIALEID            INTEGER               not null,
   KUNDEID              INTEGER,
   NAME                 VARCHAR2(50)          not null,
   ADRESSE              VARCHAR2(50)          not null,
   PLZ                  INTEGER               not null,
   ORT                  VARCHAR2(50)          not null,
   TELEFON              INTEGER               not null,
   OEFFNUNGSZEITEN      DATE,
   MENGE_VERFUEGBAR     INTEGER,
   constraint PK_FILIALE primary key (FILIALEID)
);

/*==============================================================*/
/* Index: KUNDE_BESTELLUNG_FK                                   */
/*==============================================================*/
create index KUNDE_BESTELLUNG_FK on FILIALE (
   KUNDEID ASC
);

/*==============================================================*/
/* Table: GROE_E                                                */
/*==============================================================*/
create table GROE_E (
   GROESSEID            INTEGER               not null,
   BREITE_CM            INTEGER               not null,
   HOEHE_CM             INTEGER               not null,
   TIEFE_CM             INTEGER               not null,
   BESCHREIBUNG         VARCHAR2(1000),
   constraint PK_GROE_E primary key (GROESSEID)
);

/*==============================================================*/
/* Table: KUNDE                                                 */
/*==============================================================*/
create table KUNDE (
   KUNDEID              INTEGER               not null,
   VORNAME              VARCHAR2(50)          not null,
   NACHNAME             VARCHAR2(50)          not null,
   EMAIL                VARCHAR2(50)          not null,
   ADRESSE              VARCHAR2(100)         not null,
   PLZ                  INTEGER               not null,
   ORT                  VARCHAR2(50)          not null,
   LAND                 VARCHAR2(50)          not null,
   REGESTRIERT          SMALLINT              not null,
   PASSWORT             VARCHAR2(20)          not null,
   TELEFONNUMMER        INTEGER,
   constraint PK_KUNDE primary key (KUNDEID)
);

/*==============================================================*/
/* Table: LAGER                                                 */
/*==============================================================*/
create table LAGER (
   LAGERID              INTEGER               not null,
   BESTELLUNGID         INTEGER,
   MENGE_VERFUEGBAR     INTEGER               not null,
   constraint PK_LAGER primary key (LAGERID)
);

/*==============================================================*/
/* Index: BESTELLUNG_LAGERBESTAND_FK                            */
/*==============================================================*/
create index BESTELLUNG_LAGERBESTAND_FK on LAGER (
   BESTELLUNGID ASC
);

/*==============================================================*/
/* Table: LAGERBEWEGUNG                                         */
/*==============================================================*/
create table LAGERBEWEGUNG (
   BEWEGUNGID           INTEGER               not null,
   LAGERID              INTEGER               not null,
   FILIALEID            INTEGER,
   DATUM                DATE                  not null,
   TRANSPORT            SMALLINT              not null,
   MENGE                INTEGER               not null,
   BEMERKUNG            VARCHAR2(100),
   constraint PK_LAGERBEWEGUNG primary key (BEWEGUNGID)
);

/*==============================================================*/
/* Index: LAGERBEWEGUNG_LAGERBESTAND_FK                         */
/*==============================================================*/
create index LAGERBEWEGUNG_LAGERBESTAND_FK on LAGERBEWEGUNG (
   LAGERID ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_27_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_27_FK on LAGERBEWEGUNG (
   FILIALEID ASC
);

/*==============================================================*/
/* Table: LIEFERANT                                             */
/*==============================================================*/
create table LIEFERANT (
   LIEFERANTID          INTEGER               not null,
   NAME                 VARCHAR2(50)          not null,
   EMAIL                VARCHAR2(50)          not null,
   ADRESSE              VARCHAR2(100)         not null,
   TELEFON              INTEGER,
   KONTAKTPERSON        VARCHAR2(50),
   constraint PK_LIEFERANT primary key (LIEFERANTID)
);

/*==============================================================*/
/* Table: LIEFERANT_PRODUKT                                     */
/*==============================================================*/
create table LIEFERANT_PRODUKT (
   LIEFERANTID          INTEGER               not null,
   PRODUKTID            INTEGER               not null,
   constraint PK_LIEFERANT_PRODUKT primary key (LIEFERANTID, PRODUKTID)
);

/*==============================================================*/
/* Index: WIRD_GELIEFERT_VON_FK                                 */
/*==============================================================*/
create index WIRD_GELIEFERT_VON_FK on LIEFERANT_PRODUKT (
   LIEFERANTID ASC
);

/*==============================================================*/
/* Index: LIEFERT_FK                                            */
/*==============================================================*/
create index LIEFERT_FK on LIEFERANT_PRODUKT (
   PRODUKTID ASC
);

/*==============================================================*/
/* Table: MATERIAL                                              */
/*==============================================================*/
create table MATERIAL (
   MATERIALID           INTEGER               not null,
   LIEFERANTID          INTEGER               not null,
   BEZEICHNUNG          VARCHAR2(50),
   BESCHREIBUNG         VARCHAR2(1000),
   constraint PK_MATERIAL primary key (MATERIALID)
);

/*==============================================================*/
/* Table: PREIS_HISTORIE                                        */
/*==============================================================*/
create table PREIS_HISTORIE (
   PREISID              INTEGER               not null,
   PRODUKTID            INTEGER               not null,
   ALTER_PREIS          FLOAT(10)             not null,
   NEUER_PREIS          FLOAT(10),
   DATUM                DATE,
   constraint PK_PREIS_HISTORIE primary key (PREISID)
);

/*==============================================================*/
/* Index: PRODUKT_PREIS_HISTORY_FK                              */
/*==============================================================*/
create index PRODUKT_PREIS_HISTORY_FK on PREIS_HISTORIE (
   PRODUKTID ASC
);

/*==============================================================*/
/* Table: PRODUKT                                               */
/*==============================================================*/
create table PRODUKT (
   PRODUKTID            INTEGER               not null,
   AKTIONID             INTEGER,
   BESTELLUNGID         INTEGER,
   BEWERTUNGSID         INTEGER,
   LAGERID              INTEGER,
   KATEGORIEID          INTEGER               not null,
   AKTIV                SMALLINT              not null,
   NAME                 VARCHAR2(50),
   BASISPREIS           FLOAT(10),
   BESCHREIBUNG         VARCHAR2(1000),
   constraint PK_PRODUKT primary key (PRODUKTID)
);

/*==============================================================*/
/* Index: PRODUKTKATEGORIE_PRODUKT_FK                           */
/*==============================================================*/
create index PRODUKTKATEGORIE_PRODUKT_FK on PRODUKT (
   KATEGORIEID ASC
);

/*==============================================================*/
/* Index: PRODUKT_AKTION_FK                                     */
/*==============================================================*/
create index PRODUKT_AKTION_FK on PRODUKT (
   AKTIONID ASC
);

/*==============================================================*/
/* Index: BEWERTUNG_PRODUKT_FK                                  */
/*==============================================================*/
create index BEWERTUNG_PRODUKT_FK on PRODUKT (
   BEWERTUNGSID ASC
);

/*==============================================================*/
/* Index: BESTELLUNG_PRODUKT_FK                                 */
/*==============================================================*/
create index BESTELLUNG_PRODUKT_FK on PRODUKT (
   BESTELLUNGID ASC
);

/*==============================================================*/
/* Index: PRODUKT_LAGER_FK                                      */
/*==============================================================*/
create index PRODUKT_LAGER_FK on PRODUKT (
   LAGERID ASC
);

/*==============================================================*/
/* Table: PRODUKTKATEGORIE                                      */
/*==============================================================*/
create table PRODUKTKATEGORIE (
   KATEGORIEID          INTEGER               not null,
   KATEGORIENNAME       VARCHAR2(50),
   BESCHREIBUNG         VARCHAR2(1000),
   constraint PK_PRODUKTKATEGORIE primary key (KATEGORIEID)
);

/*==============================================================*/
/* Table: PRODUKTVARIANTE                                       */
/*==============================================================*/
create table PRODUKTVARIANTE (
   VARIANTEID           INTEGER               not null,
   FARBENID             INTEGER               not null,
   GROESSEID            INTEGER               not null,
   PRODUKTID            INTEGER               not null,
   MATERIALID           INTEGER               not null,
   PREIS                FLOAT                 not null,
   VERFUEGBAR           SMALLINT              not null,
   constraint PK_PRODUKTVARIANTE primary key (VARIANTEID)
);

/*==============================================================*/
/* Index: PRODUKT_PRODUKTVARIANTE_FK                            */
/*==============================================================*/
create index PRODUKT_PRODUKTVARIANTE_FK on PRODUKTVARIANTE (
   PRODUKTID ASC
);

/*==============================================================*/
/* Index: FARBE_PRODUKT_KONFIGURATION_FK                        */
/*==============================================================*/
create index FARBE_PRODUKT_KONFIGURATION_FK on PRODUKTVARIANTE (
   FARBENID ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_9_FK                                     */
/*==============================================================*/
create index RELATIONSHIP_9_FK on PRODUKTVARIANTE (
   MATERIALID ASC
);

/*==============================================================*/
/* Index: GROESSE_PRODUKT_FK                                    */
/*==============================================================*/
create index GROESSE_PRODUKT_FK on PRODUKTVARIANTE (
   GROESSEID ASC
);

/*==============================================================*/
/* Table: RABATT                                                */
/*==============================================================*/
create table RABATT (
   AKTIONID             INTEGER               not null,
   RAPATT_PROZENT       FLOAT(3)              not null,
   "START"              DATE                  not null,
   END                  DATE,
   BEZEICHNUG           VARCHAR2(50),
   constraint PK_RABATT primary key (AKTIONID)
);

/*==============================================================*/
/* Table: RUECKGABE                                             */
/*==============================================================*/
create table RUECKGABE (
   REUCKGABEID          INTEGER               not null,
   LAGERID              INTEGER,
   VERKAUFID            INTEGER,
   KUNDEID              INTEGER,
   DATUM                DATE                  not null,
   GRUND                VARCHAR2(1000)        not null,
   BETRAG_ERSTATTUNG    FLOAT(10)             not null,
   constraint PK_RUECKGABE primary key (REUCKGABEID)
);

/*==============================================================*/
/* Index: KUNDE_RUECKGABE_FK                                    */
/*==============================================================*/
create index KUNDE_RUECKGABE_FK on RUECKGABE (
   KUNDEID ASC
);

/*==============================================================*/
/* Index: VERKAUF_RUECKGABE_FK                                  */
/*==============================================================*/
create index VERKAUF_RUECKGABE_FK on RUECKGABE (
   VERKAUFID ASC
);

/*==============================================================*/
/* Index: RELATIONSHIP_26_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_26_FK on RUECKGABE (
   LAGERID ASC
);

/*==============================================================*/
/* Table: VERKAUF                                               */
/*==============================================================*/
create table VERKAUF (
   VERKAUFID            INTEGER               not null,
   ZAHLUNGSARTID        INTEGER               not null,
   FILIALEID            INTEGER,
   KUNDEID              INTEGER,
   ANZAHL               INTEGER,
   GESAMTPREIS          FLOAT(10),
   DATUM                DATE,
   ZAHLUNGSTATUS        SMALLINT,
   BETRAG               FLOAT(9),
   RECHNUNGSDATUM       DATE,
   constraint PK_VERKAUF primary key (VERKAUFID)
);

/*==============================================================*/
/* Index: FILIALE_VERKAUF_FK                                    */
/*==============================================================*/
create index FILIALE_VERKAUF_FK on VERKAUF (
   FILIALEID ASC
);

/*==============================================================*/
/* Index: VERKAUF_KUNDE_FK                                      */
/*==============================================================*/
create index VERKAUF_KUNDE_FK on VERKAUF (
   KUNDEID ASC
);

/*==============================================================*/
/* Index: ZAHLUNGSART_VERKAUF_FK                                */
/*==============================================================*/
create index ZAHLUNGSART_VERKAUF_FK on VERKAUF (
   ZAHLUNGSARTID ASC
);

/*==============================================================*/
/* Table: ZAHLUNGSART                                           */
/*==============================================================*/
create table ZAHLUNGSART (
   ZAHLUNGSARTID        INTEGER               not null,
   BEZEICHNUNG          VARCHAR2(50)          not null,
   constraint PK_ZAHLUNGSART primary key (ZAHLUNGSARTID)
);

alter table BESTELLUNG
   add constraint FK_BESTELLU_FILIALE_B_FILIALE foreign key (FILIALEID)
      references FILIALE (FILIALEID);

alter table BEWERTUNG
   add constraint FK_BEWERTUN_KUNDE_BEW_KUNDE foreign key (KUNDEID)
      references KUNDE (KUNDEID);

alter table FILIALE
   add constraint FK_FILIALE_KUNDE_BES_KUNDE foreign key (KUNDEID)
      references KUNDE (KUNDEID);

alter table LAGER
   add constraint FK_LAGER_BESTELLUN_BESTELLU foreign key (BESTELLUNGID)
      references BESTELLUNG (BESTELLUNGID);

alter table LAGERBEWEGUNG
   add constraint FK_LAGERBEW_LAGERBEWE_LAGER foreign key (LAGERID)
      references LAGER (LAGERID);

alter table LAGERBEWEGUNG
   add constraint FK_LAGERBEW_RELATIONS_FILIALE foreign key (FILIALEID)
      references FILIALE (FILIALEID);

alter table LIEFERANT_PRODUKT
   add constraint FK_LIEFERAN_LIEFERT_PRODUKT foreign key (PRODUKTID)
      references PRODUKT (PRODUKTID);

alter table LIEFERANT_PRODUKT
   add constraint "FK_LIEFERAN_WIRD GELI_LIEFERAN" foreign key (LIEFERANTID)
      references LIEFERANT (LIEFERANTID);

alter table PREIS_HISTORIE
   add constraint FK_PREIS_HI_PRODUKT_P_PRODUKT foreign key (PRODUKTID)
      references PRODUKT (PRODUKTID);

alter table PRODUKT
   add constraint FK_PRODUKT_BESTELLUN_BESTELLU foreign key (BESTELLUNGID)
      references BESTELLUNG (BESTELLUNGID);

alter table PRODUKT
   add constraint FK_PRODUKT_BEWERTUNG_BEWERTUN foreign key (BEWERTUNGSID)
      references BEWERTUNG (BEWERTUNGSID);

alter table PRODUKT
   add constraint FK_PRODUKT_PRODUKTKA_PRODUKTK foreign key (KATEGORIEID)
      references PRODUKTKATEGORIE (KATEGORIEID);

alter table PRODUKT
   add constraint FK_PRODUKT_PRODUKT_A_RABATT foreign key (AKTIONID)
      references RABATT (AKTIONID);

alter table PRODUKT
   add constraint FK_PRODUKT_PRODUKT_L_LAGER foreign key (LAGERID)
      references LAGER (LAGERID);

alter table PRODUKTVARIANTE
   add constraint FK_PRODUKTV_FARBE_PRO_FARBE foreign key (FARBENID)
      references FARBE (FARBENID);

alter table PRODUKTVARIANTE
   add constraint FK_PRODUKTV_GROESSE_P_GROE_E foreign key (GROESSEID)
      references GROE_E (GROESSEID);

alter table PRODUKTVARIANTE
   add constraint FK_PRODUKTV_PRODUKT_P_PRODUKT foreign key (PRODUKTID)
      references PRODUKT (PRODUKTID);

alter table PRODUKTVARIANTE
   add constraint FK_PRODUKTV_RELATIONS_MATERIAL foreign key (MATERIALID)
      references MATERIAL (MATERIALID);

alter table RUECKGABE
   add constraint FK_RUECKGAB_KUNDE_RUE_KUNDE foreign key (KUNDEID)
      references KUNDE (KUNDEID);

alter table RUECKGABE
   add constraint FK_RUECKGAB_RELATIONS_LAGER foreign key (LAGERID)
      references LAGER (LAGERID);

alter table RUECKGABE
   add constraint FK_RUECKGAB_VERKAUF_R_VERKAUF foreign key (VERKAUFID)
      references VERKAUF (VERKAUFID);

alter table VERKAUF
   add constraint FK_VERKAUF_FILIALE_V_FILIALE foreign key (FILIALEID)
      references FILIALE (FILIALEID);

alter table VERKAUF
   add constraint FK_VERKAUF_VERKAUF_K_KUNDE foreign key (KUNDEID)
      references KUNDE (KUNDEID);

alter table VERKAUF
   add constraint FK_VERKAUF_ZAHLUNGSA_ZAHLUNGS foreign key (ZAHLUNGSARTID)
      references ZAHLUNGSART (ZAHLUNGSARTID);

