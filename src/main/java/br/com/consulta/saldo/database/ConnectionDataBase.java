package br.com.consulta.saldo.database;

import br.com.consulta.saldo.builder.ContaSalario;
import br.com.consulta.saldo.querie.GetSaldoQuerie;
import database.DbFactory;
import database.DbUtils;
import database.Connection;

import java.util.Map;

public class ConnectionDataBase {

    private static DbUtils dbUtils;

    public ConnectionDataBase(Connection connection){
        dbUtils = DbFactory.get(connection);
    }

    public static DbUtils dbUtils(Connection connection){
        return DbFactory.get(connection);
    }

    public static ContaSalario getSaldo(String numeroConta){
        try{
            Map<String, Object> conta;

            conta = dbUtils.lerUmaLinha(GetSaldoQuerie.CONSULTASALDOCONTASALARIO
            .replace("<NCONTA>",numeroConta)
            );

            return ContaSalario.builder()
                    .nroConta(Integer.parseInt(conta.get("VLR_SALDO").toString()))
                    .nroAgencia(Integer.parseInt(conta.get("NUM_CONTA_CS").toString())).build();

        }catch (Exception e){
            System.out.println("Erro " + e);
            return null;
        }
    }

    public static int retornaSoSaldo(String numeroConta){
        try{
            Map<String, Object> conta;

            conta = dbUtils.lerUmaLinha(GetSaldoQuerie.CONSULTASALDOCONTASALARIO
                    .replace("<NCONTA>",numeroConta)
            );

            return Integer.parseInt(conta.get("VLR_SALDO").toString());

        }catch (Exception e){
            System.out.println("Erro " + e);
            return 0;
        }
    }

}
