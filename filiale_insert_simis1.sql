-- ============================================================
-- Datei: filiale_insert_simis1.sql
-- Zweck: 5 Premium-Filialen fuer Tabelle FILIALE (Moebelhaus)
-- Format: Einzelne INSERT INTO ... VALUES ...; (wie deine anderen Dateien)
-- Hinweis: Keine Umlaute und kein ss-Sonderzeichen.
-- OEFFNUNGSZEITEN ist DATE (hier als Eroeffnungsdatum genutzt).
-- ============================================================

-- Optional: Tabelle leeren (nur ausfuehren, wenn gewuenscht)
-- DELETE FROM FILIALE;
-- COMMIT;

INSERT INTO FILIALE (FILIALEID, KUNDEID, NAME, ADRESSE, PLZ, ORT, TELEFON, OEFFNUNGSZEITEN, MENGE_VERFUEGBAR)
VALUES (1, NULL, 'Showroom Berlin Mitte', 'Friedrichstr 120', '10117', 'Berlin', '493020001001', TO_DATE('2019-03-15','YYYY-MM-DD'), 3200);

INSERT INTO FILIALE (FILIALEID, KUNDEID, NAME, ADRESSE, PLZ, ORT, TELEFON, OEFFNUNGSZEITEN, MENGE_VERFUEGBAR)
VALUES (2, NULL, 'Designhaus Hamburg HafenCity', 'Am Sandtorkai 58', '20457', 'Hamburg', '494030002002', TO_DATE('2020-09-01','YYYY-MM-DD'), 2800);

INSERT INTO FILIALE (FILIALEID, KUNDEID, NAME, ADRESSE, PLZ, ORT, TELEFON, OEFFNUNGSZEITEN, MENGE_VERFUEGBAR)
VALUES (3, NULL, 'Galerie Muenchen Schwabing', 'Leopoldstr 80', '80802', 'Muenchen', '498940003003', TO_DATE('2018-11-10','YYYY-MM-DD'), 3500);

INSERT INTO FILIALE (FILIALEID, KUNDEID, NAME, ADRESSE, PLZ, ORT, TELEFON, OEFFNUNGSZEITEN, MENGE_VERFUEGBAR)
VALUES (4, NULL, 'Wohnatelier Frankfurt Westend', 'Bockenheimer Landstr 42', '60323', 'Frankfurt am Main', '496950004004', TO_DATE('2021-05-20','YYYY-MM-DD'), 2600);

INSERT INTO FILIALE (FILIALEID, KUNDEID, NAME, ADRESSE, PLZ, ORT, TELEFON, OEFFNUNGSZEITEN, MENGE_VERFUEGBAR)
VALUES (5, NULL, 'Studio Duesseldorf Koenigsallee', 'Koenigsallee 92', '40212', 'Duesseldorf', '4921160005005', TO_DATE('2017-08-05','YYYY-MM-DD'), 2400);

COMMIT;
