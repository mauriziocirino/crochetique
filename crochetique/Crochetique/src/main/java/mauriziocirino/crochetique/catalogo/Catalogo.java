package mauriziocirino.crochetique.catalogo;

public class Catalogo {
    private String nome, stagione;
    private int id;

    public Catalogo(){
        super();
    }

    public Catalogo(String nome, String stagione) {
        this.nome = nome;
        this.stagione = stagione;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStagione() {
        return stagione;
    }

    public void setStagione(String stagione) {
        this.stagione = stagione;
    }

    public int getId() {
        return id;
    }
}
