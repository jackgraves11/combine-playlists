package data.PLCombine;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.specification.Track;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
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

  public static final SharedMethods sharedMethods = new SharedMethods();

  /** Playlist IDs: */
  public static final String rapCaviarID = "37i9dQZF1DX0XUsuxWHRQd";
  public static final String mostNecessaryID = "37i9dQZF1DX2RxBh64BHjQ";
  public static final String jackID = "1pFX4j1JdA1zfwrehY9y4m";
  public static final String grayID = "6T8maOrjE6hW0vwlcVuZkp";
  public static final String avaID = "5VFe1i2yoUMhlZ1G2SPqOM";
  public static final String testPL1ID = "0j1AolmgBCVpc8JUgAkQ1o";
  public static final String testPL2ID = "2NUfUmvH4j0UgQytZCiaGA";

  /**
   * Spotify API that holds clientIDs and redirect URI. Used to access the spotify API.
   */
  public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();

  public static void main(String[] args) throws Exception {
    //UserAuthorization.authorizeUser();
    ClientCredentials.clientCredentials_Sync();
    compareTwoPL PlaylistComparer = new compareTwoPL();
    /**
    ArrayList<Track> sharedTracks = PlaylistComparer.getSharedTracks(jackID, grayID);
    PlaylistArtistsAnalyzer pAA = new PlaylistArtistsAnalyzer(sharedTracks);
    PlaylistArtistsAnalyzer.SimpleArtistFreqNode[] topArtists = pAA.topArtists();
    pAA.printArtistsTracks(topArtists[0].getArtist());
     */
    ArrayList<Track> jackTracks = PlaylistComparer.getSharedTracks(jackID, jackID);
    ArrayList<Track> grayTracks = PlaylistComparer.getSharedTracks(grayID, grayID);
    ArtistRanker artistRanker = new ArtistRanker();
    ArrayList<ArtistRanker.SimpleArtistScoreNode> scores = artistRanker.artistRanks(jackTracks, grayTracks);
    for (ArtistRanker.SimpleArtistScoreNode node : scores) {
      System.out.print(node.getArtist().getName() + ": ");
      System.out.println(node.getScore());
    }
    //Waiter waiter = new Waiter();
    //waiter.main();
    //PlaylistComparer.printTracks(sharedTracks);
  }
}
