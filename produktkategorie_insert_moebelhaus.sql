-- Beispieldaten fuer Tabelle PRODUKTKATEGORIE (Moebelhaus / Einrichtung)
-- Struktur laut Skript_SIMIS1.sql:
--   KATEGORIEID     NUMBER(20,0)   NOT NULL (PK)
--   KATEGORIENNAME  VARCHAR2(50)
--   BESCHREIBUNG    VARCHAR2(1000)

INSERT ALL
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (1,  'Sofas & Sessel',          'Sofas, Ecksofas, Schlafsofas, Sessel und Relaxmoebel fuer Wohnzimmer und Lounge.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (2,  'Betten & Matratzen',      'Betten, Boxspringbetten, Lattenroste, Matratzen und Topper fuer erholsamen Schlaf.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (3,  'Tische',                  'Esstische, Couchtische, Beistelltische, Schreibtische und Konsolentische.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (4,  'Stuehle & Baenke',          'Esszimmerstuehle, Barhocker, Buerostuehle und Sitzbaenke in verschiedenen Materialien.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (5,  'Schraenke & Aufbewahrung', 'Kleiderschraenke, Sideboards, Kommoden, Regale und Aufbewahrungsloesungen.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (6,  'Wohnzimmermoebel',         'TV-Moebel, Wohnwaende, Vitrinen und Moebelsets fuer das Wohnzimmer.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (7,  'Schlafzimmermoebel',       'Nachttische, Kleiderschraenke, Kommoden und Schlafzimmer-Sets.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (8,  'Buero & Homeoffice',       'Schreibtische, Buerostuehle, Rollcontainer und Regale fuer produktives Arbeiten.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (9,  'Kueche & Esszimmer',       'Esszimmergruppen, Kuechenregale, Buffetschraenke und praktische Kuechenhelfer-Moebel.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (10, 'Lampen & Beleuchtung',    'Deckenleuchten, Stehlampen, Tischlampen und LED-Beleuchtung fuer jedes Zimmer.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (11, 'Garten & Balkon',         'Outdoor-Moebel, Loungesets, Gartentische, Gartenstuehle und wetterfeste Auflagen.')
  INTO PRODUKTKATEGORIE (KATEGORIEID, KATEGORIENNAME, BESCHREIBUNG) VALUES (12, 'Deko & Textilien',        'Teppiche, Vorhaenge, Kissen, Decken, Wanddeko und Wohnaccessoires.')
SELECT 1 FROM DUAL;

COMMIT;
