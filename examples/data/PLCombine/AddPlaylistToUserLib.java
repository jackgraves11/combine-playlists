package data.PLCombine;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Set;

public class AddPlaylistToUserLib {

  public Playlist execute(String accessToken, String userID, Set<Track> tracks, String pLName) {
    SpotifyApi spotifyApi = new SpotifyApi.Builder()
      .setAccessToken(accessToken)
      .build();
    CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(userID, pLName)
      .collaborative(false)
      .public_(true)
      .description("Test Jack and Gray Playlists Ertons")
      .build();
    try {
      Playlist playlist = createPlaylistRequest.execute();
      String[] uRIs = getURIs(tracks);
      int i = 0;
      while (i < uRIs.length) {
        String[] currURIs = new String[Math.min(93, uRIs.length - i)];
        for (int counter = 0; counter < currURIs.length; counter += 1) {
          currURIs[counter] = uRIs[counter + i];
        }
        AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
          .addItemsToPlaylist(playlist.getId(), currURIs)
          .position(i)
          .build();
        addItemsToPlaylistRequest.execute();
        i += 93;
      }
      return playlist;
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return null;
  }

  private String[] getURIs(Set<Track> tracks) {
    String[] result = new String[tracks.size()];
    int i = 0;
    for (Track track : tracks) {
      result[i] = track.getUri();
      i += 1;
    }
    return result;
  }
}
