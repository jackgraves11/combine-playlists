package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.*;

/**
 * Old class that scans a playlist and counts the number of tracks each artist in the playlist
 * is on.
 */

public class PlaylistArtistsAnalyzer {

  ArrayList<Track> playlist;

  public PlaylistArtistsAnalyzer(ArrayList<Track> playlist) {
    this.playlist = playlist;
  }

  /**
   * Node that stores an artist and the number of songs the artist appears on
   * in the playlist (frequency).
   */
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

  /**
   * Scans the playlist and finds out how songs each artist is on.
   * @return An array sorted of artists and how many songs they are on.
   * The array is sorted so that the artist on the most tracks is first
   * and the artist on the least number of tracks is last.
   */
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
    return result;
  }

  /**
   * Prints all of the tracks of a given artist that are in the playlist.
   */
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
}
