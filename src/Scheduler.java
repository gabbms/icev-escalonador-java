public class Scheduler {
    private ListaDeProcessos lista_alta_prioridade;
    private ListaDeProcessos lista_media_prioridade;
    private ListaDeProcessos lista_baixa_prioridade;
    private ListaDeProcessos lista_bloqueados;
    private int contador_de_ciclos_alta_prioridade;

    public Scheduler(){
        lista_alta_prioridade = new ListaDeProcessos();
        lista_media_prioridade = new ListaDeProcessos();
        lista_baixa_prioridade = new ListaDeProcessos();
        lista_bloqueados = new ListaDeProcessos();
        contador_de_ciclos_alta_prioridade = 0;
    }

    public void adicionarProcesso(Processo p){
        switch (p.getPrioridade()){ // vai pegar a prioridade e vai jogar nos casos
            case 1:
                lista_alta_prioridade.adicionar(p);
                break;
            case 2:
                lista_media_prioridade.adicionar(p);
                break;
            case 3:
                lista_baixa_prioridade.adicionar(p);
        }
    }
}