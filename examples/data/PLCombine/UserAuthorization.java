package data.PLCombine;

import com.google.gson.JsonObject;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static data.PLCombine.Main.spotifyApi;

/**
 * Class that gets a user to log in with their spotify account.
 * TODO Figure out this
 */

public class UserAuthorization {
/**
  public static void authorizeUser() throws Exception {
    AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//      .state("x4xkmn9pu3j6ukrs8n")
//          .scope("user-read-birthdate,user-read-email")
//          .show_dialog(true)
      .build();

    URI uri = authorizationCodeUriRequest.execute();
    waitForResponse(uri.toString(), "Hello", 10000);
    //getAccessToken(uri);

    //System.out.println("URI: " + uri.toString());
  }

  private static void getAccessToken(URI uri) throws Exception {
    String url = uri.toString();
    URL obj = uri.toURL();
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    int responseCode = con.getResponseCode();
    System.out.println("\nSending Get Request to URL: " + url);
    System.out.println("Response Code: " + responseCode);
    BufferedReader in = new BufferedReader(
      new InputStreamReader(con.getInputStream())
    );
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    //print in String
    System.out.println(response.toString());
    AuthorizationCode
  }
 */







}
