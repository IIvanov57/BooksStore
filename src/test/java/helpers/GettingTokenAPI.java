package helpers;

import models.ResponseAuth;

public class GettingTokenAPI {

    public static String getTokenForClient(ResponseAuth response) {
        return response.getToken();
    }
}
