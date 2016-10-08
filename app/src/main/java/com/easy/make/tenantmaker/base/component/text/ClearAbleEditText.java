/**
 * Copyright 2014 Alex Yanchenko
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package com.easy.make.tenantmaker.base.component.text;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.component.TextWatcherAdapter;
import com.easy.make.tenantmaker.base.utils.FontUtils;

/**
 * To change clear icon, set
 * 
 * <pre>
 * android:drawableRight="@drawable/custom_icon"
 * </pre>
 */
public class ClearAbleEditText extends EditText implements OnTouchListener, OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {

	@Override
	public void onTextChanged(android.widget.EditText view, String text) {
		setClearIconVisible(!TextUtils.isEmpty(text));
	}

	public interface Listener {
		void didClearText(View view);
        void isEnabled(View view, boolean isEnabled);
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private Drawable xD;
	private Listener listener;

	public ClearAbleEditText(Context context) {
		super(context);
		init();
	}

	public ClearAbleEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	@Override
	public void setOnTouchListener(OnTouchListener l) {
		this.l = l;
	}

	@Override
	public void setOnFocusChangeListener(OnFocusChangeListener f) {
		this.f = f;
	}

	private OnTouchListener l;
	private OnFocusChangeListener f;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (getCompoundDrawables()[2] != null) {
			boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - xD.getIntrinsicWidth());
			if (tappedX) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(isEnabled()){
                        setText("");
                        if (listener != null) {
                            listener.didClearText(v);
                        }
                    }
                    else{
                        setTextEnabled(true);
                    }
				}

				return false;
			}
		}
		if (l != null) {
			return l.onTouch(v, event);
		}
		return false;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
/*		if (hasFocus) {
			setClearIconVisible(!TextUtils.isEmpty(getText()));
		} else {
			setClearIconVisible(false);
		}*/

	    setClearIconVisible(!TextUtils.isEmpty(getText()));

		if (f != null) {
			f.onFocusChange(v, hasFocus);
		}
	}

//	@Override
//	public void onTextChanged(EditText view, String text) {
//	   setClearIconVisible(!TextUtils.isEmpty(text));
//	}

	private void init() {
		xD = getCompoundDrawables()[2];
		if (xD == null) {
			xD = AppCompatDrawableManager.get().getDrawable(getContext(), R.drawable.ic_clear_black_24dp);
		}
		Typeface typeface = FontUtils.createTypeface(getContext(), FontUtils.MUSEOSANS_500);
		setTypeface(typeface);
		xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
		setClearIconVisible(!TextUtils.isEmpty(getText()));
		super.setOnTouchListener(this);
		super.setOnFocusChangeListener(this);
		addTextChangedListener(new TextWatcherAdapter(this, this));
	}

	protected void setClearIconVisible(boolean visible) {
		Drawable x = isEnabled() && visible ? xD : null;
		setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (!isEnabled()) {
                setTextEnabled(true);
			}
			setClearIconVisible(!TextUtils.isEmpty(getText()));
		}
		return super.onTouchEvent(event);
	}

	public void setTextEnabled(boolean enabled) {
       // setClearIconVisible(!TextUtils.isEmpty(getText()));
        if (listener != null) {
            listener.isEnabled(this, enabled);
        }
	}

}
