package br.com.consulta.saldo.datafactory;

import br.com.consulta.saldo.builder.ContaSalario;
import br.com.consulta.saldo.querie.GetSaldoQuerie;
import database.DbFactory;
import database.DbUtils;
import database.Connection;

import java.util.Map;

public class SaldoDataFactory {

    private static DbUtils dbUtils;

    public SaldoDataFactory(Connection connection){
        dbUtils = DbFactory.get(connection);
    }

    public static DbUtils dbUtils(Connection connection){
        return DbFactory.get(connection);
    }

    public static int getSaldo(String numeroConta){
        try{
            Map<String, Object> conta;

            conta = dbUtils.lerUmaLinha(GetSaldoQuerie.SELECT_QUE_RETORNA_SO_SALDO
                    .replace("<NCONTA>",numeroConta)
            );

            return Integer.parseInt(conta.get("VLR_SALDO").toString());

        }catch (Exception e){
            System.out.println("Erro " + e);
            return 0;
        }
    }

}
