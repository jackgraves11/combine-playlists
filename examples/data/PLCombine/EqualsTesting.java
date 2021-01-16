package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Tests whether equals method for Track, Artist, Album work correctly
 */

public class EqualsTesting {

  private static final String testTrackPLOne = "0j1AolmgBCVpc8JUgAkQ1o";
  private static final String testTrackPLTwo = "2NUfUmvH4j0UgQytZCiaGA";

  @Test
  public void trackEqualsTest() {
    ClientCredentials.clientCredentials_Sync();
    compareTwoPL PlaylistComparer = new compareTwoPL();

    /** Test that same playlist with itself returns every song */
    ArrayList<Track> plOne = SharedMethods.getPLTracks(testTrackPLOne);
    ArrayList<Track> plTwo = SharedMethods.getPLTracks(testTrackPLTwo);
    ArrayList<Track> sharedTracks = PlaylistComparer.getSharedTracks(plOne, plTwo);
    assertEquals(sharedTracks.size(), 10);

    /**
     * Shared tracks on Test Tracks One and Test Tracks Two should be:
     * March Madness - Tests that single vs album song is equal
     * Gold Digger - Tests that explicit version equals clean
     * Franchise - Tests that Franchise linked from Travis equals Franchise linked from Thugs page
     * Liger - Tests that single vs album song is equal
     */
    ArrayList<Track> sharedTracks2 = PlaylistComparer.getSharedTracks(SharedMethods.getPLTracks(testTrackPLOne),
      SharedMethods.getPLTracks(testTrackPLTwo));
    assertEquals(sharedTracks2.size(), 4);
    PlaylistComparer.printTracks(sharedTracks2);
  }
}
