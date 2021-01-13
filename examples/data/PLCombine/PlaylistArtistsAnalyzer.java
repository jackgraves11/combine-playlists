package data.PLCombine;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.*;

public class PlaylistArtistsAnalyzer {
  ArrayList<Track> playlist;

  public PlaylistArtistsAnalyzer(ArrayList<Track> playlist) {
    this.playlist = playlist;
  }

  public Map<String, Integer> topArtists() {
    HashMap<String, Integer> result = new HashMap<>();
    for (Track t : playlist) {
      ArtistSimplified[] artists = t.getArtists();
      for (ArtistSimplified artist : artists) {
        if (result.containsKey(artist.getName())) {
          result.put(artist.getName(), result.get(artist.getName()) + 1);
        } else {
          result.put(artist.getName(), 1);
        }
      }
    }
    //printMap(result);
    List<Map.Entry<String, Integer>> list = new ArrayList<>(result.entrySet());
    list.sort(Map.Entry.comparingByValue());
    for (int i = list.size() - 1; i >= 0; i -= 1) {
      System.out.print(list.get(i).getKey() + ": ");
      System.out.println(list.get(i).getValue());
    }
    return result;
  }

  private void printMap(Map<String, Integer> map) {
    Set<String> artists = map.keySet();
    for (String artist : artists) {
      System.out.print(artist + ": ");
      System.out.println(map.get(artist));
    }
  }
}
