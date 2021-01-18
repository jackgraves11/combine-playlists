package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.*;

/**
 * Ranks artist across the two inputted playlists according to the ranking algorithm in google doc.
 * A higher ranking ideally means that both playlist owners like the artist a lot/have a lot
 * of tracks from this artist compared to other artist. Ex if both playlists have a lot
 * of Young Thug songs, Young thug would have a high rating. The rating/score is given as a double
 * and is returned as a part of a SimpleArtistScoreNode for each artist. The resulting playlist
 * will ideally have more songs from high rated artists than low rated ones.
 */

public class ArtistRanker {

  int numPlaylists;

  public ArtistRanker() {
    numPlaylists = 2;
  }

  /**
   * The method where the artist ranking process starts. Returns all the artist scores
   * in a descended sorted list of SimpleArtistScoreNodes.
   * @param playlist1 First user playlist
   * @param playlist2 Second user playlist.
   * @return ArrayList of SimpleScoreNodes (artist ranks) sorted by highest score first and lowest
   * score last.
   */
  public ArrayList<SimpleArtistScoreNode> artistRanks(ArrayList<Track> playlist1,
                                                      ArrayList<Track> playlist2) {
    HashMap<ArtistSimplified, BuildingArtistRankNode> rankings = new HashMap<>();
    findRankings(playlist1, 1, rankings);
    findRankings(playlist2, 2, rankings);
    ArrayList<SimpleArtistScoreNode> result = new ArrayList<>();
    for (Map.Entry<ArtistSimplified, BuildingArtistRankNode> entry : rankings.entrySet()) {
      result.add(new SimpleArtistScoreNode(entry.getKey(), entry.getValue().getScore()));
    }
    result.sort(Collections.reverseOrder());
    return result;
  }

  /**
   * Scans through the given playlist and creates BuildingArtistRankNodes for all the artists
   * in the playlist and adds them to the rankings hashmap. If there is already a node for
   * an artist in the rankings hashmap, it will update that node to contain another
   * song appearance and make sure that it knows that artist is on this current playlist.
   * @param playlist Current user inputted playlist
   * @param playlistNumber Number of the user inputted playlist. Ex the first playlist the
   *                       user gives is playlist number 1.
   * @param rankings A HashMap containing artists and BuildingArtistRankNodes. This method
   *                 will update this map to contain all the artists from the inputted
   *                 playlist.
   */
  private void findRankings(ArrayList<Track> playlist, int playlistNumber,
                            HashMap<ArtistSimplified, BuildingArtistRankNode> rankings) {
    for (Track track : playlist) {
      if (track != null) {
        ArtistSimplified[] artists = track.getArtists();
        for (ArtistSimplified artist : artists) {
          if (artist.getId() != null) {
            if (rankings.containsKey(artist)) {
              BuildingArtistRankNode currNode = rankings.get(artist);
              currNode.setNumAppearances(currNode.getNumAppearances() + 1);
              currNode.addPlaylistContainingArtist(playlistNumber);
            } else {
              rankings.put(artist, new BuildingArtistRankNode(artist, playlistNumber));
            }
          }
        }
      }
    }
  }



  /**
   * Node class that keeps track of how many songs the artist has in both of the playlists combined,
   * the number of playlists this artist has a song in and the artists current score/rank (this may
   * not be the correct rank until all the playlists have been scanned and total num appearances
   * is found). When sorting/comparing these nodes it is based on each node's score.
   */
  private class BuildingArtistRankNode implements Comparable<BuildingArtistRankNode> {
    ArtistSimplified artist;
    int numAppearances;
    Set<Integer> playlistsIn;

    public BuildingArtistRankNode(ArtistSimplified artist, int currPlaylistNumber) {
      this.artist = artist;
      this.numAppearances = 1;
      this.playlistsIn = new HashSet<>();
      playlistsIn.add(currPlaylistNumber);
    }

    public void addPlaylistContainingArtist(int playlistNumber) {
      playlistsIn.add(playlistNumber);
    }

    public ArtistSimplified getArtist() {
      return artist;
    }

    public int getNumAppearances() {
      return numAppearances;
    }

    public void setNumAppearances(int numAppearances) {
      this.numAppearances = numAppearances;
    }

    public int getNumPlaylistsIn() {
      return playlistsIn.size();
    }

    public Set<Integer> getPlaylistsIn() {
      return playlistsIn;
    }

    public double getScore() {
      return getNumAppearances() *  ((double) getNumPlaylistsIn() / numPlaylists);
    }

    public boolean equals(BuildingArtistRankNode other) {
      return (this.getArtist().equals(other.getArtist()));
    }

    /**
     * Because the numAppearances and playlistsIn are mutable/changing values the hashCode
     * needs to be based off the artist (who's hashCode is based off artistID),
     * which will never be changed.
     */
    @Override
    public int hashCode() {
      return getArtist().hashCode();
    }

    /**
     * Need to override and base off the artist because hashCode is overridden
     * and based on artist as well.
     */
    @Override
    public boolean equals(Object obj) {
      if (!this.getClass().equals(obj.getClass())) {
        return false;
      } else {
        BuildingArtistRankNode otherNode = (BuildingArtistRankNode) obj;
        return this.getArtist().equals(otherNode.getArtist());
      }
    }

    @Override
    public int compareTo(BuildingArtistRankNode o) {
      if (this.getScore() - o.getScore() > 0) {
        return 1;
      } else if (this.getScore() - o.getScore() < 0){
        return -1;
      } else {
        return 0;
      }
    }
  }



  /**
   * Node class that contains artist and score. These nodes are non-mutable and end up
   * being returned by artistRanks() method. When sorting/comparing these nodes it is
   * based upon the score of each node.
   */
  public class SimpleArtistScoreNode implements Comparable<SimpleArtistScoreNode> {
    ArtistSimplified artist;
    double score;

    public SimpleArtistScoreNode(ArtistSimplified artist, double score) {
      this.artist = artist;
      this.score = score;
    }

    public ArtistSimplified getArtist() {
      return artist;
    }

    public double getScore() {
      return score;
    }

    @Override
    public int compareTo(SimpleArtistScoreNode o) {
      if (this.getScore() - o.getScore() > 0) {
        return 1;
      } else if (this.getScore() - o.getScore() < 0){
        return -1;
      } else {
        return 0;
      }
    }
  }
}
