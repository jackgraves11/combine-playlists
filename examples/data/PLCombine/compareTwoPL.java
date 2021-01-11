package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

import static data.Jack.firstExample.jefferyID;
import static data.PLCombine.Main.*;

public class compareTwoPL {

  private static final GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(rapCaviarID)
//          .fields("description")
//          .market(CountryCode.SE)
//          .additionalTypes("track,episode")
    .build();

  private static final GetPlaylistRequest gPLR = spotifyApi.getPlaylist(mostNecessaryID)
    .build();


  public static void getPlaylist_Sync() {
    try {
      final Playlist playlist = gPLR.execute();

      System.out.println("Name: " + playlist.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

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
}
