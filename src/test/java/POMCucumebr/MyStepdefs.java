package POMCucumebr;

import java.net.URL;
import org.junit.Assert;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import io.restassured.specification.RequestSpecification;


public class MyStepdefs {

    private static final String BASE_URL = "http://api.citybik.es/v2/networks";
    private static final String URL = "http://api.citybik.es/v2/networks/visa-frankfurt";

    @Given("user is on correct webpage")
    public void user_is_on_correct_webpage() {
        RestAssured.baseURI = BASE_URL;
        //Request object
        RequestSpecification request = given();
        //Response object
        Response response = request.get(BASE_URL);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().prettyPrint();
        System.out.println("Responsebody:" + responseBody);
        //verify the status code
        Assert.assertEquals(200, statusCode);
    }

    @When("city Frankfurt is in Germany")
    public void city_Frankfurt_is_in_Germany() throws IOException, JSONException {
        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // printing the json result
            System.out.println("json:" + response.toString());
            JSONObject jsonObj = new JSONObject(response.toString());
            String Actual_city = (String) jsonObj.getJSONObject("network").getJSONObject("location").get("city");
            String Expected_city = "Frankfurt";
            //verify the actual and expected city
            Assert.assertEquals("Actual and Expected city are same", Actual_city, Expected_city);
        }
    }

    @Then("verify Frankfurt is in Germany")
    public void verify_Frankfurt_is_in_Germany() throws IOException, JSONException {
        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("json:" + response.toString());
            JSONObject jsonObj = new JSONObject(response.toString());
            String Actual_country = (String) jsonObj.getJSONObject("network").getJSONObject("location").get("country");
            String Expected_country = "DE";
            //verifying actual and expected country
            Assert.assertEquals("Actual and Expected country are same", Actual_country, Expected_country);
            String Actual_city = (String) jsonObj.getJSONObject("network").getJSONObject("location").get("city");
            String Expected_city = "Frankfurt";
            //verify the actual and expected city
            Assert.assertEquals("Actual and Expected city are same", Actual_city, Expected_city);

        }
        }
        @And("it should return corresponding latitude and longitude")
        public void verify_the_corresponding_latitude_and_longitude() throws IOException, JSONException
        {
            URL obj = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            //get the response code
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("json:" + response.substring(0,200).concat("}"));
                JSONObject jsonObj = new JSONObject(response.toString());
                double Actual_latitude = (double) jsonObj.getJSONObject("network").getJSONObject("location").get("latitude");
                System.out.println("Actual_latitude:"+Actual_latitude);
                Double Expected_latitude = 50.1072;
               //verifying actual and expected latitude
                Assert.assertTrue("Check Actual_latitude and Expected_latitude:", Actual_latitude == Expected_latitude);
                double Actual_longitude = (double) jsonObj.getJSONObject("network").getJSONObject("location").get("longitude");
                Double Expected_longitude = 8.66375;
                //verifying actual and expected longitude
                System.out.println("Actual_longitude: "+Actual_longitude);
                //verifying actual and expected latitude
                Assert.assertTrue("Check Actual_longitude and Expected_longitude:", Actual_longitude == Expected_longitude);
            }

        }

    }





