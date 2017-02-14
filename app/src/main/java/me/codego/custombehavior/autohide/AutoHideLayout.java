package me.codego.custombehavior.autohide;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by mengxn on 17-2-14.
 */
public class AutoHideLayout extends FrameLayout {

    enum Direction {
        UP,
        DOWN,
    }

    public AutoHideLayout(Context context) {
        super(context);
    }

    public AutoHideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static class AutoHideBehavior extends CoordinatorLayout.Behavior<View> {

        private Direction mDirection;

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
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, final View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
            super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

            Direction newDirection = dyConsumed > 0 ? Direction.DOWN : Direction.UP;
            if (mDirection == newDirection) {
                return;
            }
            mDirection = newDirection;
            child.animate().cancel();
            switch (mDirection) {
                case UP:
                    animateView(child, 0, 1);
                    break;
                case DOWN:
                    animateView(child, child.getHeight(), 0);
                    break;
                default:
                    break;
            }
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
