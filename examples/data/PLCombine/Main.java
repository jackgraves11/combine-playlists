package data.PLCombine;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.specification.Track;

import java.net.*;
import java.util.ArrayList;
import java.util.Set;

import static data.PLCombine.SharedMethods.getPLTracks;

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
    //ClientCredentials.clientCredentials_Sync();
    UserAuthorization userAuthorization = new UserAuthorization();
    userAuthorization.execute();
    String accessToken = spotifyApi.getAccessToken();
    String userID = "31yhmkso6gm2zfjteputdsjbbrem"; //Jack's User ID
    CreatePlaylist createPlaylist = new CreatePlaylist();
    Set<Track> playlist = createPlaylist.getPlaylist(jackID, grayID, 1000);
    System.out.println(playlist.size());
    int i = 0;
    for (Track track : playlist) {
      if (i == 93) {
        System.out.println(track.getName());
      }
      i += 1;
    }
    AddPlaylistToUserLib addPlaylistToUserLib = new AddPlaylistToUserLib();
    addPlaylistToUserLib.execute(accessToken, userID, playlist, "It's Necessary");
    //Waiter waiter = new Waiter();
    //waiter.main();
    //PlaylistComparer.printTracks(sharedTracks);
  }
}
