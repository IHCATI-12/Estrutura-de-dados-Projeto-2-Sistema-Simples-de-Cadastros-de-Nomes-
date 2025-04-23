import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken; 

public class App {
    // adicionarNome
    public static void adicionarNome(List<String> nomes, Scanner sc) {
        System.out.println("Digite o nome: ");
        String nome = sc.nextLine().trim();
        
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
        } else if (nomes.contains(nome)) {
            System.out.println("Nome já está na lista.");
        } else {
            nomes.add(nome);
            System.out.println("Nome adicionado com sucesso.");
        }
    }

    // listarNomes
    public static void listarNomes(List<String> nomes) {
        if (nomes.isEmpty()){
            System.out.println("Nenhum nome cadastrado.");
            return;
        }

        ArrayList<String> nomesOrdenados = new ArrayList<>(nomes);
        Collections.sort(nomesOrdenados);

        System.out.println("\nNomes ordenados:");
        for (String nome : nomesOrdenados) {
            System.out.println(nome);
        }

    }

    // removerNomes
    public static void removerNomes(List<String> nomes, Scanner sc) {
        System.out.print("Digite o nome a remover: ");
        String nome = sc.nextLine().trim();

        if (nomes.remove(nome)) {
            System.out.println("Nome removido com sucesso.");
        } else {
            System.out.println("Nome não encontrado.");
        }
    }

    // buscarNome
    public static void buscarNome(List<String> nomes, Scanner sc) {
        System.out.println("Digite o nome para busca: ");
        String nome = sc.nextLine().trim();

        if (nomes.contains(nome)) {
            System.out.println("Nome encontrado.");
        } else {
            System.out.println("Nome não está na lista.");
        }
    }

    // salvarNomes
    private static void salvarNomes(List<String> nomes, String caminhoArquivo, Gson gson) {
        try (Writer writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(nomes, writer);
            System.out.println("Nomes salvos com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar nomes: " + e.getMessage());
        }
    }

    // carregarNomes (nova função)
    private static void carregarNomes(List<String> nomes, String caminhoArquivo, Gson gson) {
        try (Reader reader = new FileReader(caminhoArquivo)) {
            List<String> nomesCarregados = gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());
            nomes.clear();
            nomes.addAll(nomesCarregados);
            System.out.println("Nomes carregados com sucesso de " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar nomes: " + e.getMessage());
        }
    }



    public static void main(String[] args) throws Exception {
        ArrayList <String> nomes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String caminho = "nomes.json";
        Gson gson = new Gson();
        int escolha;

        do {
            System.out.println("==================================");
            System.out.println("         MENU PRINCIPAL");
            System.out.println("==================================");
            System.out.println("1. Adicionar nome");
            System.out.println("2. Listar nomes");
            System.out.println("3. Remover nome");
            System.out.println("4. Buscar nome");
            System.out.println("5. Salvar nomes em arquivo JSON");
            System.out.println("6. Carregar nomes de arquivo JSON");
            System.out.println("0. Sair");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    System.out.println("Você escolheu: Adicionar nome");
                    adicionarNome(nomes, sc);
                    break;
                case 2:
                    System.out.println("Você escolheu: Listar nomes");
                    listarNomes(nomes);
                    break;
                case 3:
                    System.out.println("Você escolheu: Remover nome");
                    removerNomes(nomes, sc);
                    break;
                case 4:
                    System.out.println("Você escolheu: Buscar nome");
                    removerNomes(nomes, sc);
                    break;
                case 5:
                    System.out.println("Você escolheu: Salvar nomes em arquivo JSON");
                    salvarNomes(nomes, caminho, gson);
                    break;
                case 6:
                    System.out.println("Você escolheu: Carregar nomes de arquivo JSON");
                    carregarNomes(nomes, caminho, gson);
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");

            }

        } while (escolha != 0);

        sc.close();
    }

}
