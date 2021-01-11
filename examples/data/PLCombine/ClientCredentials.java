package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

import static data.PLCombine.Main.spotifyApi;

/**
 * Class that obtains a token from the spotify API to access items that are not private to a user.
 * It does not require anybody to log in to their spotify account.
 * Most of code taken from ClientCredentials example.
 */

public class ClientCredentials {

  /**
   * A ClientCredentialRequest object that gets a token from the API when execute() is called on it.
   */
  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
    .build();

  /**
   * Gets the token from spotify API and sets the SpotifyAPI instance in Main.java to hold the token string.
   * If you want to get the token now you say spotifyApi.getAccessToken().
   */
  public static void clientCredentials_Sync() {
    try {
      final com.wrapper.spotify.model_objects.credentials.ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

      //System.out.println("Expires in: " + clientCredentials.getExpiresIn());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
