package data.Jack;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;

import java.net.URI;

public class firstExample {
  public static final String clientId = "a7966f4fb0cf407a80156cf2e4b3c426";
  public static final String clientSecret = "093f82121ba1441eadad3a01d394b2fa";
  public static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");
  public static final String jefferyID = "7EpUpNUkkEGnaCvkcn1j4H";

  public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();

  public static void getJeffrey() {
    ClientCredentials.clientCredentials_Sync();
    //ClientCredentials.clientCredentials_Async();
    GetJeffery.getAlbum_Sync();
    //GetJeffery.getAlbum_Async();
  }

  public static void main(String[] args) {
    getJeffrey();
    //TryingAuthorization.authorizationCodeUri_Sync();
  }
}
