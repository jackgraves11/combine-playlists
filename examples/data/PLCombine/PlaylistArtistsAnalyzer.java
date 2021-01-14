package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.*;

public class PlaylistArtistsAnalyzer {
  ArrayList<Track> playlist;

  public PlaylistArtistsAnalyzer(ArrayList<Track> playlist) {
    this.playlist = playlist;
  }

  public class SimpleArtistFreqNode implements Comparable<SimpleArtistFreqNode> {
    ArtistSimplified artist;
    int frequency;

    public SimpleArtistFreqNode(ArtistSimplified artist, int frequency) {
      this.artist = artist;
      this.frequency = frequency;
    }

    public ArtistSimplified getArtist() {
      return artist;
    }

    public int getFrequency() {
      return frequency;
    }

    public void setArtist(ArtistSimplified artist) {
      this.artist = artist;
    }

    public void setFrequency(int frequency) {
      this.frequency = frequency;
    }

    @Override
    public int compareTo(SimpleArtistFreqNode o) {
      return this.getFrequency() - o.getFrequency();
    }
  }

  public SimpleArtistFreqNode[] topArtists() {
    HashMap<ArtistSimplified, Integer> map = new HashMap<>();
    for (Track t : playlist) {
      ArtistSimplified[] artists = t.getArtists();
      for (ArtistSimplified artist : artists) {
        if (map.containsKey(artist)) {
          map.put(artist, map.get(artist) + 1);
        } else {
          map.put(artist, 1);
        }
      }
    }
    SimpleArtistFreqNode[] result = new SimpleArtistFreqNode[map.size()];
    int index = 0;
    for (Map.Entry<ArtistSimplified, Integer> entry : map.entrySet()) {
      result[index] = new SimpleArtistFreqNode(entry.getKey(), entry.getValue());
      index += 1;
    }
    Arrays.sort(result, Collections.reverseOrder());
    for (int i = 0; i < result.length; i += 1) {
      System.out.print(result[i].getArtist().getName() + ": ");
      System.out.println(result[i].getFrequency());
    }
    return result;
  }

  public void printArtistsTracks(ArtistSimplified artist) {
    for (Track track : playlist) {
      ArtistSimplified[] artists = track.getArtists();
      for (ArtistSimplified currArtist : artists) {
        if (currArtist.equals(artist)) {
          System.out.println(track.getName());
        }
      }
    }
  }

  private void printMap(Map<String, Integer> map) {
    Set<String> artists = map.keySet();
    for (String artist : artists) {
      System.out.print(artist + ": ");
      System.out.println(map.get(artist));
    }
  }
}
