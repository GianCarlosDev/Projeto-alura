package com.conversor.api;

import java.net.http.*;
import java.net.URI;
import com.google.gson.*;

public class ServicoDeCambio {

    private static final String CHAVE_API = "5b5d106f95799eff0c366caf";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/" + CHAVE_API + "/pair/";

    public double buscarTaxa(String de, String para) throws Exception {
        String url = URL_BASE + de + "/" + para;

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

        JsonObject json = JsonParser.parseString(resposta.body()).getAsJsonObject();
        return json.get("conversion_rate").getAsDouble();
    }
}
