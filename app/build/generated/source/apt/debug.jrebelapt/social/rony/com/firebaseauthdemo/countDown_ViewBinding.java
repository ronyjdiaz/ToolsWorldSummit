// Generated code from Butter Knife. Do not modify!
package social.rony.com.firebaseauthdemo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class countDown_ViewBinding<T extends countDown> implements Unbinder {
  protected T target;

  @UiThread
  public countDown_ViewBinding(T target, View source) {
    this.target = target;

    target.mDaysLabel = Utils.findRequiredViewAsType(source, R.id.activity_countdown_timer_days_text, "field 'mDaysLabel'", TextView.class);
    target.mHoursLabel = Utils.findRequiredViewAsType(source, R.id.activity_countdown_timer_hours_text, "field 'mHoursLabel'", TextView.class);
    target.mMinutesLabel = Utils.findRequiredViewAsType(source, R.id.activity_countdown_timer_minutes_text, "field 'mMinutesLabel'", TextView.class);
    target.mSecondsLabel = Utils.findRequiredViewAsType(source, R.id.activity_countdown_timer_seconds_text, "field 'mSecondsLabel'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mDaysLabel = null;
    target.mHoursLabel = null;
    target.mMinutesLabel = null;
    target.mSecondsLabel = null;

    this.target = null;
  }
}
