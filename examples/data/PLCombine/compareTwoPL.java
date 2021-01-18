package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.*;

import java.util.ArrayList;

/**
 * Class that compares two different playlists.
 * TODO Pretty excessive to have this in its own class, probably just add to sharedMethods.
 */

public class compareTwoPL {

  /**
   * Finds and returns the shared tracks of two inputted playlists.
   * @param firstPlaylist ArrayList of tracks of first playlist
   * @param secondPlaylist ArrayList of tracks of second playlist
   * @return ArrayList of Track instances of the tracks that are in both playlists
   */
  public ArrayList<Track> getSharedTracks(ArrayList<Track> firstPlaylist, ArrayList<Track> secondPlaylist) {
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
