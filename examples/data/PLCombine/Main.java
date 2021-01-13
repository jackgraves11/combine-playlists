package data.PLCombine;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.specification.Track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
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
  public static final String avaID = "5VFe1i2yoUMhlZ1G2SPqOM";

  /**
   * Spotify API that holds clientIDs and redirect URI. Used to access the spotify API.
   */
  public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();

  public static void main(String[] args) throws IOException {
    //UserAuthorization.authorizeUser();
    ClientCredentials.clientCredentials_Sync();
    compareTwoPL PlaylistComparer = new compareTwoPL();
    ArrayList<Track> sharedTracks = PlaylistComparer.getSharedTracks(jackID, jackID);
    PlaylistArtistsAnalyzer pAA = new PlaylistArtistsAnalyzer(sharedTracks);
    pAA.topArtists();
    //PlaylistComparer.printTracks(sharedTracks);
  }
}
