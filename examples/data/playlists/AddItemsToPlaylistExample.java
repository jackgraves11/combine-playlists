package data.playlists;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AddItemsToPlaylistExample {
  private static final String accessToken = "BQBiXGiVeRq5546EuctpFb743D_nsdSY7DHhis7C2A5glwh8fIwr4QWS-pehcS4GRNbaOFATD_mLvkdAl0RpVluIJLGKNebyVnDtBsv11vdYY4rO9GZtv5eQJK79H3rG5n9iDdIiG-usULL3UttSK1PhvHiYihyyam4nBJsCDqBzl0ik8ezRieRIFCBiVD0eFO7ExBfixgRTjSN08FRSuYGPnU5_OpGk06qaS4P9j7yG4a3xQw";
  private static final String playlistId = "1Y6NlSgmkffa33Rb7TJg1B";
  private static final String[] uris = new String[]{"spotify:track:5yZQiE2vZFJYsB4BgOOgIk", "spotify:track:6ZFbXIJkuI1dVNWvzJzown"};

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
    .addItemsToPlaylist(playlistId, uris)
//          .position(0)
    .build();

  public static void addItemsToPlaylist_Sync() {
    try {
      final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void addItemsToPlaylist_Async() {
    try {
      final CompletableFuture<SnapshotResult> snapshotResultFuture = addItemsToPlaylistRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final SnapshotResult snapshotResult = snapshotResultFuture.join();

      System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    addItemsToPlaylist_Sync();
    //addItemsToPlaylist_Async();
  }
}
