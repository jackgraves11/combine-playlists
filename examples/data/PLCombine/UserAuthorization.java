package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import org.apache.hc.core5.http.ParseException;
import static data.PLCombine.Main.spotifyApi;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

/**
 * Class that gets a user to log in with their spotify account.
 * TODO Figure out this
 */

public class UserAuthorization {

  public void execute() {
    AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
      .scope("playlist-modify-public")
      .build();
    URI uri = authorizationCodeUriRequest.execute();
    System.out.println(uri.toString());
    Scanner myObj = new Scanner(System.in);
    System.out.println("Enter code: ");
    String code = myObj.nextLine();
    AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
      .build();
    try {
      AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
      spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
      spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
