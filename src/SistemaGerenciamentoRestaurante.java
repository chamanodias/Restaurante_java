import java.util.HashMap;
import java.util.Scanner;

public class SistemaGerenciamentoRestaurante {
    private static HashMap<String, Integer> popularidadeItens = new HashMap<>(); // Qual item do menu é mais procurado
    private static HashMap<String, Integer> popularidadeBebidas = new HashMap<>(); // Qual bebida é mais procurada

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===================================");
        System.out.println("  Bem-vindo ao Sistema de Gerenciamento de Restaurante");
        System.out.println("===================================\n");
        gerenciamentoRestaurante(scanner);
        scanner.close();
    }

    private static void gerenciamentoRestaurante(Scanner scanner) {
        System.out.println("Por favor, informe o número da mesa que deseja ocupar.");
        System.out.print("Número da mesa: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nMesa " + numeroMesa + " ocupada com sucesso! Estamos prontos para receber seu pedido.\n");

        double total = 0.0;

        boolean adicionarMaisItens = true;
        while (adicionarMaisItens) {
            System.out.println("Por favor, informe os detalhes do item que deseja adicionar ao pedido:");
            System.out.print("Nome do item: ");
            String nomeItem = scanner.nextLine();

            System.out.print("Preço do item (R$): ");
            double precoItem = scanner.nextDouble();

            System.out.print("Quantidade desejada: ");
            int quantidadeItem = scanner.nextInt();
            scanner.nextLine();

            total += precoItem * quantidadeItem;
            popularidadeItens.put(nomeItem, popularidadeItens.getOrDefault(nomeItem, 0) + quantidadeItem);

            // Pergunta se deseja adicionar bebidas
            System.out.print("\nDeseja adicionar alguma bebida ao pedido? (s/n): ");
            String respostaBebida = scanner.nextLine();
            if (respostaBebida.equalsIgnoreCase("s")) {
                System.out.print("Nome da bebida: ");
                String nomeBebida = scanner.nextLine();

                System.out.print("Preço da bebida (R$): ");
                double precoBebida = scanner.nextDouble();

                System.out.print("Quantidade da bebida: ");
                int quantidadeBebida = scanner.nextInt();
                scanner.nextLine();

                // Pergunta se deseja com gelo
                System.out.print("Você deseja a bebida com gelo? (s/n): ");
                String comGelo = scanner.nextLine();
                if (comGelo.equalsIgnoreCase("s")) {
                    System.out.println("A bebida será servida com gelo.");
                } else {
                    System.out.println("A bebida será servida sem gelo.");
                }

                total += precoBebida * quantidadeBebida;
                popularidadeBebidas.put(nomeBebida, popularidadeBebidas.getOrDefault(nomeBebida, 0) + quantidadeBebida);
            }

            System.out.print("\nDeseja adicionar mais itens ao pedido? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                adicionarMaisItens = false;
            }
            System.out.println();
        }

        System.out.println("===================================");
        System.out.println("Resumo do Pedido - Mesa " + numeroMesa);
        System.out.println("===================================");
        System.out.println("Total a pagar: R$" + String.format("%.2f", total));

        // Exibe o item mais popular
        String itemMaisPopular = popularidadeItens.entrySet().stream()
            .max((entry1, entry2) -> entry1.getValue() - entry2.getValue())
            .get()
            .getKey();
        System.out.println("O item mais popular até o momento foi: " + itemMaisPopular);

        // Exibe a bebida mais popular (se houver)
        if (!popularidadeBebidas.isEmpty()) {
            String bebidaMaisPopular = popularidadeBebidas.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() - entry2.getValue())
                .get()
                .getKey();
            System.out.println("A bebida mais popular até o momento foi: " + bebidaMaisPopular);
        }

        System.out.println("===================================");
        System.out.println("Agradecemos por utilizar nosso sistema de gerenciamento!");
    }
}
