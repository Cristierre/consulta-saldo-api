package br.com.consulta.saldo.basetest;

import br.com.consulta.saldo.datafactory.SaldoDataFactory;
import br.com.sicredi.contacorrente.util.it.enums.DbInfo;
import org.junit.BeforeClass;


public class BaseTest {

    @BeforeClass
    public static void setUp() {
        new SaldoDataFactory(DbInfo.HOMOLOG.getConnection());
    }


}
