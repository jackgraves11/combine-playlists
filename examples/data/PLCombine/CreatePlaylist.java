package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.*;

/**
 *  * Class where everything starts from: It finds the songs to add to the resulting playlist
 *  * and eventually will create that playlist and add it to spotify.
 * TODO: Make sure that the common tracks across playlists appear first in the resulting playlist?
 */

public class CreatePlaylist {

  /**
   * Creates and returns a Set of Tracks that will ultimately become the playlist that is
   * the final product of this program.
   * @param firstPlaylistID Spotify ID of the first user inputted playlist.
   * @param secondPlaylistID Spotify ID of the second user inputted playlist.
   * @param numSongs How many songs the user wants of the resulting playlist.
   * @return Set of tracks that is all the songs that will go on the resulting playlist.
   */
  public Set<Track> getPlaylist(String firstPlaylistID, String secondPlaylistID, int numSongs) {
    ArrayList<Track> firstPl = SharedMethods.getPLTracks(firstPlaylistID);
    ArrayList<Track> secondPl = SharedMethods.getPLTracks(secondPlaylistID);
    compareTwoPL playlistComparer = new compareTwoPL();
    ArrayList<Track> sharedTracks = playlistComparer.getSharedTracks(firstPl, secondPl); //TODO Could have getSharedTracks return a set.
    Set<Track> finalPL = new HashSet<>(sharedTracks);
    while (finalPL.size() > numSongs) { //If there are more common songs than songs requested, take out some.
      finalPL.remove(SharedMethods.getRandomTrack(finalPL));
    }
    findAndAddTracks(firstPl, secondPl, finalPL, numSongs);
    return finalPL;
  }

  /**
   * Helper method that uses artist ranking strategy as defined in algorithm google doc
   * to add more songs to the playlist until it reaches the desired length.
   * @param pLOne First user inputted playlist
   * @param pLTwo Second user inputted playlist
   * @param finalPlaylist Resulting playlist as a set of tracks
   * @param numSongsDesired Number of songs the user requests in the resulting playlist.
   */
  private void findAndAddTracks(ArrayList<Track> pLOne, ArrayList<Track> pLTwo,
                                Set<Track> finalPlaylist, int numSongsDesired) {
    if (finalPlaylist.size() < numSongsDesired) {
      ArtistRanker artistRanker = new ArtistRanker();
      ArrayList<ArtistRanker.SimpleArtistScoreNode> ranks = artistRanker.artistRanks(pLOne, pLTwo);
      int currentRankIndex = 0;
      while (finalPlaylist.size() < numSongsDesired) {
        ArtistSimplified currArtist = ranks.get(currentRankIndex).getArtist();
        Set<Track> currArtistsTracks = new HashSet<>();
        addArtistsTracks(currArtist, pLOne, currArtistsTracks);
        addArtistsTracks(currArtist, pLTwo, currArtistsTracks);
        for (int i = 0; i < 5; i += 1) {
          finalPlaylist.add(SharedMethods.getRandomTrack(currArtistsTracks));
          if (finalPlaylist.size() == numSongsDesired) {
            break;
          }
        }
        currentRankIndex += 1;
      }
    }
  }

  /**
   * Scans through the playlist that is inputted and adds all the tracks that
   * have the given artist in them to the Set of tracks that is passed in.
   * @param artist The artist whose songs we are scanning for.
   * @param playlist The playlist which we scan to find the artist's songs.
   * @param setToAddTracksTo Where we add the tracks that have the artist in them.
   */
  private void addArtistsTracks(ArtistSimplified artist, ArrayList<Track> playlist,
                                Set<Track> setToAddTracksTo) {
    for (Track track : playlist) {
      ArtistSimplified[] artists = track.getArtists();
      for (ArtistSimplified currArtist : artists) {
        if (currArtist.equals(artist)) {
          setToAddTracksTo.add(track);
        }
      }
    }
  }
}
