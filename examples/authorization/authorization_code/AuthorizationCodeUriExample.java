package authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AuthorizationCodeUriExample {
  private static final String clientId = "a7966f4fb0cf407a80156cf2e4b3c426";
  private static final String clientSecret = "093f82121ba1441eadad3a01d394b2fa";
  private static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();
  private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//          .state("x4xkmn9pu3j6ukrs8n")
//          .scope("user-read-birthdate,user-read-email")
//          .show_dialog(true)
    .build();

  public static void authorizationCodeUri_Sync() {
    final URI uri = authorizationCodeUriRequest.execute();

    System.out.println("URI: " + uri.toString());
  }

  public static void authorizationCodeUri_Async() {
    try {
      final CompletableFuture<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final URI uri = uriFuture.join();

      System.out.println("URI: " + uri.toString());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    authorizationCodeUri_Sync();
    authorizationCodeUri_Async();
  }
}
