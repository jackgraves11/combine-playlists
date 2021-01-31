package data.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetArtistsTopTracksExample {
  private static final String accessToken = "BQCMWlGSGW4cOPm4cjRNXQYS-cynBaNZA-ok8od7DaLPDtwi1XSh3u_dhhQAgOWMhFKWWyHKiyAoLvy-y4v7AkduST-YRPC2UH1SvEQPP2sA4Lt7lxEZ6mQN9uYlNYMtxFWxSKaQwNDlTVh0-0RBlfbulAOCTqA4IeTNz8tZeZY-ImV6nMPVSIQ";
  private static final String id = "0Y5tJX1MQlPlqiwlOH1tJY";
  private static final CountryCode countryCode = CountryCode.US;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetArtistsTopTracksRequest getArtistsTopTracksRequest = spotifyApi
    .getArtistsTopTracks(id, countryCode)
    .build();

  public static void getArtistsTopTracks_Sync() {
    try {
      final Track[] tracks = getArtistsTopTracksRequest.execute();

      System.out.println("Length: " + tracks.length);
      for (Track track : tracks) {
        System.out.println(track.getName());
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getArtistsTopTracks_Async() {
    try {
      final CompletableFuture<Track[]> artistsFuture = getArtistsTopTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Track[] tracks = artistsFuture.join();

      System.out.println("Length: " + tracks.length);
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getArtistsTopTracks_Sync();
    //getArtistsTopTracks_Async();
  }
}
