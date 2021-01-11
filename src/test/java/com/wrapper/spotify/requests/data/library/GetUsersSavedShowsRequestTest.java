package com.wrapper.spotify.requests.data.library;

import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedShow;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetUsersSavedShowsRequestTest extends AbstractDataTest<Paging<SavedShow>> {
  private final GetUsersSavedShowsRequest defaultRequest = SPOTIFY_API.getUsersSavedShows()
    .setHttpManager(
      TestUtil.MockedHttpManager.returningJson(
        "requests/data/library/GetUsersSavedShowsRequest.json"))
    .limit(LIMIT)
    .offset(OFFSET)
    .build();

  public GetUsersSavedShowsRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
      "https://api.spotify.com:443/v1/me/shows?limit=10&offset=0",
      defaultRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault(defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final Paging<SavedShow> savedShowPaging) {
    assertEquals(
      "https://api.spotify.com/v1/me/shows?offset=0&limit=20",
      savedShowPaging.getHref());
    assertEquals(
      3,
      savedShowPaging.getItems().length);
    assertEquals(
      20,
      (int) savedShowPaging.getLimit());
    assertNull(
      savedShowPaging.getNext());
    assertEquals(
      0,
      (int) savedShowPaging.getOffset());
    assertNull(
      savedShowPaging.getPrevious());
    assertEquals(
      3,
      (int) savedShowPaging.getTotal());
  }
}
