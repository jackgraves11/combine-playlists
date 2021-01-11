package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

import static data.PLCombine.Main.spotifyApi;


public class ClientCredentials {

  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
    .build();

  public static void clientCredentials_Sync() {
    try {
      final com.wrapper.spotify.model_objects.credentials.ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
      System.out.println(spotifyApi.getAccessToken());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
