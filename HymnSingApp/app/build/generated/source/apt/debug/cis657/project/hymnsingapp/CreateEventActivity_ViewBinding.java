// Generated code from Butter Knife. Do not modify!
package cis657.project.hymnsingapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateEventActivity_ViewBinding<T extends CreateEventActivity> implements Unbinder {
  protected T target;

  private View view2131230781;

  private View view2131230805;

  @UiThread
  public CreateEventActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.eventLocation = Utils.findRequiredViewAsType(source, R.id.eventLoc, "field 'eventLocation'", EditText.class);
    target.eventTitle = Utils.findRequiredViewAsType(source, R.id.eventTitle, "field 'eventTitle'", EditText.class);
    target.eventTime = Utils.findRequiredViewAsType(source, R.id.eventTime, "field 'eventTime'", EditText.class);
    view = Utils.findRequiredView(source, R.id.date, "field 'eventDateView' and method 'datePressed'");
    target.eventDateView = Utils.castView(view, R.id.date, "field 'eventDateView'", TextView.class);
    view2131230781 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.datePressed();
      }
    });
    view = Utils.findRequiredView(source, R.id.fab, "method 'FABPressed'");
    view2131230805 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.FABPressed();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.eventLocation = null;
    target.eventTitle = null;
    target.eventTime = null;
    target.eventDateView = null;

    view2131230781.setOnClickListener(null);
    view2131230781 = null;
    view2131230805.setOnClickListener(null);
    view2131230805 = null;

    this.target = null;
  }
}
