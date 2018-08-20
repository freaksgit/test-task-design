package vasyl.v.stoliarchuk.material.popup;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.labo.kaji.relativepopupwindow.RelativePopupWindow;

import vasyl.v.stoliarchuk.material.R;

public class CardPopup extends RelativePopupWindow {

    private final TextView popupText;

    public CardPopup(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.pop_up, null);
        popupText = view.findViewById(R.id.pop_up_text);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(context.getResources().getDrawable(android.R.drawable.dialog_holo_light_frame));

        // Disable default animation for circular reveal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setAnimationStyle(0);
            view.setElevation(10f);
        }
    }

    @Override
    public void showOnAnchor(@NonNull View anchor, int vertPos, int horizPos, int x, int y, boolean fitInScreen) {
        super.showOnAnchor(anchor, vertPos, horizPos, x, y, fitInScreen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularReveal(anchor);
        }
    }

    public void setText(@StringRes int resId) {
        popupText.setText(resId);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void circularReveal(@NonNull final View anchor) {
        final View contentView = getContentView();
        contentView.post(new Runnable() {
            @Override
            public void run() {
                final int[] myLocation = new int[2];
                final int[] anchorLocation = new int[2];
                contentView.getLocationOnScreen(myLocation);
                anchor.getLocationOnScreen(anchorLocation);
                final int cx = anchorLocation[0] - myLocation[0] + anchor.getWidth() / 2;
                final int cy = anchorLocation[1] - myLocation[1] + anchor.getHeight() / 2;

                contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                final int dx = Math.max(cx, contentView.getMeasuredWidth() - cx);
                final int dy = Math.max(cy, contentView.getMeasuredHeight() - cy);
                final float finalRadius = (float) Math.hypot(dx, dy);
                Animator animator = ViewAnimationUtils.createCircularReveal(contentView, cx, cy, 0f, finalRadius);
                animator.setDuration(500);
                animator.start();
            }
        });
    }
}
