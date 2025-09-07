public class Processo {
    private int id;
    private String nome;
    private int prioridade;
    private int ciclosNecessarios;
    private String recursoNecessario;

    public Processo(int id, String nome, int prioridade, int ciclosNecessarios, String recursoNecessario) {
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclosNecessarios = ciclosNecessarios;
        this.recursoNecessario = recursoNecessario;
    }

    public int getId() {return id;}

    public String getNome() {return nome;}

    public int getPrioridade() {return prioridade;}

    public int getCiclosNecessarios() {return ciclosNecessarios;}

    public String getRecursoNecessario() {return recursoNecessario;}

    public void setCiclosNecessarios(int ciclos) {
            this.ciclosNecessarios = ciclos;
    }

    public String toString(){
        return String.format("[ID: %d, Nome: %s, Prioridade: %d, Ciclos: %d]", id, nome, prioridade, ciclosNecessarios);
    }
}