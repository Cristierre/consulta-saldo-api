package basetest;

import br.com.consulta.saldo.database.ConnectionDataBase;
import br.com.sicredi.contacorrente.util.it.enums.DbInfo;
import org.junit.BeforeClass;
import br.com.consulta.saldo.caller.ConsultaSaldoBuildRequest;


public class BaseTest {

    @BeforeClass
    public void setUp() {

        new ConnectionDataBase(DbInfo.HOMOLOG.getConnection());
    }


}
