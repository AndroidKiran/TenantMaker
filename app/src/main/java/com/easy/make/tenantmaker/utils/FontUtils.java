package com.easy.make.tenantmaker.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.util.SparseArray;

import com.easy.make.tenantmaker.TenantApplication;

import java.text.Normalizer;

public class FontUtils {

	public static final String TAG = "FontUtils";
	/*
	 * Permissible values ​​for the "typeface" attribute.
	 */
	public final static int MUSEOSANS_500 = 0;
	public final static int MUSEOSANS_700 = 1;
	public final static int PT_SERIF_REGULAR = 2;
	public final static int PT_SERIF_BOLD = 3;

	/**
	 * List of created typefaces for later reused.
	 */
	private final static SparseArray<Typeface> mTypefaces = new SparseArray<Typeface>(4);


	public FontUtils() {
	}

	public static SparseArray<Typeface> getTypefaces() {
		return mTypefaces;
	}

	/**
	 * Create typeface from assets.
	 *
	 * @param context
	 *            The Context the widget is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param typefaceValue
	 *            values ​​for the "typeface" attribute
	 * @return Roboto {@link Typeface}
	 * @throws IllegalArgumentException
	 *             if unknown `typeface` attribute value.
	 */
	public static Typeface createTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
		Typeface typeface;
		switch (typefaceValue) {
			case FontUtils.MUSEOSANS_500:
				typeface = Typeface.createFromAsset(context.getAssets(), "fonts/museosans_small.otf");
				break;
			case FontUtils.MUSEOSANS_700:
				typeface = Typeface.createFromAsset(context.getAssets(), "fonts/museosans_big.otf");
				break;
			case FontUtils.PT_SERIF_REGULAR:
				typeface = Typeface.createFromAsset(context.getAssets(), "fonts/pt_serif_regular.ttf");
				break;
			case FontUtils.PT_SERIF_BOLD:
				typeface = Typeface.createFromAsset(context.getAssets(), "fonts/pt_serif_bold.ttf");
				break;
		default:
			throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
		}
		return typeface;
	}

	public static SpannableString generateStyledSpannableMuseo500(Context context, CharSequence title) {
        if(TextUtils.isEmpty(title)){
            return null;
        }
        SpannableString spannableString = new SpannableString(title);
        Typeface typeface = getTypefaces().get(MUSEOSANS_500);
        if (typeface == null) {
            typeface = createTypeface(context, MUSEOSANS_500);
            getTypefaces().put(MUSEOSANS_500, typeface);
        }
        spannableString.setSpan(new CustomTypefaceSpan("", typeface),
                0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

	public static SpannableString generateStyledSpannableMuseo700(Context context, CharSequence title) {
		if(TextUtils.isEmpty(title)){
			return null;
		}
		SpannableString spannableString = new SpannableString(title);
		Typeface typeface = getTypefaces().get(MUSEOSANS_700);
		if (typeface == null) {
			typeface = createTypeface(context, MUSEOSANS_700);
			getTypefaces().put(MUSEOSANS_700, typeface);
		}
		spannableString.setSpan(new CustomTypefaceSpan("", typeface),
				0, spannableString.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
	}

	public static class ShadowSpan extends CharacterStyle {
		public float dx;
		public float dy;
		public float radius;
		public int color;

		public ShadowSpan(float radius, float dx, float dy, int color) {
			this.radius = radius;
			this.dx = dx;
			this.dy = dy;
			this.color = color;
		}

		@Override
		public void updateDrawState(TextPaint tp) {
			//tp.setShadowLayer(radius, dx, dy, color);
		}
	}

	public static class StrikeColorSpan extends CharacterStyle implements ParcelableSpan {
		public static final int STRIKETHROUGH_SPAN = 5;
		public static final int FOREGROUND_COLOR_SPAN = 2;
		private final int mColor;

		public StrikeColorSpan(int color) {
			mColor = color;
		}

		public StrikeColorSpan(Parcel src) {
			mColor = src.readInt();
		}

		@Override
		public int getSpanTypeId() {
			return FOREGROUND_COLOR_SPAN;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(mColor);
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			ds.setStrikeThruText(true);
			ds.setColor(mColor);
		}
	}


	public static class CustomTypefaceSpan extends android.text.style.TypefaceSpan {
		private final Typeface newType;

		public CustomTypefaceSpan(String family, Typeface type) {
			super(family);
			newType = type;
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			applyCustomTypeFace(ds, newType);
		}

		@Override
		public void updateMeasureState(TextPaint paint) {
			applyCustomTypeFace(paint, newType);
		}

		private static void applyCustomTypeFace(Paint paint, Typeface tf) {
			int oldStyle;
			Typeface old = paint.getTypeface();
			if (old == null) {
				oldStyle = 0;
			} else {
				oldStyle = old.getStyle();
			}

			int fake = oldStyle & ~tf.getStyle();
			if ((fake & Typeface.BOLD) != 0) {
				paint.setFakeBoldText(true);
			}

			if ((fake & Typeface.ITALIC) != 0) {
				paint.setTextSkewX(-0.25f);
			}

			paint.setTypeface(tf);
		}
	}

	@SuppressLint("NewApi")
	public static CharSequence highlight(Context context, String search, String originalText) {
		// ignore case and accents
		// the same thing should have been done for the search text
		String normalizedText = Normalizer.normalize(originalText, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
				.toLowerCase(TenantApplication.getInstance().getLocale());
		// StyleSpan styleSpan = new StyleSpan(android.graphics.Typeface.BOLD);
		// ForegroundColorSpan colorSpan = new
		// ForegroundColorSpan(context.getResources().getColor(R.color.searchTextColor));

        if(search == null || search.isEmpty() || originalText == null || originalText.isEmpty())
            return originalText;

		int start = normalizedText.indexOf(search);

        if (start < 0) {
			// not found, nothing to to
			return originalText;
		} else {
			// highlight each appearance in the original text
			// while searching in normalized text
			Spannable highlighted = new SpannableString(originalText);
			while (start >= 0) {
				int spanStart = Math.min(start, originalText.length());
				int spanEnd = Math.min(start + search.length(), originalText.length());

              //  if(spanStart == 0 || (spanStart>0 && Character.isWhitespace(originalText.charAt(spanStart-1)))) {
                    highlighted.setSpan(new StyleSpan(Typeface.BOLD),
                            spanStart,
                            spanEnd,
                            0);
              //  }
				start = normalizedText.indexOf(search, spanEnd);
			}

			return highlighted;
		}
	}

}