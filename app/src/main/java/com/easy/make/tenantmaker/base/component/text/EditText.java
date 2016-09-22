package com.easy.make.tenantmaker.base.component.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.utils.FontUtils;

/**
 * Created by apurvasharma on 02/09/16.
 */
public class EditText extends android.support.design.widget.TextInputEditText {

    private CharSequence originalText = "";

    public EditText(Context context) {
        super(context);
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        // Typeface.createFromAsset doesn't work in the layout editor, so skipping.
        if (isInEditMode()) {
            return;
        }

        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.TextView);
        int typefaceValue = values.getInt(R.styleable.TextView_fontTypeface, FontUtils.MUSEOSANS_500);
        values.recycle();

        originalText = getText();
        setTypeface(obtainTypeface(context, typefaceValue));
    }

    /**
     * Obtain typeface.
     *
     * @param context       The Context the widget is running in, through which it can
     *                      access the current theme, resources, etc.
     * @param typefaceValue values ​​for the "typeface" attribute
     * @return Roboto {@link Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */
    private Typeface obtainTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface = FontUtils.getTypefaces().get(typefaceValue);
        if (typeface == null) {
            typeface = FontUtils.createTypeface(context, typefaceValue);
            FontUtils.getTypefaces().put(typefaceValue, typeface);
        }
        return typeface;
    }
}
