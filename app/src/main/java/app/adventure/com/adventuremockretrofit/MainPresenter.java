package app.adventure.com.adventuremockretrofit;

import retrofit2.Call;

public class MainPresenter {

    private Call<MainResponse> call;
    private MainUi ui;

    public void onUiReady(MainUi ui) {
        this.ui = ui;
    }

    void getLength(String text) {
        MockMainService service = MockMainService.newInstance();

        call = service.getMockTextLength(text, new MockMainListener() {
            @Override
            public void onSuccess(int length) {
                ui.getLength(length);
            }

            @Override
            public void onFail() {
                ui.getFailMessage();
            }
        });
    }

    public interface MainUi {
        void getLength(int length);
        void getFailMessage();
    }
}
