package data.PLCombine;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.specification.Track;

import java.net.URI;
import java.util.ArrayList;

/**
 * Class where general info and instances are stored and where the method calls start.
 */
public class Main {

  /** Registered under jack's spotify account, use to access API */
  public static final String clientId = "a7966f4fb0cf407a80156cf2e4b3c426";

  /** Registered under jack's spotify account, use to access API */
  public static final String clientSecret = "093f82121ba1441eadad3a01d394b2fa";

  /** Where the website would redirect to (essentially website homepage) */
  public static final URI redirectUri = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");

  /** Playlist IDs: */
  public static final String rapCaviarID = "37i9dQZF1DX0XUsuxWHRQd";
  public static final String mostNecessaryID = "37i9dQZF1DX2RxBh64BHjQ";
  public static final String jackID = "1pFX4j1JdA1zfwrehY9y4m";
  public static final String grayID = "6T8maOrjE6hW0vwlcVuZkp";

  /**
   * Spotify API that holds clientIDs and redirect URI. Used to access the spotify API.
   */
  public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();

  public static void main(String[] args) {
    ClientCredentials.clientCredentials_Sync();
    compareTwoPL PlaylistComparer = new compareTwoPL();
    ArrayList<Track> sharedTracks = PlaylistComparer.getSharedTracks(jackID, grayID);
    PlaylistComparer.printTracks(sharedTracks);
  }
}
