package br.uema.poo;

import java.util.Scanner;

public class Atividade {
    private static Scanner scanner = new Scanner(System.in);

    public void Iniciar() {
        double valor, b, c, d, result;
        int tipo;
        String nome, cpf, str;

        int op = 99;

        Agencia agencia = new Agencia("Agência Principal", "21.147.963/0001-32");

        try {

            while (op > 0) {
                System.out.println("\nBanco do Povo S/A:\n");
                System.out.println(" 1 - Cadastro de Clientes");
                System.out.printf(" 2 - Listar Clientes (%d)\n", agencia.getClientes().size());
                System.out.println(" 3 - Abrir Conta");
                System.out.println(" 4 - Acessar Caixa Eletrônico");

                System.out.print("\nEscolha: ");

                op = Integer.parseInt(scanner.nextLine());

                switch (op) {
                    case 1:
                        System.out.print("\nEntre com o Nome do Cliente: ");
                        nome = String.valueOf(scanner.nextLine());

                        System.out.print("\nEntre com o CPF: ");
                        cpf = String.valueOf(scanner.nextLine());

                        agencia.getClientes().add(new Cliente(nome, String.valueOf(cpf)));
                        System.out.printf("Cliente Cadastrado com sucesso!", 0);
                        break;

                    case 2:
                        System.out.print("\nLista de Clientes:\n");

                        agencia.getClientes()
                            .forEach(cli -> System.out.printf("Nome: %s, CPF: %s\n" +
                                    "-----------------------------------------------------------------------------------\n",
                                    cli.getNome(), cli.getCpf()));
                        break;

                    case 3:
                        System.out.print("\nEntre com o CPF do Cliente cadastrado: ");
                        cpf = String.valueOf(scanner.nextLine());

                        System.out.printf("\nInforme o tipo de Conta %s:\n ", ETipo.getValues());
                        tipo = Integer.parseInt(scanner.nextLine());

                        System.out.print("\nQual valor de depósito inicial?");
                        valor = Double.parseDouble(scanner.nextLine());

                        Conta conta = new Conta(agencia, cpf, tipo, valor);
                        System.out.printf("\nConta aberta com sucesso! Saldo : %f\n", conta.getSaldo());
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
