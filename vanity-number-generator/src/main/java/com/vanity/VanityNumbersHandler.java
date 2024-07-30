package com.vanity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vanity.service.NumberToLettersMap;
import com.vanity.service.VanityDatabaseService;
import com.vanity.service.VanityDictionary;
import com.vanity.service.VanityGenerator;

import java.util.List;

public class VanityNumbersHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        VanityGenerator vanityGenerator = new VanityGenerator(new NumberToLettersMap(), new VanityDictionary(), new VanityDatabaseService());
        JsonObject requestObj = JsonParser.parseString(input.getBody()).getAsJsonObject();
        String phoneNumber = requestObj.get("phoneNumber").getAsString();

        List<String> vanityNumbers = null;
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        try {
            vanityNumbers = vanityGenerator.saveVanityNumbers(phoneNumber);
        } catch (Exception e) {
            // could not handle specific exceptions like 4XX and 5XX
            response.setStatusCode(500);
            response.setBody(new Gson().toJson(e.getMessage()));
            return response;
        }
        response.setStatusCode(200);
        response.setBody(new Gson().toJson(vanityNumbers));
        return response;
    }
}
