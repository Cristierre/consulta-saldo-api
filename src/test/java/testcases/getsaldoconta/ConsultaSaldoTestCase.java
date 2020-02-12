package testcases.getsaldoconta;


import br.com.consulta.saldo.basetest.BaseTest;
import br.com.consulta.saldo.builder.ContaSalario;
import br.com.consulta.saldo.caller.ConsultaSaldoBuildRequest;

import br.com.consulta.saldo.database.ConnectionDataBase;
import org.junit.Assert;
import org.junit.Test;

import static br.com.consulta.saldo.database.ConnectionDataBase.getSaldo;
import static org.testng.Assert.assertEquals;


public class ConsultaSaldoTestCase extends BaseTest {

    @Test
    public void respostaRequisicao(){
    ContaSalario expected = getSaldo("139354");

        int result = ConsultaSaldoBuildRequest.requisicao("IB","0101","139354", "USER")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("saldo");
        System.out.println(expected);

        Assert.assertEquals(expected, result);

    }
    @Test
    public void verificaSaldo(){
        float result = ConsultaSaldoBuildRequest.requisicao("IB","0101","139354", "USER")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getFloat("saldo");

        float expected = ConnectionDataBase.retornaSoSaldo("139354");

        assertEquals(expected, result);
    }
    @Test
    public void verificaSaldoContaInvalidaFalha(){
        String expected = "[{message=Necessário 6 caracteres. Ex.: 000000, field=nroConta}]";
        String result = ConsultaSaldoBuildRequest.requisicao("IB","0101","13954", "USER")
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .body()
                .path("constraints")
                .toString();

        Assert.assertEquals(expected, result);

    }
    @Test
    public void verificaSaldoAgenciaInvalidaFalha(){
        String expected = "[{message=Necessário 4 caracteres., field=nroAgencia}]";

        String result = ConsultaSaldoBuildRequest.requisicao("IB","45","139354", "USER")
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("constraints")
                .toString();

        Assert.assertEquals(expected, result);

    }

    @Test
    public void verificaSaldoCanalInvalidoFalha(){
        String expected = "[{message=Formato do canal inválido, necessário até 3 caracteres., field=consultarSaldo.canal}]";

        String result = ConsultaSaldoBuildRequest.requisicao("XPTO","0101","139354", "USER")
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("constraints")
                .toString();

        Assert.assertEquals(expected, result);

    }

    @Test
    public void verificaSaldoComAgenciaDiferente(){
        String expected = "Erro ao consultar saldo";

        String result = ConsultaSaldoBuildRequest.requisicao("IB","0116","139354", "USER")
                .then()
                .assertThat()
                .statusCode(500)
                .extract()
                .body()
                .path("message");

        Assert.assertEquals(expected, result);
    }
    @Test
    public void verificaSaldoComNomeInvalido(){
        String expected = "[{message=Formato do usuario inválido., field=consultarSaldo.usuario}]";

        String result = ConsultaSaldoBuildRequest.requisicao("IB","0116","139354", "USUARIO")
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .body()
                .path("constraints")
                .toString();

        Assert.assertEquals(expected, result);
    }
}
