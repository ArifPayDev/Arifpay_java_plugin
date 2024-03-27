package Arifpay.java.plugin;
import java.util.*;
import java.net.*;
import java.io.*;
import org.json.*;

public class ArifPay {
    private String API_key;
    private String expireDate;
    public ArifPay(String API_key, String expireDate) {
        this.API_key = API_key;
        this.expireDate = expireDate;
    }

    // public JSONObject change_to_number(JSONObject data) {
    //     JSONArray items = data.getJSONArray("items");
    //     for (int i = 0; i < items.length(); i++) {
    //         JSONObject item = items.getJSONObject(i);
    //         item.put("quantity", Integer.parseInt(item.getString("quantity")));
    //         item.put("price", Double.parseDouble(item.getString("price")));
    //     }
    //     JSONArray beneficiaries = data.getJSONArray("beneficiaries");
    //     for (int i = 0; i < beneficiaries.length(); i++) {
    //         JSONObject benef = beneficiaries.getJSONObject(i);
    //         benef.put("amount", Double.parseDouble(benef.getString("amount")));
    //     }
    //     return data;
    // }

    public JSONObject Make_payment(JSONObject payment_info) {
        List<String> requiredFields = Arrays.asList("cancelUrl", "successUrl", "errorUrl", "notifyUrl", "paymentMethods", "items", "beneficiaries");
        List<String> missingFields = new ArrayList<>();
        for (String field : requiredFields) {
            if (!payment_info.has(field)) {
                missingFields.add(field);
            }
        }
        if (missingFields.size() > 0) {
            JSONObject missingFieldsObj = new JSONObject();
            for (String field : missingFields) {
                missingFieldsObj.put(field, field + " is a required field please enter this field");
            }
            return missingFieldsObj;
        }
        payment_info.put("nonce", UUID.randomUUID().toString());
        payment_info.put("expireDate", this.expireDate);
        // payment_info = this.change_to_number(payment_info);
        System.out.println(payment_info);

        if (missingFields.size() == 0) {
            String url = "https://gateway.arifpay.org/api/sandbox/checkout/session";
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("x-arifpay-key", this.API_key);
                con.setDoOutput(true);
                OutputStream os = con.getOutputStream();
                os.write(payment_info.toString().getBytes());
                os.flush();
                os.close();

                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return new JSONObject(response.toString());
                } else {
                    JSONObject error = new JSONObject();
                    error.put("status", responseCode);
                    error.put("message", con.getResponseMessage());
                    return error;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
