package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;

import static data.PLCombine.Main.spotifyApi;

public class SharedMethods {

  public static ArrayList<Track> getPLTracks(String pLID) {
    return getPlayListTracks(pLID);
  }

  /**
   * Helper method for getPlaylistTracks that returns an object that can get the items of a playlist.
   * @param pLID Playlist ID
   * @param offset Spotify API only returns max of 100 songs at a time, so offset is the starting point in the playlist
   *               for the songs you want to get. Ex A playlist of 400 songs: offset = 100: gets tracks 100-199,
   *               offset = 350: gets tracks 350-400.
   * @return A GetPlaylistItemsRequest object that gets the items of a playlist when you call execute() on it.
   */
  private static GetPlaylistsItemsRequest initializePLItemsRequest(String pLID, int offset) {
    return spotifyApi.getPlaylistsItems(pLID)
      .offset(offset)
      .build();
  }

  /**
   * Returns a ArrayList of all the tracks from the inputted playlist ID.
   * It accesses the spotify API to do so.
   * @param pLID Playlist ID
   * @return ArrayList of all the tracks in the inputted playlist.
   */
  private static ArrayList<Track> getPlayListTracks(String pLID) {
    int offset = 0;
    int plLength = 0;
    ArrayList<Track> allTracks = new ArrayList<>();
    try {
      while (offset == 0 || offset < plLength) {
        GetPlaylistsItemsRequest pLRequest = initializePLItemsRequest(pLID, offset);
        Paging<PlaylistTrack> playlistPager = pLRequest.execute();
        PlaylistTrack[] pLTracks = playlistPager.getItems();
        for (PlaylistTrack pLTrack : pLTracks) {
          Track currentTrack = (Track) pLTrack.getTrack();
          if (currentTrack != null && currentTrack.getId() != null) {
            allTracks.add((Track) pLTrack.getTrack()); //Check Cast
          }
        }
        plLength = playlistPager.getTotal();
        offset = offset + 100;
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return allTracks;
  }
}
