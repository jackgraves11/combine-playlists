package data.PLCombine;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;

import java.net.URI;

public class Main {

  public static final String clientId = "a7966f4fb0cf407a80156cf2e4b3c426";
  public static final String clientSecret = "093f82121ba1441eadad3a01d394b2fa";
  public static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");

  public static final String rapCaviarID = "37i9dQZF1DX0XUsuxWHRQd";
  public static final String mostNecessaryID = "37i9dQZF1DX2RxBh64BHjQ";

  public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();

  public static void main(String[] args) {
    ClientCredentials.clientCredentials_Sync();
    compareTwoPL compareTwoPlaylist = new compareTwoPL(rapCaviarID, mostNecessaryID);
    compareTwoPlaylist.start();
  }
}
