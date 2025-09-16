import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Erro: Forneça o caminho para o arquivo de processos como argumento.");
            System.out.println("Exemplo: java Main processos.txt");
            return;
        }

        Scheduler scheduler = new Scheduler();
        String fileName = args[0];


        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String nome = parts[1].trim();
                int prioridade = Integer.parseInt(parts[2].trim());
                int ciclos = Integer.parseInt(parts[3].trim());
                String recurso = (parts.length > 4) ? parts[4].trim() : null;

                scheduler.adicionarProcesso(new Processo(id, nome, prioridade, ciclos, recurso));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado: " + fileName);
            return;
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo. Verifique o formato. Detalhes: " + e.getMessage());
            return;
        }


        int cicloAtual = 1;
        while (!scheduler.todosProcessosConcluidos()) {
            System.out.println("\n------------------- CICLO DE CPU #" + cicloAtual + " -------------------");
            scheduler.executarCicloDeCPU();
            cicloAtual++;
        }

        System.out.println("\n----------------------------------------------------");
        System.out.println("Todos os processos foram concluídos.");
        System.out.println("----------------------------------------------------");
    }
}