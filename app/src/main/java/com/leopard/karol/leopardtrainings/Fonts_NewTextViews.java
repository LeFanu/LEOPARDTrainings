package com.leopard.karol.leopardtrainings;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Karol on 2017-03-03.
 */

public class Fonts_NewTextViews extends android.support.v7.widget.AppCompatTextView {
    private final static int BERLIN_DEMI = 0;
    private final static int BERLIN_SANS = 1;

    public Fonts_NewTextViews(Context context) {
        super(context);
    }

    /*public Fonts_NewTextViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public Fonts_NewTextViews(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        parseAttributes(context, attrs);
    }*/




    /*private void parseAttributes (Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.LeopardTextView);

        int typeface = values.getInt(R.styleable.LeopardTextView_typeface, 0);

        switch (typeface) {
            case BERLIN_DEMI: default:
                setTypeface(berlinDemi);
                break;
            case BERLIN_SANS:
                setTypeface(berlinSans);
                break;
        }
        values.recycle();


    }
*/


}
