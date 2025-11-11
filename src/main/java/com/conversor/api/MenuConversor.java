package com.conversor.api;
import java.util.Scanner;

    public class MenuConversor {

        private final ServicoDeCambio servico = new ServicoDeCambio();
        private final Scanner entrada = new Scanner(System.in);

        public void iniciar() {
            while (true) {
                System.out.println("""
            ==========================
             CONVERSOR DE MOEDAS
            ==========================
            1. Dólar (USD) → Real (BRL)
            2. Real (BRL) → Dólar (USD)
            3. Euro (EUR) → Real (BRL)
            4. Real (BRL) → Euro (EUR)
            5. Libra (GBP) → Dólar (USD)
            6. Dólar (USD) → Iene (JPY)
            7. Sair
            """);

                System.out.print("Escolha uma opção: ");
                int opcao = entrada.nextInt();

                if (opcao == 7) {
                    System.out.println("Encerrando o conversor... Até logo!");
                    break;
                }

                System.out.print("Digite o valor: ");
                double valor = entrada.nextDouble();

                try {
                    switch (opcao) {
                        case 1 -> converter("USD", "BRL", valor);
                        case 2 -> converter("BRL", "USD", valor);
                        case 3 -> converter("EUR", "BRL", valor);
                        case 4 -> converter("BRL", "EUR", valor);
                        case 5 -> converter("GBP", "USD", valor);
                        case 6 -> converter("USD", "JPY", valor);
                        default -> System.out.println("⚠️ Opção inválida!");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao realizar a conversão: " + e.getMessage());
                }
            }
        }

        private void converter(String de, String para, double valor) throws Exception {
            double taxa = servico.buscarTaxa(de, para);
            double resultado = valor * taxa;
            resultado = Math.round(resultado * 100.0) / 100.0;
            System.out.printf("Resultado: %.2f %s = %.2f %s%n%n", valor, de, resultado, para);
        }
    }
