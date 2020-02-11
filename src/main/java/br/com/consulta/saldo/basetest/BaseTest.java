package br.com.consulta.saldo.basetest;

import br.com.consulta.saldo.database.ConnectionDataBase;
import br.com.sicredi.contacorrente.util.it.enums.DbInfo;
import org.junit.BeforeClass;


public class BaseTest {

    @BeforeClass
    public static void setUp() {
        new ConnectionDataBase(DbInfo.HOMOLOG.getConnection());
    }


}
