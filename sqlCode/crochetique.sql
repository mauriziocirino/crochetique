DROP DATABASE IF EXISTS Crochetique;

CREATE DATABASE IF NOT EXISTS Crochetique;
use Crochetique;

-- tabella cliente
CREATE TABLE Cliente (
	id int auto_increment primary key,
    username varchar(50) not null unique,
    pass varchar(255) not null,
    ruolo enum('admin', 'user') not null,
    email varchar(100) not null unique,
    dataCreazione timestamp default current_timestamp
);


-- tabella pagamento
CREATE TABLE Pagamento(
	numCarta varchar(19) primary key,
    nomeIntestatario varchar(30) not null,
    cognomeIntestatario varchar(30) not null,
    meseScadenza tinyint not null,
    annoScadenza YEAR not null
   -- CHECK (annoScadenza > YEAR(curdate()) or annoScadenza=YEAR(CURDATE()) and meseScadenza>MONTH(curdate()))
);

-- tabella catalogo
CREATE TABLE Catalogo(
	id int auto_increment primary key,
    nome varchar(30) not null,
    stagione varchar(30) not null,
    sconto int default 0,
    CHECK (sconto between 0 and 100)
);

-- tabella articolo 
CREATE TABLE Articolo(
	id int auto_increment primary key,
    descrizione varchar(100) not null, 
    numAcquisti int default 0,
    dimensione enum('XS','S', 'M', 'L', 'XL'),
	price decimal(9,2) not null
);




-- tabella Borsa
CREATE TABLE Borsa(
	id int primary key,
    imbottitura varchar(30),
    FOREIGN KEY (id) REFERENCES Articolo(id)    
);

-- tabella Accessorio
CREATE TABLE Accessorio(
	id int primary key,
    indossabile boolean default 0,
    FOREIGN KEY (id) REFERENCES Articolo(id)    
);

-- tabella modello
CREATE TABLE Modello(
	id int auto_increment primary key,
    chiusura enum('Cerniera', 'Clutch', 'Strap', 'Bottone', 'Nessuna') default 'Nessuna',
    idArticolo int REFERENCES Articolo(id)
);

-- tabella tessuto + colore per non avere doppioni di colori(possono essere su tessuti diversi)
CREATE TABLE Tessuto (
    id int auto_increment primary key,
    materiale varchar(50) not null,
    idColore char(6) REFERENCES Colore(id)
);
CREATE TABLE Colore(
	id int auto_increment primary key,
    nome varchar(30) NOT NULL UNIQUE,
    codiceHEX char(6) CHECK (codiceHEX REGEXP('^#[0-9A-Fa-F]{6}$'))
);

-- ordine
CREATE TABLE Ordine (
	id int auto_increment primary key,
    idCliente int references Cliente(id) on delete cascade,
    sconto int default 0,
    CHECK (sconto BETWEEN 0 AND 99),
    via varchar(30) not null,
    civico varchar(6) not null,
    CAP varchar(5) not null
);

-- tabella articoloOrdine
CREATE TABLE articoloOrdine(
	idArticolo int REFERENCES Articolo(id) on delete cascade,
    idOrdine int REFERENCES Ordine(id) on delete cascade,
    quantity int default 1 not null,
    prezzoTot decimal(10,2) default 0.00,
    primary key (idArticolo, idOrdine)
);
-- trigger per auto update numAcquisti
DELIMITER $$

CREATE TRIGGER aggiornaNumAcquisti
AFTER INSERT ON articoloOrdine
FOR EACH ROW
BEGIN
    UPDATE Articolo
    SET numAcquisti = (
        SELECT COALESCE(SUM(quantita), 0) --  la funzione COALESCE restituisce il primo elemento non NULL
        FROM articoloOrdine
        WHERE idArticolo= NEW.idArticolo
    )
    WHERE id = NEW.idArticolo;
END$$

DELIMITER ;

-- trigger update prezzo totale  (x cami : l'ho creato ma non sò nemmeno se il prof l'accetta in questo modo o lo vuole diversamente)
DELIMITER $$

CREATE TRIGGER aggiorna_prezzoTot
BEFORE INSERT ON articoloOrdine
FOR EACH ROW
BEGIN
    DECLARE prezzoUnitario decimal(10,2);
    
    -- Recupera il prezzo dell'articolo
    SELECT prezzo INTO prezzoUnitario
    FROM Articolo
    WHERE id = NEW.idArticolo;
    
    -- Calcola il prezzo totale
    SET NEW.prezzoTot = NEW.quantity * prezzoUnitario;
END$$

DELIMITER ;
