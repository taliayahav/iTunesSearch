package yahav.itunessearch;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItunesSearchServiceFactory {
    public ItunesSearchService newInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        ItunesSearchService service = retrofit.create(ItunesSearchService.class);

        return service;
    }
}
