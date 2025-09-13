public class Scheduler {
    private ListaDeProcessos lista_alta_prioridade;
    private ListaDeProcessos lista_media_prioridade;
    private ListaDeProcessos lista_baixa_prioridade;
    private ListaDeProcessos lista_bloqueados;
    private int contador_ciclos_alta_prioridade;

    public Scheduler(){
        lista_alta_prioridade = new ListaDeProcessos();
        lista_media_prioridade = new ListaDeProcessos();
        lista_baixa_prioridade = new ListaDeProcessos();
        lista_bloqueados = new ListaDeProcessos();
        contador_ciclos_alta_prioridade = 0;
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

    public void executarCicloDeCPU() {
        // Desbloqueio de Processos no início do ciclo
        if (!lista_bloqueados.estaVazia()) {
            Processo pDesbloqueado = lista_bloqueados.remover();
            System.out.printf("EVENTO: Processo %s (ID %d) desbloqueado.\n", pDesbloqueado.getNome(), pDesbloqueado.getId());
            adicionarProcesso(pDesbloqueado); // Adiciona de volta ao final de sua lista de prioridade original
        }

        Processo processoParaExecutar = null;
        boolean antiInanicaoAtivada = false;

        // Prevenção de Inanição (Starvation)
        if (contador_ciclos_alta_prioridade >= 5) {
            System.out.println("EVENTO: Prevenção de Inanição Ativada!");
            antiInanicaoAtivada = true;
            if (!lista_media_prioridade.estaVazia()) {
                processoParaExecutar = lista_media_prioridade.remover();
            } else if (!lista_baixa_prioridade.estaVazia()) {
                processoParaExecutar = lista_baixa_prioridade.remover();
            }

            if (processoParaExecutar != null) {
                contador_ciclos_alta_prioridade = 0; // O contador é zerado após a execução
            } else {
                antiInanicaoAtivada = false;
            }
        }

        // Execução Padrão
        if (processoParaExecutar == null) {
            if (!lista_alta_prioridade.estaVazia()) {
                processoParaExecutar = lista_alta_prioridade.remover();
            } else if (!lista_media_prioridade.estaVazia()) {
                processoParaExecutar = lista_media_prioridade.remover();
            } else if (!lista_baixa_prioridade.estaVazia()) {
                processoParaExecutar = lista_baixa_prioridade.remover();
            }
        }

        if (processoParaExecutar != null) {
            // Gerenciamento de Recursos (Bloqueio)
            if ("DISCO".equals(processoParaExecutar.getRecursoNecessario()) && !processoParaExecutar.foiBloqueado()) {
                processoParaExecutar.setFoiBloqueado(true); // Garante que isso só ocorra na primeira vez
                lista_bloqueados.adicionar(processoParaExecutar); // Adicionado ao final da lista_bloqueados
                System.out.printf("PROCESSO EM EXECUÇÃO: Nenhum (Processo %s foi bloqueado por 'DISCO')\n", processoParaExecutar.getNome());
            } else {
                // Simulação de Execução
                System.out.printf("PROCESSO EM EXECUÇÃO: %s\n", processoParaExecutar.toString());
                processoParaExecutar.decrementarCiclos();

                if (processoParaExecutar.getPrioridade() == 1 && !antiInanicaoAtivada) {
                    contador_ciclos_alta_prioridade++;
                } else if (processoParaExecutar.getPrioridade() != 1) {
                    contador_ciclos_alta_prioridade = 0;
                }

                if (processoParaExecutar.getCiclosNecessarios() > 0) {
                    adicionarProcesso(processoParaExecutar); // Reinserido no final da sua lista de origem
                } else {
                    System.out.printf("EVENTO: Processo %s terminou a execução.\n", processoParaExecutar.getNome());
                }
            }
        } else {
            System.out.println("PROCESSO EM EXECUÇÃO: Nenhum (CPU Ociosa)");
        }

        imprimirEstadoFilas();
    }

    public boolean todosProcessosConcluidos() {
        return lista_alta_prioridade.estaVazia() &&
                lista_media_prioridade.estaVazia() &&
                lista_baixa_prioridade.estaVazia() &&
                lista_bloqueados.estaVazia();
    }

    public void imprimirEstadoFilas() {
        System.out.println("-> Fila Alta:    " + lista_alta_prioridade);
        System.out.println("-> Fila Média:   " + lista_media_prioridade);
        System.out.println("-> Fila Baixa:   " + lista_baixa_prioridade);
        System.out.println("-> Bloqueados:   " + lista_bloqueados);
        System.out.println("-> Ciclos Alta Prio Consecutivos: " + contador_ciclos_alta_prioridade);
    }
}
