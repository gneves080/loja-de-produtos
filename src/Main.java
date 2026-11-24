import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");
    private static final int FINALIZAR = 0;

    public static void main(String[] args) {
        // Catálogo: o tipo declarado é Produto, os objetos são as subclasses (polimorfismo)
        final Produto[] catalogo = {
                new Televisao(),
                new Radio(),
                new Videogame(),
                new Tablet(),
                new Celular()
        };

        // Vetor de compras: cada unidade comprada é um elemento do vetor
        Produto[] compras = new Produto[10];
        int totalCompras = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("  Bem-vindo(a) à nossa loja de eletrônicos!");
        System.out.println("==============================================");

        try {
            while (true) {
                exibirMenu(catalogo);
                System.out.print("Escolha uma opção: ");

                Integer opcao = lerInteiro(scanner);
                if (opcao == null) {
                    System.out.println("Entrada inválida! Digite apenas o número da opção.\n");
                    continue;
                }

                if (opcao == FINALIZAR) {
                    break;
                }

                if (opcao < 1 || opcao > catalogo.length) {
                    System.out.println("Produto inexistente! Escolha um número entre 0 e "
                            + catalogo.length + ".\n");
                    continue;
                }

                Produto escolhido = catalogo[opcao - 1];
                System.out.print("Quantidade de " + escolhido.getNome() + ": ");

                Integer quantidade = lerInteiro(scanner);
                if (quantidade == null || quantidade <= 0) {
                    System.out.println("Quantidade inválida! Digite um número inteiro maior que zero.\n");
                    continue;
                }

                for (int i = 0; i < quantidade; i++) {
                    if (totalCompras == compras.length) {
                        compras = Arrays.copyOf(compras, compras.length * 2);
                    }
                    compras[totalCompras++] = escolhido;
                }
                System.out.println(quantidade + "x " + escolhido.getNome() + " adicionado(s) ao carrinho.\n");
            }
        } catch (NoSuchElementException e) {
            // Entrada encerrada (Ctrl+D / fim do stdin): finaliza a compra normalmente
            System.out.println("\nEntrada encerrada. Finalizando a compra...");
        }

        exibirResumo(catalogo, compras, totalCompras);
    }

    private static void exibirMenu(Produto[] catalogo) {
        System.out.println("Produtos disponíveis:");
        for (int i = 0; i < catalogo.length; i++) {
            Produto p = catalogo[i];
            System.out.println(String.format(PT_BR, "  %d - %-10s (%s) - R$ %,.2f",
                    i + 1, p.getNome(), p.getDescricao(), p.getPreco()));
        }
        System.out.println("  " + FINALIZAR + " - Finalizar compra");
    }

    /** Retorna o inteiro digitado ou null se a entrada não for um número inteiro. */
    private static Integer lerInteiro(Scanner scanner) {
        String linha = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static void exibirResumo(Produto[] catalogo, Produto[] compras, int totalCompras) {
        System.out.println("\n========== RESUMO DA COMPRA ==========");

        if (totalCompras == 0) {
            System.out.println("Nenhum produto foi comprado.");
            System.out.println("======================================");
            return;
        }

        double total = 0;
        for (Produto produto : catalogo) {
            int quantidade = 0;
            for (int i = 0; i < totalCompras; i++) {
                if (compras[i] == produto) {
                    quantidade++;
                }
            }
            if (quantidade > 0) {
                double subtotal = quantidade * produto.getPreco();
                total += subtotal;
                System.out.println(String.format(PT_BR, "%dx %-10s R$ %,.2f cada = R$ %,.2f",
                        quantidade, produto.getNome(), produto.getPreco(), subtotal));
            }
        }

        System.out.println("--------------------------------------");
        System.out.println(String.format(PT_BR, "PREÇO FINAL: R$ %,.2f", total));
        System.out.println("======================================");
        System.out.println("Obrigado pela preferência!");
    }
}