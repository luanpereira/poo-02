package br.uema.poo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Conta {
    private Agencia agencia;
    private Cliente cliente;
    private ETipo tipo;
    private Boolean ativa;
    private Double saldo;
    private Map<Date, String> historico;

    public Conta(Agencia agencia, String cpf_cliente, int tipo, Double valor) {
        historico = new HashMap<>();
        this.saldo = 0d;

        this.agencia = agencia;

        this.cliente = agencia.getClientes().stream()
                .filter(cli -> cli.getCpf().equals(cpf_cliente))
                .findAny()
                .orElse(null);

        if (!Optional.ofNullable(this.cliente).isPresent()) {
            System.out.println("CPF do Cliente não encontrado na agência, verifique.");
            return;
        }

        this.tipo = ETipo.getByCodigo(tipo);

        if (!Optional.ofNullable(this.tipo).isPresent()) {
            System.out.println("Tipo de conta inválida, verifique.");
            return;
        }

        if (valor <= 0) {
            System.out.println("Valor de depósito inicial inválido.");
            return;
        }

        this.depositar(valor, "Abertura de conta - ");

        cliente.getContas().add(this);

        System.out.printf("\nConta aberta com sucesso! Saldo : %f\n", this.getSaldo());

    }

    public void depositar(Double valor) {
        this.depositar(valor, "");
    }

    public void depositar(Double valor, String descricao) {
        this.saldo = new BigDecimal(saldo)
                .add(new BigDecimal(valor))
                .doubleValue();

        historico.put(new Date(),
                descricao + "[+] crédito de R$ " + valor + ", saldo R$ " + this.saldo);

        System.out.println("\nDepósito realizado com sucesso!");
        this.getSaldo();
    }

    public void sacar(Integer valor) throws InterruptedException {
        this.sacar(valor, "");
    }

    public void sacar(Integer valor, String descricao) throws InterruptedException {

        if(this.saldo <=0)
            throw new IllegalArgumentException("Saque inválido. Sem saldo.");

        if(valor > this.saldo)
            throw new IllegalArgumentException("Saque inválido. Valor do saque maior que o saldo.");

        System.out.println("\nSaque autorizado");

        this.saldo = new BigDecimal(saldo)
                .subtract(new BigDecimal(valor))
                .doubleValue();

        historico.put(new Date(),
                descricao + "[-] débito de R$ " + valor + ", saldo R$ " + this.saldo);

        this.dispensarCedulas();
    }

    private void dispensarCedulas() throws InterruptedException {
        System.out.print("\nContando cédulas...");
        Thread.sleep(3000);
        System.out.print("\nRetire o dinheiro\n\n");
    }

    public Double getSaldo() {
        if(saldo == 0)
            System.out.println("\nConta inativa. Saldo zerado.");

        return saldo;
    }

    public String getExtrato() {
        return historico.entrySet()
                .stream()
                .map(h -> h.getKey() + " >> " + h.getValue())
                .sorted()
                .collect(Collectors.joining("\n"));
    }

    public ETipo getTipo() {
        return tipo;
    }
}
