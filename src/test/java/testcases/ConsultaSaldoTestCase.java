package testcases;


import br.com.consulta.saldo.builder.ContaSalario;
import br.com.consulta.saldo.caller.ConsultaSaldoBuildRequest;

import br.com.consulta.saldo.database.ConnectionDataBase;
import org.junit.Assert;
import org.junit.Test;

import static org.testng.Assert.assertEquals;


public class ConsultaSaldoTestCase {

    @Test
    public void respostaRequisicao(){
        String expected = "{\"fontePagadora\":\"ROAL IND METALURGICA\",\"saldo\":0,\"saldoBloqueado\":0}";

        String result = ConsultaSaldoBuildRequest.requisicao("IB","0101","139354", "USER")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .asString();

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
        System.out.println(result);
        Assert.assertEquals(expected, result);

    }
}
