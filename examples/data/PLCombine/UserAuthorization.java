package data.PLCombine;

import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

import static data.PLCombine.Main.spotifyApi;

public class UserAuthorization {

  public static void authorizeUser() throws IOException {
    AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//      .state("x4xkmn9pu3j6ukrs8n")
//          .scope("user-read-birthdate,user-read-email")
//          .show_dialog(true)
      .build();

    URI uri = authorizationCodeUriRequest.execute();
    //getAccessToken(uri);

    //System.out.println("URI: " + uri.toString());
  }

  private static void getAccessToken(URI uri) throws Exception {
    test("http://example.com/servlet/ReverseServlet", "Reverse Me");
  }

  public static void test(String url, String input) throws Exception {

    String stringToReverse = URLEncoder.encode(url, "UTF-8");

    URL url2 = new URL(url);
    URLConnection connection = url2.openConnection();
    connection.setDoOutput(true);

    OutputStreamWriter out = new OutputStreamWriter(
      connection.getOutputStream());
    out.write("string=" + stringToReverse);
    out.close();

    BufferedReader in = new BufferedReader(
      new InputStreamReader(
        connection.getInputStream()));
    String decodedString;
    while ((decodedString = in.readLine()) != null) {
      System.out.println(decodedString);
    }
    in.close();
  }
}
