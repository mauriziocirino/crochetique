package mauriziocirino.crochetique.articolo;

public class Articolo {
    private String dimensione, descrizione, foto;
    private int id, numAcquisti, sconto, codiceTessuto, idCatalogo;

    public Articolo(){}
    public Articolo(int id,String dimensione, String descrizione, String foto, int numAcquisti, int sconto, int codiceTessuto, int idCatalogo) {
        this.id = id;
        this.dimensione = dimensione;
        this.descrizione = descrizione;
        this.foto = foto;
        this.numAcquisti = numAcquisti;
        this.sconto = sconto;
        this.codiceTessuto = codiceTessuto;
        this.idCatalogo = idCatalogo;
    }
    public Articolo(int id,String dimensione, String descrizione, String foto, int numAcquisti, int sconto, int codiceTessuto) {
        this.id = id;
        this.dimensione = dimensione;
        this.descrizione = descrizione;
        this.foto = foto;
        this.numAcquisti = numAcquisti;
        this.sconto = sconto;
        this.codiceTessuto = codiceTessuto;
    }
    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public int getCodiceTessuto() {
        return codiceTessuto;
    }

    public void setCodiceTessuto(int codiceTessuto) {
        this.codiceTessuto = codiceTessuto;
    }

    public String getDimensione() {
        return dimensione;
    }

    public void setDimensione(String dimensione) {
        this.dimensione = dimensione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public int getNumAcquisti() {
        return numAcquisti;
    }

    public void setNumAcquisti(int numAcquisti) {
        this.numAcquisti = numAcquisti;
    }

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
        this.sconto = sconto;
    }
}
