package data.Jack;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static data.Jack.firstExample.jefferyID;
import static data.Jack.firstExample.spotifyApi;

public class GetJeffery {

  private static final GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(jefferyID)
//          .market(CountryCode.SE)
    .build();

  public static void getAlbum_Sync() {
    try {
      final Album album = getAlbumRequest.execute();

      System.out.println("Name: " + album.getName());
      Paging<TrackSimplified> tracks = album.getTracks();
      TrackSimplified[] realTracks = tracks.getItems();
      for (TrackSimplified t : realTracks) {
        System.out.println(t.getName());
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getAlbum_Async() {
    try {
      final CompletableFuture<Album> albumFuture = getAlbumRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Album album = albumFuture.join();

      System.out.println("Name: " + album.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }
}
