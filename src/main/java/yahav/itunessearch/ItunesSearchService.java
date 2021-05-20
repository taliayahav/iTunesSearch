package yahav.itunessearch;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ItunesSearchService {
    @GET("?term=")
    Single<ItunesSearchFeed> getSong(@Query("term") String term);
}
