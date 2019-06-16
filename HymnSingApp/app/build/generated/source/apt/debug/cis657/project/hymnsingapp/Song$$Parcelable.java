
package cis657.project.hymnsingapp;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2019-06-16T14:39-0400")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Song$$Parcelable
    implements Parcelable, ParcelWrapper<cis657.project.hymnsingapp.Song>
{

    private cis657.project.hymnsingapp.Song song$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<Song$$Parcelable>CREATOR = new Creator<Song$$Parcelable>() {


        @Override
        public Song$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Song$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Song$$Parcelable[] newArray(int size) {
            return new Song$$Parcelable[size] ;
        }

    }
    ;

    public Song$$Parcelable(cis657.project.hymnsingapp.Song song$$2) {
        song$$0 = song$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(song$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(cis657.project.hymnsingapp.Song song$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(song$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(song$$1));
            parcel$$1 .writeString(song$$1 .reference);
            parcel$$1 .writeString(song$$1 .title);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public cis657.project.hymnsingapp.Song getParcel() {
        return song$$0;
    }

    public static cis657.project.hymnsingapp.Song read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            cis657.project.hymnsingapp.Song song$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            song$$4 = new cis657.project.hymnsingapp.Song();
            identityMap$$1 .put(reservation$$0, song$$4);
            song$$4 .reference = parcel$$3 .readString();
            song$$4 .title = parcel$$3 .readString();
            cis657.project.hymnsingapp.Song song$$3 = song$$4;
            identityMap$$1 .put(identity$$1, song$$3);
            return song$$3;
        }
    }

}
