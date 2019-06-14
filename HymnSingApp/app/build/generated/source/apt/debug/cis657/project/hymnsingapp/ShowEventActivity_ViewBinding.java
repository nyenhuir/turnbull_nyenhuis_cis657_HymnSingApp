// Generated code from Butter Knife. Do not modify!
package cis657.project.hymnsingapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShowEventActivity_ViewBinding<T extends ShowEventActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ShowEventActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.eventLocation = Utils.findRequiredViewAsType(source, R.id.Location, "field 'eventLocation'", TextView.class);
    target.eventTitle = Utils.findRequiredViewAsType(source, R.id.Title, "field 'eventTitle'", TextView.class);
    target.eventTime = Utils.findRequiredViewAsType(source, R.id.Time, "field 'eventTime'", TextView.class);
    target.eventDate = Utils.findRequiredViewAsType(source, R.id.Date, "field 'eventDate'", TextView.class);
    target.editButton = Utils.findRequiredViewAsType(source, R.id.editbutton, "field 'editButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.eventLocation = null;
    target.eventTitle = null;
    target.eventTime = null;
    target.eventDate = null;
    target.editButton = null;

    this.target = null;
  }
}
