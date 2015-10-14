package com.fitforever.anim;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

/**
 * Created by sarvex on 14/10/15.
 */
public class ScrollAwareFabBehavior extends FloatingActionButton.Behavior {
  // ...

  @Override
  public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                             View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
        dyUnconsumed);

    if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
      child.hide();
    } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
      child.show();
    }
  }

  // ...
}
