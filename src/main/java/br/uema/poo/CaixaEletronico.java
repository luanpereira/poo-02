package br.uema.poo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CaixaEletronico {
    private static Scanner scanner = new Scanner(System.in);
    private Cliente cliente;
    private Agencia agencia;

    public CaixaEletronico(Agencia agencia, String cpf) {
        this.agencia = agencia;

        this.cliente = agencia.getClientes().stream()
                .filter(cli -> cli.getCpf().equals(cpf))
                .findAny()
                .orElse(null);

        if (!Optional.ofNullable(this.cliente).isPresent()) {
            System.out.println("CPF do Cliente não encontrado na agência, verifique.");
            return;
        }

        if (cliente.getContas().size() == 0) {
            System.out.println("Cliente não possui conta bancária, verifique.");
            return;
        } else if (cliente.getContas().size() > 1) {
            String contas = cliente.getContas()
                    .stream()
                    .map(c -> c.getTipo().getCodigo() + " - " + c.getTipo().getDescricao())
                    .collect(Collectors.joining("|", "[", "]"));
            System.out.printf("Qual conta deseja acessar? %s", contas);
            int conta_sel = Integer.parseInt(scanner.nextLine());
            cliente.setContaSelecionada(cliente.getContas()
                    .stream()
                    .filter(c -> c.getTipo() == ETipo.getByCodigo(conta_sel))
                    .findAny()
                    .get());
        } else
            cliente.setContaSelecionada(cliente.getContas().get(0));

        iniciar();
    }

    private void iniciar() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:m:s");
        double deposito, c, d, result;
        Integer valor;
        String str;

        int op = 99;

        try {

            while (op > 0) {
                System.out.println("--------------------------------------------------");
                System.out.println("Caixa Eletrônico Banco do Povo S/A");
                System.out.printf("Cliente: %s \n", cliente.getNome());
                System.out.printf("Conta: %s \n", cliente.getContaSelecionada().getTipo().getDescricao());
                System.out.printf("Data: %sh \n\n", dateFormat.format(new Date()));
                System.out.println(" 1 - Consultar Saldo");
                System.out.println(" 2 - Extrato");
                System.out.println(" 3 - Sacar");
                System.out.println(" 4 - Depositar");
                System.out.println(" 5 - Sair do Caixa Eletrônico");

                System.out.print("\nEscolha: ");

                op = Integer.parseInt(scanner.nextLine());

                switch (op) {
                    case 1:
                        System.out.printf("\nResultado: Saldo: %f \n\n", cliente.getContaSelecionada().getSaldo());
                        break;

                    case 2:
                        System.out.printf("\nExtrato: \n%s \n\n", cliente.getContaSelecionada().getExtrato());
                        break;

                    case 3:
                        try {
                            System.out.print("\nInforme a quantia para saque:");
                            valor = Integer.parseInt(scanner.nextLine());

                            cliente.getContaSelecionada().sacar(valor, "Saque Cx Eletr");
                            cliente.getContaSelecionada().getSaldo();
                        } catch (NumberFormatException ex) {
                            System.out.print("\nO valor para saque deve ser inteiro.\n\n");
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("\nInforme a quantia para depósito:");
                        deposito = Double.parseDouble(scanner.nextLine());

                        cliente.getContaSelecionada().depositar(deposito, "Depósito Cx Eletr");
                        break;

                    default:
                        return;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
