import java.util.*;

import org.json.JSONObject;

import Arifpay.java.plugin.ArifPay;
public class App {
    public static void main(String[] args) throws Exception {
        ArifPay arifpay= new ArifPay("G8FbER8zZ9uco5tLuVnNKycJwXzvJTyo", "2025-02-01T03:45:27");
        String jsonString = "{"
            + "\"cancelUrl\": \"https://example.com\","
            + "\"phone\":\"251944294981\","
            + "\"email\":\"natnael@arifpay.net\","
             + "\"successUrl\": \"http://error.com\","
            + "\"errorUrl\": \"http://error.com\","
            + "\"notifyUrl\": \"https://gateway.arifpay.net/test/callback\","
            + "\"la\": \"http://example.com\","
            + "\"paymentMethods\": [\"TELEBIRR\"],"
            + "\"expireDate\": \"2025-02-01T03:45:27\","
            + "\"items\": ["
                + "{"
                    + "\"name\": \"ሙዝ\","
                    + "\"quantity\": 1,"
                    + "\"price\": 1,"
                    + "\"description\": \"Fresh Corner preimuim Banana.\","
                    + "\"image\": \"https://4.imimg.com/data4/KK/KK/GLADMIN-/product-8789_bananas_golden-500x500.jpg\""
                + "},"
                + "{"
                    + "\"name\": \"ሙዝ\","
                    + "\"quantity\": 1,"
                    + "\"price\": 1,"
                    + "\"description\": \"Fresh Corner preimuim Banana.\","
                    + "\"image\": \"https://4.imimg.com/data4/KK/KK/GLADMIN-/product-8789_bananas_golden-500x500.jpg\""
                + "}"
            + "],"
            + "\"beneficiaries\": ["
                + "{"
                    + "\"accountNumber\": \"01320811436100\","
                    + "\"bank\": \"AWINETAA\","
                    + "\"amount\": 2.0"
                + "}"
            + "],"
            + "\"lang\": \"EN\""
        + "}";
        JSONObject jsonObject = new JSONObject(jsonString);
        System.out.println(arifpay.Make_payment(jsonObject));
    }
}
