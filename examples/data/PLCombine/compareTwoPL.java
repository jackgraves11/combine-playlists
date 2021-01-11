package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

import static data.PLCombine.Main.spotifyApi;

public class compareTwoPL {
  String firstPLID;
  String secondPLID;

  public compareTwoPL(String firstID, String secondID) {
    firstPLID = firstID;
    secondPLID = secondID;
  }

  public void start() {
    GetPlaylistRequest getPlaylistRequestOne = spotifyApi.getPlaylist(firstPLID)
//          .fields("description")
//          .market(CountryCode.SE)
//          .additionalTypes("track,episode")
      .build();

    GetPlaylistRequest getPlaylistRequestTwo = spotifyApi.getPlaylist(firstPLID)
//          .fields("description")
//          .market(CountryCode.SE)
//          .additionalTypes("track,episode")
      .build();
    getPlaylist_Sync(getPlaylistRequestOne);
    getPlaylist_Sync(getPlaylistRequestTwo);
  }

  public static void getPlaylist_Sync(GetPlaylistRequest gPLR) {
    try {
      final Playlist playlist = gPLR.execute();

      System.out.println("Name: " + playlist.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
