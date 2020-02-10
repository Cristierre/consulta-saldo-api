package br.com.consulta.saldo.querie;

public class GetSaldoQuerie {
    public static final String CONSULTASALDOCONTASALARIO =
            "                       SELECT SAL.NUM_AGENCIA, \n" +
                    "                      SAL.NUM_CONTA_SALARIO,\n" +
                    "               FROM AG0101.SALDO_CONTA_SALARIO SAL\n" +
                    "               WHERE SAL.NUM_CONTA_SALARIO =<NCONTA>";

    public static final String SELECT_QUE_RETORNA_SO_SALDO =
            "                       SELECT SAL.VLR_SALDO_CS , \n" +
                    "               FROM AG0101.SALDO_CONTA_SALARIO SAL\n" +
                    "               WHERE SAL.NUM_CONTA_SALARIO =<NCONTA>";
}
