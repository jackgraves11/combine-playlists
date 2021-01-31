package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import static data.PLCombine.Main.grayID;
import static data.PLCombine.Main.jackID;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static data.PLCombine.Main.spotifyApi;

public class TestCreatePlaylist {

  @Test
  public void testOne() {
    ClientCredentials.clientCredentials_Sync();
    CreatePlaylist playlistCreator = new CreatePlaylist();
    Set<Track> finalPlaylist = playlistCreator.getPlaylist(jackID, grayID, 200);
    assertTrue(finalPlaylist.size() == 200);
    compareTwoPL playlistComparer = new compareTwoPL();
    ArrayList<Track> sharedTracks = playlistComparer.getSharedTracks(SharedMethods.getPLTracks(jackID), SharedMethods.getPLTracks(grayID));
    assertPlaylistContains(finalPlaylist, sharedTracks);
  }

  public static void testTwo() {
    UserAuthorization userAuthorization = new UserAuthorization();
    userAuthorization.execute();
    String accessToken = spotifyApi.getAccessToken();
    String userID = "31yhmkso6gm2zfjteputdsjbbrem";
    CreatePlaylist createPlaylist = new CreatePlaylist();
    Set<Track> playlist = createPlaylist.getPlaylist(jackID, grayID, 200);
    System.out.println(playlist.size());
    AddPlaylistToUserLib addPlaylistToUserLib = new AddPlaylistToUserLib();
    Playlist createdPL = addPlaylistToUserLib.execute(accessToken, userID, playlist, "It's Necessary");
    ArrayList<Track> obtained = SharedMethods.getPLTracks(createdPL.getId());
    Iterator<Track> other = obtained.iterator();
    for (Track track : playlist) {
      if (other.hasNext()) {
        Track otherTrack = other.next();
        if (!otherTrack.equals(track)) {
          System.out.println(track.getName());
          System.out.println(otherTrack.getName());
        }
      } else {
        System.out.println(track.getName());
      }
    }
  }

  private void assertPlaylistContains(Set<Track> playlist, ArrayList<Track> songs) {
    for (Track track : songs) {
      assertTrue(playlist.contains(track));
    }
  }

  public static void main(String[] args) {
    testTwo();
  }
}
