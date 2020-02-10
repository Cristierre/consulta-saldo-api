package br.com.consulta.saldo.requestspecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class ConsultaSaldoRequestSpecification {
    private static String BASEURI = "https://contasalario-saldo-api-hom.uat.rs-1.paas.sicredi.net/contasalario-saldo-api";
    private static String BASEPATH = "v1/saldos/{nroAgencia}/{nroConta}";

    public static RequestSpecification get(){
        return new RequestSpecBuilder()
                .setConfig(new RestAssuredConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                .setContentType(JSON)
                .setBaseUri(BASEURI)
                .setBasePath(BASEPATH)
                .log(LogDetail.ALL)
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
