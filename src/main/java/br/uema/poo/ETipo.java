package br.uema.poo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public enum ETipo {
    CORRENTE(1, "Conta Corrente"),
    POUPANCA(2, "Conta Poupança"),
    SALARIO(3, "Conta Salário");

    private final int codigo;
    private final String descricao;

    ETipo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static ETipo getByCodigo(int codigo){
        List<ETipo> eTipos = Arrays.asList(ETipo.values());
        return eTipos.stream()
                .filter(t -> t.codigo == codigo)
                .findAny()
                .orElse(null);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String getValues() {
        return Arrays.asList(ETipo.values())
                .stream()
                .map(t->t.codigo + " - " + t.descricao)
                .collect(Collectors.joining("|","[","]"));
    }
}
