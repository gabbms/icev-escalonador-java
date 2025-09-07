public class ListaCircularDeProcessos {
    private Node cursor; // ponteiro que se move pela lista

    public ListaCircularDeProcessos() {
        this.cursor = null;
    }

    public boolean estaVazia(){
        return this.cursor == null;
    }

    public void inserir(Processo processo){
        Node novoNo = new Node(processo);
        if(estaVazia()){
            cursor = novoNo;
            cursor.setProximo(novoNo); // aponta para si mesmo
        }else{
            novoNo.setProximo(cursor.getProximo());
            cursor.setProximo(novoNo);
        }
    }

    // Método principal da lógica Round-Robin: obtém o próximo processo e avança o cursor.
    public Processo obterProximoParaExecutar(){
        if(estaVazia()){
            return null;
        }
        cursor = cursor.getProximo(); // avança para o próximo nó
        return cursor.getProcesso(); // Retorna o processo desse nó
    }

    // Remove um processo específico da lista (quando termina ou é bloqueado)
    public boolean remover(){
        if(estaVazia()){
            return false;
        }
        Node noAnterior = cursor;
        Node noAtual = cursor.getProximo();

        // Itera pela lista para encontrar o processo
        return true; // continua...
    }


}
