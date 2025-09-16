import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java Main <caminho_para_arquivo_csv>");
            return;
        }

        String caminhoArquivo = args[0];
        Scheduler scheduler = new Scheduler();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                // Ignorar cabeçalho
                if (linha.startsWith("id")) continue;

                String[] dados = linha.split(",");

                int id = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                int prioridade = Integer.parseInt(dados[2].trim());
                int ciclos = Integer.parseInt(dados[3].trim());
                String recurso = dados.length > 4 ? dados[4].trim() : null;

                Processo processo = new Processo(id, nome, prioridade, ciclos, recurso);
                scheduler.adicionarProcesso(processo);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        int ciclo = 1;
        while (!scheduler.todosProcessosConcluidos()) {
            System.out.println("===== CICLO " + ciclo + " =====");
            scheduler.executarCicloDeCPU();
            ciclo++;
            System.out.println();
        }

        System.out.println("Todos os processos foram finalizados.");
    }
}