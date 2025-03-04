# crochetique
La proprietaria del marchio 'Crochetique' vuole gestire gli ordini per il suo ecommerce per le sue borsette fatte a mano.
Si vuole tener conto di cataloghi stagionali definiti da un codice univoco, un nome, una breve descrizione, la stagione e l’anno del catalogo.
L’articolo è definito da un ID, una dimensione (S, M, L), una descrizione, una foto e il numero di volte acquistato (attributo derivabile). Gli articoli possono essere delle borse,
caratterizzate dalla presenza di imbottitura interna e appartenenti a un modello, oppure degli accessori, che possono essere indossabili o non indossabili.
Ogni borsa si basa su un modello, contraddistinto da un ID, un nome e un tipo di chiusura.
Ogni articolo è realizzato con un tessuto identificato dal suo codice, il materiale e il colore.
Dei clienti si memorizzano username, password, email, nome, cognome e fino a due metodi di pagamento (numero di carta e data di scadenza - attributo multivalore).
Ogni cliente può effettuare svariati ordini, ciascuno associato a un ID ordine, uno stato (es. "In attesa", "Spedito", "Consegnato")
e un indirizzo di spedizione (attributo composto: via, civico, CAP, stato).
Gli ordini contengono uno o più articoli e vengono gestiti attraverso l’entità Articolo_Ordine, che tiene traccia della quantità acquistata e del prezzo al momento dell’acquisto.
Il prezzo dei prodotti può variare in base a offerte e sconti, che possono essere applicati a un articolo specifico o a un ordine intero.
