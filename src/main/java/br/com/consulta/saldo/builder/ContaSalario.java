package br.com.consulta.saldo.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ContaSalario {
    private int nroAgencia;
    private int nroConta;
}
