package data.PLCombine;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.specification.Track;

import java.net.URI;
import java.util.ArrayList;

public class Main {

  public static final String clientId = "a7966f4fb0cf407a80156cf2e4b3c426";
  public static final String clientSecret = "093f82121ba1441eadad3a01d394b2fa";
  public static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");

  public static final String rapCaviarID = "37i9dQZF1DX0XUsuxWHRQd";
  public static final String mostNecessaryID = "37i9dQZF1DX2RxBh64BHjQ";
  public static final String jackID = "1pFX4j1JdA1zfwrehY9y4m";
  public static final String grayID = "6T8maOrjE6hW0vwlcVuZkp";

  public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();

  public static void main(String[] args) {
    ClientCredentials.clientCredentials_Sync();
    compareTwoPL PlaylistComparer = new compareTwoPL();
    ArrayList<Track> sharedTracks = PlaylistComparer.getCombinedTracks(jackID, grayID);
    PlaylistComparer.printTracks(sharedTracks);
  }
}
