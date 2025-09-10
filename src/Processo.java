public class Processo {
    private int id;
    private String nome;
    private int prioridade; // 1 - alta  2 - média  3 - baixa
    private int ciclosNecessarios;
    private String recursoNecessario; // DISCO ou NULL
    private boolean foiBloqueado;

    public Processo(int id, String nome, int prioridade, int ciclosNecessarios, String recursoNecessario) {
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.ciclosNecessarios = ciclosNecessarios;
        this.recursoNecessario = (recursoNecessario == null || recursoNecessario.equalsIgnoreCase("null") || recursoNecessario.trim().isEmpty()) ? null : recursoNecessario;
        this.foiBloqueado = false;
    }

    public int getId() {return id;}

    public String getNome() {return nome;}

    public int getPrioridade() {return prioridade;}

    public int getCiclosNecessarios() {return ciclosNecessarios;}

    public String getRecursoNecessario() {return recursoNecessario;}

    public boolean foiBloqueado(){
        return foiBloqueado;
    }


    public void decrementarCiclos(){
        if(this.ciclosNecessarios > 0){
            this.ciclosNecessarios--;
        }
    }


    @Override
    public String toString(){
        return String.format("[ID: %d, Nome: %s, Prioridade: %d, Ciclos: %d]", id, nome, prioridade, ciclosNecessarios);
    }
}