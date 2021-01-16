package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.*;

/**
 * TODO: Make sure that the common tracks across playlists appear first in the resulting playlist?
 */
public class CreatePlaylist {

  public Set<Track> getPlaylist(String firstPlaylistID, String secondPlaylistID, int numSongs) {
    ArrayList<Track> firstPl = SharedMethods.getPLTracks(firstPlaylistID);
    ArrayList<Track> secondPl = SharedMethods.getPLTracks(secondPlaylistID);
    compareTwoPL playlistComparer = new compareTwoPL();
    ArrayList<Track> sharedTracks = playlistComparer.getSharedTracks(firstPl, secondPl);
    Set<Track> finalPL = new HashSet<>();
    finalPL.addAll(sharedTracks);
    Random r = new Random();
    if (finalPL.size() < numSongs) {
      ArtistRanker artistRanker = new ArtistRanker();
      ArrayList<ArtistRanker.SimpleArtistScoreNode> ranks = artistRanker.artistRanks(firstPl, secondPl);
      int currentRankIndex = 0;
      while (finalPL.size() < numSongs) {
        ArtistSimplified currArtist = ranks.get(currentRankIndex).getArtist();
        Set<Track> currArtistsTracks = new HashSet<>();
        getArtistsTracks(currArtist, firstPl, currArtistsTracks);
        getArtistsTracks(currArtist, secondPl, currArtistsTracks);
        for (int i = 0; i < 5; i += 1) {
          int index = r.nextInt(currArtistsTracks.size());
          Iterator<Track> iter = currArtistsTracks.iterator();
          for (int currIndex = 0; index < currArtistsTracks.size(); currIndex += 1) {
            Track track = iter.next();
            if (currIndex == index) {
              finalPL.add(track);
              break;
            }
          }
          if (finalPL.size() > numSongs) {
            break;
          }
        }
        currentRankIndex += 1;
      }
    }
    return finalPL;
  }

  private void getArtistsTracks(ArtistSimplified artist, ArrayList<Track> playlist,
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
