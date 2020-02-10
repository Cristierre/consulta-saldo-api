package br.com.consulta.saldo.caller;

import br.com.consulta.saldo.requestspecification.ConsultaSaldoRequestSpecification;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class ConsultaSaldoBuildRequest {


    public static Response requisicao(String canal, String nroAgencia, String nroConta, String usuario){
           return  given().
                   spec(ConsultaSaldoRequestSpecification.get())
                   .pathParam("nroAgencia", nroAgencia)
                   .pathParam("nroConta", nroConta)
                   .header("canal", canal)
                   .header("usuario", usuario)
                   .when()
                   .get();
    }
}
