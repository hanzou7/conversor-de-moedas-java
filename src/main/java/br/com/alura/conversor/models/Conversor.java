package br.com.alura.conversor.models;

import java.util.Scanner;

public class Conversor {
    Scanner scanner = new Scanner(System.in);
    boolean inMenu = true;

    public void menu() {

        while (inMenu) {
            System.out.println("""
                    ****** CONVERSOR DE MOEDAS ******
                    
                    1 - DÓLAR (USD) > REAL (BRL)
                    2 - EURO (EUR) > REAL (BRL)
                    3 - FRANCO SUIÇO (CHF) > REAL (BRL)
                    4 - REAL (BRL) > DÓLAR (USD)
                    5 - REAL (BRL) > EURO (EUR)
                    6 - REAL (BRL) > FRANCO SUÍÇO (CHF)
                    7 - SAIR
                    
                    Escolha uma das opções:
                    """);

            var userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    this.userValue("USD", "BRL");
                    break;
                case "2":
                    this.userValue("EUR", "BRL");
                    break;
                case "3":
                    this.userValue("CHF", "BRL");
                    break;
                case "4":
                    this.userValue("BRL", "USD");
                    break;
                case "5":
                    this.userValue("BRL", "EUR");
                    break;
                case "6":
                    this.userValue("BRL", "CHF");
                    break;

                default:
                    if (userChoice.equals("7")) {
                        inMenu = false;
                    } else {
                        System.out.println("Opção inválida. Selecione outra opção.");
                    }
                    break;
            }
        }
    }

    public void userValue(String baseCode, String targetCode) {

        System.out.println("Digite o valor que deseja converter: ");

        try {
            double conversionValue = scanner.nextDouble();
            scanner.nextLine();

            ConversorDados conversorDados = new ConversorDados(baseCode, targetCode, conversionValue);
            ConsultaAPI consultaAPI = new ConsultaAPI(conversorDados);
            System.out.println(consultaAPI.apiCall());

        } catch (Exception e) {
            System.out.println("O valor digitado é inválido.");
        }
    }
}
