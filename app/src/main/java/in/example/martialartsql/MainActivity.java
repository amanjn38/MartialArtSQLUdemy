package in.example.martialartsql;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.NumberFormat;
import java.util.ArrayList;

import in.example.martialartsql.model.DatabaseHandler;
import in.example.martialartsql.model.MartialArt;
import in.example.martialartsql.model.MartialArtButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler databaseHandler;
    private Double totalMartialArtsPrice;
    private ScrollView scrollView;
    private int martialArtButtonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler(MainActivity.this);
        totalMartialArtsPrice = 0.0;
        scrollView = findViewById(R.id.scrollView);

        Point screenWidth = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenWidth);

        martialArtButtonWidth = screenWidth.x / 2;

        modifyUserInterface();
    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> martialArtArrayList = databaseHandler.returnAlMartialArtObject();
        scrollView.removeAllViewsInLayout();

        if (martialArtArrayList.size() > 0) {

            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((martialArtArrayList.size() + 1) / 2);
            gridLayout.setColumnCount(2);

            MartialArtButton[] martialArtButtons = new MartialArtButton[martialArtArrayList.size()];
            int index = 0;

            for (MartialArt martialArt : martialArtArrayList) {
                martialArtButtons[index] = new MartialArtButton
                        (MainActivity.this, martialArt);

                martialArtButtons[index].setText(martialArt.getMartialArtID() + "\n" +
                        martialArt.getMartialArtName() + "\n" +
                        martialArt.getMartialArtPrice());
                switch (martialArt.getMartialArtColor()) {
                    case "Red":
                        martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Black":
                        martialArtButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "Yellow":
                        martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "Green":
                        martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "Cyan":
                        martialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "White":
                        martialArtButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                    default:
                        martialArtButtons[index].setBackgroundColor(Color.GRAY);
                }

                martialArtButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtButtons[index], martialArtButtonWidth,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }

            scrollView.addView(gridLayout);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.add_martial_art:
                Intent intent = new Intent(MainActivity.this, AddMartialArt.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            case R.id.delete_martial_art:
                Intent intent1 = new Intent(MainActivity.this, DeleteMartialArtActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                return true;

            case R.id.update_martial_art:
                Intent intent2 = new Intent(MainActivity.this, UpdateMartialArtActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                return true;

            case R.id.martial_arts_prices_reser:
                totalMartialArtsPrice = 0.0;
                Toast.makeText(this, totalMartialArtsPrice + "", Toast.LENGTH_SHORT).show();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        MartialArtButton martialArtButton = (MartialArtButton) v;
        totalMartialArtsPrice = totalMartialArtsPrice + martialArtButton.getMartialArtPrice();
        String martialArtsPriceFormatted = NumberFormat.getCurrencyInstance()
                .format(totalMartialArtsPrice);
        Toast.makeText(this, martialArtsPriceFormatted, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        modifyUserInterface();
    }
}
