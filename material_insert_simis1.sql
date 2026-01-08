-- ============================================================
-- Datei: material_insert_simis1.sql
-- Zweck: Beispieldaten fuer Tabelle MATERIAL (Premium-Moebelhaus)
-- Hinweis: Keine Sonderzeichen (keine Umlaute/ss).
-- ============================================================

-- Optional: Tabelle leeren (nur ausfuehren, wenn gewuenscht)
-- DELETE FROM MATERIAL;
-- COMMIT;

INSERT ALL
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (1,  'Eiche Massiv', 'Eiche massiv, geoe lt oder lackiert, sehr robust und langlebig.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (2,  'Nussbaum Massiv', 'Nussbaum massiv, warme Maserung, hochwertiger Look fuer Premiummoebel.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (3,  'Buche Massiv', 'Buche massiv, hartes Holz, gute Formstabilitaet fuer Stuehle und Tische.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (4,  'Esche Massiv', 'Esche massiv, elastisch und belastbar, moderne helle Maserung.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (5,  'Teak Massiv', 'Teak massiv, hohe Wetterbestaendigkeit, ideal fuer Outdoor-Moebel.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (6,  'Eiche Furnier', 'Echtholzfurnier Eiche auf Traegerplatte, hochwertige Optik bei geringem Gewicht.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (7,  'Nussbaum Furnier', 'Echtholzfurnier Nussbaum, edle Optik fuer Sideboards und Regalsysteme.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (8,  'MDF Lack matt', 'MDF mit matter Lackierung, gleichmaessige Oberflaeche, leicht zu reinigen.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (9,  'MDF Lack hochglanz', 'MDF mit Hochglanzlack, sehr elegante Optik, pflegeintensiver als matt.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (10, 'Keramik Platte', 'Keramikoberflaeche, kratzfest und hitzebestaendig, ideal fuer Tischplatten.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (11, 'Marmor Carrara', 'Naturstein Marmor, klassische Premiumoptik, jede Platte ein Unikat.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (12, 'Travertin', 'Naturstein Travertin, warme Beige-Toene, fuer Konsolen und Beistelltische.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (13, 'Edelstahl gebuerstet', 'Gebuersteter Edelstahl, sehr hochwertig, korrosionsbestaendig, modern.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (14, 'Stahl pulverbeschichtet', 'Stahl mit Pulverbeschichtung, strapazierfaehig, viele Farbtoene moeglich.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (15, 'Aluminium pulverbeschichtet', 'Leicht und stabil, ideal fuer Outdoor und Gestelle, pflegeleicht.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (16, 'Messing Finish', 'Messingoptik fuer Akzentmoebel und Leuchten, edler warmer Glanz.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (17, 'Vollnarbenleder', 'Sehr hochwertiges Leder, natuerliche Struktur, langlebig und angenehm im Griff.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (18, 'Anilinleder', 'Offenporiges Premiumleder, sehr weich, entwickelt eine edle Patina.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (19, 'Semianilinleder', 'Premiumleder mit leichtem Schutz, gute Balance aus Look und Pflegeleichtigkeit.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (20, 'Velours Stoff', 'Samtiger Veloursstoff, weich und elegant, fuer Polsterbetten und Sessel.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (21, 'Boucle Stoff', 'Strukturstoff Boucle, trendig und hochwertig, sehr wohnlich.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (22, 'Leinen Mix', 'Leinenmischgewebe, atmungsaktiv, natuerliche Optik, hochwertiges Wohngefuehl.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (23, 'Wolle', 'Wolltextil fuer Teppiche und Polster, warm, strapazierfaehig, premium.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (24, 'Sicherheitsglas', 'Gehartetes Sicherheitsglas, stabil und sicher, fuer Vitrinen und Tische.')
  INTO MATERIAL (MATERIALID, BEZEICHNUNG, BESCHREIBUNG) VALUES (25, 'Rope Geflecht', 'Outdoor Rope-Geflecht, wetterfest und formstabil, fuer Sessel und Lounge.')
SELECT 1 FROM DUAL;

COMMIT;
