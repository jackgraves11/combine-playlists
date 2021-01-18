package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static data.PLCombine.Main.grayID;
import static data.PLCombine.Main.jackID;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  private void assertPlaylistContains(Set<Track> playlist, ArrayList<Track> songs) {
    for (Track track : songs) {
      assertTrue(playlist.contains(track));
    }
  }
}
