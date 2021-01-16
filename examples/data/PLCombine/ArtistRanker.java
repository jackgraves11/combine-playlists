package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.*;

public class ArtistRanker {

  public ArrayList<SimpleArtistScoreNode> artistRanks(ArrayList<Track> playlist1, ArrayList<Track> playlist2) {
    HashMap<ArtistSimplified, BuildingArtistRankNode> rankings = new HashMap<>();
    for (Track track : playlist1) {
      ArtistSimplified[] artists = track.getArtists();
      for (ArtistSimplified artist : artists) {
        if (rankings.containsKey(artist)) {
          BuildingArtistRankNode currNode = rankings.get(artist);
          currNode.setNumAppearances(currNode.getNumAppearances() + 1);
          currNode.addPlaylistContainingArtist(1);
        } else {
          rankings.put(artist, new BuildingArtistRankNode(artist, 1, 2));
        }
      }
    }
    for (Track track : playlist2) {
      ArtistSimplified[] artists = track.getArtists();
      for (ArtistSimplified artist : artists) {
        if (rankings.containsKey(artist)) {
          BuildingArtistRankNode currNode = rankings.get(artist);
          currNode.setNumAppearances(currNode.getNumAppearances() + 1);
          currNode.addPlaylistContainingArtist(2);
        } else {
          rankings.put(artist, new BuildingArtistRankNode(artist, 2, 2));
        }
      }
    }
    ArrayList<SimpleArtistScoreNode> result = new ArrayList<>();
    for (Map.Entry<ArtistSimplified, BuildingArtistRankNode> entry : rankings.entrySet()) {
      result.add(new SimpleArtistScoreNode(entry.getKey(), entry.getValue().getScore()));
    }
    Collections.sort(result, Collections.reverseOrder());
    return result;
  }

  private class BuildingArtistRankNode implements Comparable<BuildingArtistRankNode> {
    ArtistSimplified artist;
    int numAppearances;
    Set<Integer> playlistsIn;
    int numTotalPlaylists;

    public BuildingArtistRankNode(ArtistSimplified artist, int currPlaylistNumber,
                                  int numTotalPlaylists) {
      this.artist = artist;
      this.numAppearances = 1;
      this.playlistsIn = new HashSet<>();
      playlistsIn.add(currPlaylistNumber);
      this.numTotalPlaylists = numTotalPlaylists;
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

    public int getNumTotalPlaylists() {
      return numTotalPlaylists;
    }

    public void setNumTotalPlaylists(int numTotalPlaylists) {
      this.numTotalPlaylists = numTotalPlaylists;
    }

    public double getScore() {
      return numAppearances *  ((double) playlistsIn.size() / numTotalPlaylists);
    }

    public boolean equals(BuildingArtistRankNode other) {
      return (this.getArtist().equals(other.getArtist()));
    }

    @Override
    public int hashCode() {
      return getArtist().hashCode();
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
