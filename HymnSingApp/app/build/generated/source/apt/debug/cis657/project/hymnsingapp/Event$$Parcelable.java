
package cis657.project.hymnsingapp;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2019-06-13T15:48-0400")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Event$$Parcelable
    implements Parcelable, ParcelWrapper<cis657.project.hymnsingapp.Event>
{

    private cis657.project.hymnsingapp.Event event$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<Event$$Parcelable>CREATOR = new Creator<Event$$Parcelable>() {


        @Override
        public Event$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Event$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Event$$Parcelable[] newArray(int size) {
            return new Event$$Parcelable[size] ;
        }

    }
    ;

    public Event$$Parcelable(cis657.project.hymnsingapp.Event event$$2) {
        event$$0 = event$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(event$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(cis657.project.hymnsingapp.Event event$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(event$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(event$$1));
            parcel$$1 .writeString(event$$1 .date);
            if (event$$1 .songs == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(event$$1 .songs.size());
                for (java.lang.String string$$0 : ((List<java.lang.String> ) event$$1 .songs)) {
                    parcel$$1 .writeString(string$$0);
                }
            }
            parcel$$1 .writeString(event$$1 .location);
            parcel$$1 .writeString(event$$1 .time);
            parcel$$1 .writeString(event$$1 .title);
            parcel$$1 .writeString(event$$1 ._eventkey);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public cis657.project.hymnsingapp.Event getParcel() {
        return event$$0;
    }

    public static cis657.project.hymnsingapp.Event read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            cis657.project.hymnsingapp.Event event$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            event$$4 = new cis657.project.hymnsingapp.Event();
            identityMap$$1 .put(reservation$$0, event$$4);
            event$$4 .date = parcel$$3 .readString();
            int int$$0 = parcel$$3 .readInt();
            ArrayList<java.lang.String> list$$0;
            if (int$$0 < 0) {
                list$$0 = null;
            } else {
                list$$0 = new ArrayList<java.lang.String>(int$$0);
                for (int int$$1 = 0; (int$$1 <int$$0); int$$1 ++) {
                    list$$0 .add(parcel$$3 .readString());
                }
            }
            event$$4 .songs = list$$0;
            event$$4 .location = parcel$$3 .readString();
            event$$4 .time = parcel$$3 .readString();
            event$$4 .title = parcel$$3 .readString();
            event$$4 ._eventkey = parcel$$3 .readString();
            cis657.project.hymnsingapp.Event event$$3 = event$$4;
            identityMap$$1 .put(identity$$1, event$$3);
            return event$$3;
        }
    }

}
