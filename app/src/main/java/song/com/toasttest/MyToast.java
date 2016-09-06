package song.com.toasttest;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by songpan on 16/9/6.
 */
public class MyToast {
    private final Handler mHandler = new Handler();
    private int mDuration = 2000;
    private int mGravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
    private int mX = 30;
    private int mY = 30;
    private float mHorizontalMargin;
    private float mVerticalMargin;
    private View mView;
    private View mNextView;

    private WindowManager mWindow;
    private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();


    public MyToast(Context context) {
        final WindowManager.LayoutParams params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                |WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        params.windowAnimations = android.R.style.Animation_Toast;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");
        mWindow = (WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    }

    public static MyToast makeText(Context context, CharSequence text, int duration) {
        MyToast myToast = new MyToast(context);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.my_toast_layout,null,false);
        TextView textView = (TextView) layout.findViewById(R.id.message);
        textView.setText(text);
        myToast.mNextView = layout;
        myToast.mDuration = duration;
        return myToast;
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public int getmGravity() {
        return mGravity;
    }

    public void setmGravity(int mGravity) {
        this.mGravity = mGravity;
    }

    public float getmHorizontalMargin() {
        return mHorizontalMargin;
    }

    public void setmHorizontalMargin(float mHorizontalMargin) {
        this.mHorizontalMargin = mHorizontalMargin;
    }

    public float getmVerticalMargin() {
        return mVerticalMargin;
    }

    public void setmVerticalMargin(float mVerticalMargin) {
        this.mVerticalMargin = mVerticalMargin;
    }

    public View getmView() {
        return mView;
    }

    public void setmView(View mView) {
        this.mView = mView;
    }

    public void show() {
        mHandler.post(mShow);
        if (mDuration > 0) {
            mHandler.postDelayed(mHide, mDuration);
        }
     }

    private final Runnable mShow = new Runnable() {
        @Override
        public void run() {
            handleShow();
        }
    };

    private final Runnable mHide = new Runnable() {
        @Override
        public void run() {
            handleHide();
        }
    };

    private void handleShow() {
        if (mView != mNextView) {
            handleHide();
            mView = mNextView;

            mParams.gravity = mGravity;
            if ((mGravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
                mParams.horizontalMargin = 1.0f;
            }
            if ((mGravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
                mParams.verticalMargin = 1.0f;
            }
            mParams.x = mX;
            mParams.y = mY;
            mParams.horizontalMargin = mHorizontalMargin;
            mParams.verticalMargin = mVerticalMargin;
            if (mView.getParent() != null) {
                mWindow.removeView(mView);
            }
            mWindow.addView(mView,mParams);
        }
    }
    private void handleHide() {
        if (mView != null) {
            if (mView.getParent() != null) {
                mWindow.removeView(mView);
            }
        }
    }
}
