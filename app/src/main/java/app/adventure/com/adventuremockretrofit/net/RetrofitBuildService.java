package app.adventure.com.adventuremockretrofit.net;

import app.adventure.com.adventuremockretrofit.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public abstract class RetrofitBuildService {

    private final String FAKE_URL = "https://your-url/";

    protected Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(FAKE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("X-Device-Type", String.valueOf(2))
                                    .header("X-App-Version", String.valueOf(BuildConfig.VERSION_CODE))
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        })
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                        .build())
                .build();
    }
}

