package in.example.martialartsql;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import in.example.martialartsql.model.DatabaseHandler;
import in.example.martialartsql.model.MartialArt;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);

        databaseHandler = new DatabaseHandler(UpdateMartialArtActivity.this);

        modifyUserInterface();
    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> martialArts = databaseHandler.returnAlMartialArtObject();
        if (martialArts.size() > 0) {

            //First column for id,
            ScrollView scrollView = new ScrollView(UpdateMartialArtActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArtActivity.this);
            gridLayout.setRowCount(martialArts.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArts.size()];
            EditText[][] etNamePriceAndColor = new EditText[martialArts.size()][3];
            Button[] modifyButton = new Button[martialArts.size()];

            //Accessing the width of the user phone screen
            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth = screenSize.x;
            int index = 0;

            for (MartialArt martialArt : martialArts) {
                idTextViews[index] = new TextView(UpdateMartialArtActivity.this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(martialArt.getMartialArtID() + "");

                etNamePriceAndColor[index][0] = new EditText(UpdateMartialArtActivity.this);
                etNamePriceAndColor[index][1] = new EditText(UpdateMartialArtActivity.this);
                etNamePriceAndColor[index][2] = new EditText(UpdateMartialArtActivity.this);

                etNamePriceAndColor[index][0].setText(martialArt.getMartialArtName());
                etNamePriceAndColor[index][1].setText(martialArt.getMartialArtPrice() + "");
                etNamePriceAndColor[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                etNamePriceAndColor[index][2].setText(martialArt.getMartialArtColor());

                etNamePriceAndColor[index][0].setId(martialArt.getMartialArtID() + 10);
                etNamePriceAndColor[index][1].setId(martialArt.getMartialArtID() + 20);
                etNamePriceAndColor[index][2].setId(martialArt.getMartialArtID() + 30);

                modifyButton[index] = new Button(UpdateMartialArtActivity.this);
                modifyButton[index].setText("MODIFY");
                modifyButton[index].setId(martialArt.getMartialArtID());
                modifyButton[index].setOnClickListener(UpdateMartialArtActivity.this);

                gridLayout.addView(idTextViews[index], (int)(screenWidth * 0.05), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(etNamePriceAndColor[index][0],
                        (int) (screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(etNamePriceAndColor[index][1],
                        (int) (screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(etNamePriceAndColor[index][2],
                        (int) (screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);

                gridLayout.addView(modifyButton[index], (int) (screenWidth * 0.35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                index++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }
    }

    @Override
    public void onClick(View v) {
        int martialArtObjectId = v.getId();

        EditText etName = findViewById(martialArtObjectId + 10);
        EditText etPrice = findViewById(martialArtObjectId + 20);
        EditText etColor = findViewById(martialArtObjectId + 30);

        String name = etName.getText().toString();
        String price = etPrice.getText().toString();
        String color = etColor.getText().toString();

        try {

            double priceDouble =
                    Double.parseDouble(price);
            databaseHandler.modifyMartialArtObject(martialArtObjectId, name,
                    priceDouble, color);

            Toast.makeText(this, "This martial art object is updated", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
