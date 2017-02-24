package me.codego.custombehavior.autohide;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import me.codego.custombehavior.R;


/**
 * Created by mengxn on 17-2-14.
 */
public class AutoHideLayout extends FrameLayout {

    enum Direction {
        TOP(0),
        BOTTOM(1);

        private int id;

        Direction(int id) {
            this.id = id;
        }

        public static Direction format(int id) {
            for (Direction direction : values()) {
                if (direction.id ==id) {
                    return direction;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    private Direction mDirection;

    public AutoHideLayout(Context context) {
        super(context);
    }

    public AutoHideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AutoHideLayout, 0, 0);
        if (typedArray.hasValue(R.styleable.AutoHideLayout_direction)) {
            int dirId = typedArray.getInt(R.styleable.AutoHideLayout_direction, 0);
            mDirection = Direction.format(dirId);
        }
    }

    public Direction getDirection() {
        return mDirection;
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }

    public static class AutoHideBehavior extends CoordinatorLayout.Behavior<View> {

        private boolean isHided;

        public AutoHideBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
            return dependency instanceof NestedScrollingChild;
        }

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
            return true;
        }

        @Override
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        }

        @Override
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, final View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
            super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

            if (dyConsumed > 0 == isHided) {
                return;
            }

            Direction direction = Direction.TOP;
            if (child instanceof AutoHideLayout) {
                direction = ((AutoHideLayout) child).getDirection();
            }

            int translationY = 0;
            int alpha = 1;
            if (dyConsumed > 0) {
                //hide
                switch (direction) {
                    case TOP:
                        translationY = -child.getHeight();
                        break;
                    case BOTTOM:
                        translationY = child.getHeight();
                        break;
                }
                alpha = 0;
                isHided = true;
            } else {
                //show
                translationY = 0;
                alpha = 1;
                isHided = false;
            }
            animateView(child, translationY, alpha);
        }

        private void animateView(final View child, int translationY, int alpha) {
            child.animate().
                    translationY(translationY)
                    .alpha(alpha)
                    .setDuration(300)
                    .start();
        }
    }
}
