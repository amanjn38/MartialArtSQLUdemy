package in.example.martialartsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.example.martialartsql.model.DatabaseHandler;
import in.example.martialartsql.model.MartialArt;

public class AddMartialArt extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etPrice, etColor;
    private Button btnaddMartialArt, btnGoBack;
    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        etColor = findViewById(R.id.etColor);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);

        btnaddMartialArt = findViewById(R.id.btnAddMartialArt);
        btnGoBack = findViewById(R.id.btnGoBack);

        databaseHandler = new DatabaseHandler(AddMartialArt.this);

        btnaddMartialArt.setOnClickListener(AddMartialArt.this);
        btnGoBack.setOnClickListener(AddMartialArt.this);

    }

    private void addMartialArtObjectToDatabase() {
        String name = etName.getText().toString();
        String price = etPrice.getText().toString();
        String color = etColor.getText().toString();

        try {
            double price1 = Double.parseDouble(price);
            MartialArt martialArt = new MartialArt(0, name, price1 , color);
            databaseHandler.addMartialArt(martialArt);
            Toast.makeText(this, martialArt + "This martial art object is saved to database", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddMartialArt:
                addMartialArtObjectToDatabase();
                break;

            case R.id.btnGoBack:
                this.finish();
                break;
        }
    }
}
