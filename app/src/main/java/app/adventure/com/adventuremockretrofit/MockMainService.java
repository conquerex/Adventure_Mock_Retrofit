package app.adventure.com.adventuremockretrofit;

import java.util.concurrent.TimeUnit;

import app.adventure.com.adventuremockretrofit.net.RetrofitBuildService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class MockMainService extends RetrofitBuildService {

    public Call<MainResponse> getMockTextLength(String text) {

        Retrofit retrofit = createRetrofit();

        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
        BehaviorDelegate<Api> delegate = mockRetrofit.create(Api.class);

        MockApi mockApi = new MockApi(delegate);
        behavior.setDelay(500, TimeUnit.MILLISECONDS);
        Call<MainResponse> call = mockApi.getMockLength(text);

        Callback<MainResponse> callback = new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                //
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                //
            }
        };
        call.enqueue(callback);

        return call;
    }

    class MockApi {
        private BehaviorDelegate<Api> delegate;

        public MockApi(BehaviorDelegate<Api> delegate) {
            this.delegate = delegate;
        }

        public Call<MainResponse> getMockLength(String text) {
            MainResponse response = new MainResponse();

            response.length = 0;

            return delegate.returningResponse(response).getLength(text);
        }
    }

    interface Api {
        @POST("signin")
        Call<MainResponse> getLength(String text);
    }
}
