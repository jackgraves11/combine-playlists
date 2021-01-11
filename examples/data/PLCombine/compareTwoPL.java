package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;

import static data.PLCombine.Main.*;

/**
 * Class that compares two different playlists.
 */

public class compareTwoPL {

  /**
   * Finds and returns the shared tracks of two inputted playlists.
   * @param pLIDOne Playlist ID of first playlist
   * @param pLIDTwo Playlist ID of second playlist
   * @return ArrayList of Track instances of the tracks that are in both playlists
   */
  public ArrayList<Track> getSharedTracks(String pLIDOne, String pLIDTwo) {
    ArrayList<Track> firstPlaylist = getPlayListTracks(pLIDOne);
    ArrayList<Track> secondPlaylist = getPlayListTracks(pLIDTwo);
    ArrayList<Track> combinedPlaylist = new ArrayList<>();
    for (Track t : firstPlaylist) {
      if (t != null){
        for (Track t2 : secondPlaylist) {
          if (t2 != null) {
            if (t.equals(t2)) {
              combinedPlaylist.add(t);
              break;
            }
          }
        }
      }
    }
    return combinedPlaylist;
  }

  /**
   * Helper method for getSharedTracks that returns a ArrayList of all the tracks from the inputted playlist ID.
   * It accesses the spotify API to do so.
   * @param pLID Playlist ID
   * @return ArrayList of all the tracks in the inputted playlist.
   */
  private ArrayList<Track> getPlayListTracks(String pLID) {
    int offset = 0;
    int plLength = 0;
    ArrayList<Track> allTracks = new ArrayList<>();
    try {
      while (offset == 0 || offset < plLength) {
        GetPlaylistsItemsRequest pLRequest = initializePLItemsRequest(pLID, offset);
        Paging<PlaylistTrack> playlistPager = pLRequest.execute();
        PlaylistTrack[] pLTracks = playlistPager.getItems();
        for (PlaylistTrack pLTrack : pLTracks) {
          allTracks.add((Track) pLTrack.getTrack()); //Check Cast
        }
        plLength = playlistPager.getTotal();
        offset = offset + 100;
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return allTracks;
  }

  /**
   * Helper method for getPlaylistTracks that returns an object that can get the items of a playlist.
   * @param pLID Playlist ID
   * @param offset Spotify API only returns max of 100 songs at a time, so offset is the starting point in the playlist
   *               for the songs you want to get. Ex A playlist of 400 songs: offset = 100: gets tracks 100-199,
   *               offset = 350: gets tracks 350-400.
   * @return A GetPlaylistItemsRequest object that gets the items of a playlist when you call execute() on it.
   */
  private GetPlaylistsItemsRequest initializePLItemsRequest(String pLID, int offset) {
    return spotifyApi.getPlaylistsItems(pLID)
      .offset(offset)
      .build();
  }

  /**
   * Prints the names of the tracks in the given input list.
   * @param trackList ArrayList of Track instances
   */
  public void printTracks(ArrayList<Track> trackList) {
    for (Track t : trackList) {
      System.out.println(t.getName());
    }
  }
}
