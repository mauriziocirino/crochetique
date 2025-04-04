-- Selezione ordinata su un attributo di una tabella con condizioni AND e OR
SELECT * FROM Articolo;
SELECT * 
FROM Articolo 
WHERE price > 40 AND (dimensione = 'M' OR dimensione = 'L') 
ORDER BY price DESC;


-- Selezione su due o più tabelle con condizione
SELECT u.username, a.descrizione, ao.quantity 
FROM Utente u
JOIN Ordine o ON u.id = o.idUtente
JOIN articoloOrdine ao ON o.id = ao.idOrdine
JOIN Articolo a ON ao.idArticolo = a.id;

-- Selezione aggregata su tutti i valore (somma totale delle vendite di una articolo)
SELECT aOrd.idArticolo, SUM(totPrezzo) AS totaleVendite FROM articoloOrdine aOrd group by aOrd.idArticolo;


-- Selezione aggregata su raggruppamenti con condizione (numero dei pezzi acquistati di un articolo con numAcquisti > 2)
SELECT a.id, a.descrizione, SUM(aOrd.quantity) AS totaleVenduto 
FROM articoloOrdine aOrd
JOIN Articolo a ON aOrd.idArticolo = a.id
GROUP BY a.id
HAVING totaleVenduto > 2;

-- Selezione aggregata su raggruppamenti con condizione che includano un'altra funzione di raggruppamento (catalogo  con il numero massimo di articoli venduti)

DROP VIEW IF EXISTS vista1;
CREATE VIEW vista1 AS
    SELECT a.idCatalogo AS catalogo, SUM(aOrd.quantity) AS totaleVenduto
    FROM articoloOrdine aOrd
    JOIN Articolo a ON aOrd.idArticolo = a.id
    GROUP BY a.idCatalogo;

SELECT c.id, c.nome, v.totaleVenduto
FROM Catalogo c, vista1 v
WHERE c.id = v.catalogo
  AND v.totaleVenduto = (SELECT MAX(v1.totaleVenduto)
                         FROM vista1 v1);

-- Selezione con operazioni insiemistiche (l'utente che non ha mai effettuato un ordine)
SELECT u.username 
FROM Utente u 
WHERE u.id NOT IN (SELECT o.idUtente FROM Ordine o WHERE o.idUtente);



-- Selezione con l'uso appropriato della divisione (l'utente che ha acquistato tutti gli articoli)
SELECT u.username 
FROM Utente u
WHERE NOT EXISTS (
    SELECT * 
    FROM Articolo a
    WHERE NOT EXISTS (
        SELECT * 
        FROM articoloOrdine aOrd
        WHERE aOrd.idArticolo = a.id
        AND aOrd.idOrdine IN (
            SELECT o.id
            FROM Ordine o
            WHERE o.idUtente = u.id
        )
    )
);



