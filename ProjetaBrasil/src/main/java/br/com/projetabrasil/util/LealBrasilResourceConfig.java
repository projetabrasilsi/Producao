package br.com.projetabrasil.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("rest")
//http://localhost:8080/LealBrasil/[rest]
public class LealBrasilResourceConfig extends ResourceConfig {
    public LealBrasilResourceConfig() {
        packages("org.foo.rest;org.bar.rest");
    }
}
