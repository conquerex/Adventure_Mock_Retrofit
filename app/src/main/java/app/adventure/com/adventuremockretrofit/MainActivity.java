package app.adventure.com.adventuremockretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainUi {

    EditText edtInput;
    Button btnInput;
    TextView txtResult;

    MainPresenter presenter;
    String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        presenter.onUiReady(this);

        edtInput = findViewById(R.id.edt_input);
        btnInput = findViewById(R.id.btn_input);
        txtResult = findViewById(R.id.txt_result);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = edtInput.getText().toString();
                Toast.makeText(
                        getApplicationContext(), "Welcome to Sample app", Toast.LENGTH_LONG
                ).show();
                presenter.getLength(inputText);
            }
        });
    }

    @Override
    public void getLength(int length) {
        txtResult.setText(inputText + "는 총 " + length + "자 입니다.");
    }

    @Override
    public void getFailMessage() {
        txtResult.setText("입력값이 잘못되었습니다.");
    }
}
