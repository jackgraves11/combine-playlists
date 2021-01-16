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
    ArrayList<Track> firstPlaylist = sharedMethods.getPLTracks(pLIDOne);
    ArrayList<Track> secondPlaylist = sharedMethods.getPLTracks(pLIDTwo);
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
   * Prints the names of the tracks in the given input list.
   * @param trackList ArrayList of Track instances
   */
  public void printTracks(ArrayList<Track> trackList) {
    for (Track t : trackList) {
      System.out.println(t.getName());
    }
  }
}
