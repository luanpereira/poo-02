package br.uema.poo;

import java.util.Scanner;

public class CaixaEletronico {
    private static Scanner scanner = new Scanner(System.in);
    private Cliente cliente;

    public void Iniciar(){
        double a, b, c, d, result;
        int e;
        String str;

        int op = 99;

        try {

            while (op > 0) {
                System.out.println("Caixa Eletrônico Banco do Povo S/A");
                System.out.printf("Cliente: %s \n\n", cliente.getNome());
                System.out.println(" 1 - Consultar Saldo");
                System.out.println(" 2 - Extrato");
                System.out.println(" 3 - Sacar");
                System.out.println(" 4 - Depositar");
                System.out.println(" 4 - Sair do Caixa Eletrônico");

                System.out.print("\nEscolha: ");

                op = Integer.parseInt(scanner.nextLine());

                switch (op) {
                    case 1:
                        System.out.print("\nEntre com o primeiro número: ");
                        a = Double.parseDouble(scanner.nextLine());

                        System.out.print("\nEntre com o segundo número: ");
                        b = Double.parseDouble(scanner.nextLine());

                        //result = new Produto(a, b).calcular();
                        System.out.printf("Resultado: %f \n\n", 0);
                        break;


                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
