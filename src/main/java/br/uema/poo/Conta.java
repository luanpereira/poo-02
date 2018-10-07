package br.uema.poo;

import java.util.Date;
import java.util.Map;

public class Conta {
    private Cliente cliente;
    private ETipo tipo;
    private Boolean ativa;
    private Double saldo;
    private Map<Date, String> historico;
}
