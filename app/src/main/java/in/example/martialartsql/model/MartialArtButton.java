package in.example.martialartsql.model;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

public class MartialArtButton extends AppCompatButton {

    private MartialArt martialArt;

    public MartialArtButton(Context context, MartialArt martialArt1){
        super(context);
        martialArt = martialArt1;
    }

    public String getMartialArtColor() {
        return martialArt.getMartialArtColor();
    }

    public double getMartialArtPrice() {
        return martialArt.getMartialArtPrice();
    }

}
