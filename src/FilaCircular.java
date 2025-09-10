public class FilaCircular {
    private Node cauda;

    public FilaCircular(){
        this.cauda = null;
    }

    public boolean estaVazia(){
        return this.cauda == null;
    }

    // inserir no final da fila (FIFO)
    public void inserir(Processo processo){
        Node novoNo = new Node(processo);
        if(estaVazia()){
            this.cauda = novoNo;
            this.cauda.setProximo(cauda); // aponta para si mesmo
        }else{
            novoNo.setProximo(cauda.getProximo());
            this.cauda.setProximo(novoNo);
            this.cauda = novoNo;
        }
    }
    // remove no inicio da fila (FIFO)
    public Processo remover(){
        if(estaVazia()){
            return null;
        }

        Node cabeca = cauda.getProximo();
        Processo processoRemovido = cabeca.getProcesso();

        if(this.cauda == cabeca){ // se houver apenas um elemento na lista
            cauda = null; // a lista vai ficar vazia
        }else{
            cauda.setProximo(cabeca.getProximo());
        }

        return processoRemovido;
    }


    @Override
    public String toString() {
        if (estaVazia()) {
            return "[ ]";
        }

        // 1. Começa com uma String normal, em vez de um StringBuilder
        String resultado = "[ ";

        Node cabeca = cauda.getProximo();
        Node atual = cabeca;

        do {
            // 2. Usa o operador '+=' para concatenar (adicionar) a nova parte à String
            resultado += atual.getProcesso().toString();

            atual = atual.getProximo();

            if (atual != cabeca) {
                // 3. Concatena a vírgula e o espaço
                resultado += ", ";
            }
        } while (atual != cabeca);

        // 4. Concatena o colchete final
        resultado += " ]";

        // 5. Retorna a String final
        return resultado;
    }
}

