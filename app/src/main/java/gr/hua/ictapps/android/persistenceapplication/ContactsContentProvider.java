package gr.hua.ictapps.android.persistenceapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ContactsContentProvider extends ContentProvider {

    private UriMatcher uriMatcher;
    private static final String authority = "gr.hua.ictapps.android.persistenceapplication";
    private static final String path = "contacts";
    private OpenHelper openHelper;

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority,path,1);
        uriMatcher.addURI(authority,path+"/#",2);

        openHelper = new OpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                cursor = openHelper.getRecords();
                break;
            case 2:
                int id = Integer.parseInt(uri.getLastPathSegment());
                cursor = openHelper.getRecords(id);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long result = 0;
        switch (uriMatcher.match(uri)){
            case 1:
                contentValues.put(OpenHelper.KEY_ID,755);
                result = openHelper.addRecord(contentValues);
                break;
            case 2:
                contentValues.put(OpenHelper.KEY_ID,Integer.parseInt(uri.getLastPathSegment()));
                result = openHelper.addRecord(contentValues);
                break;
        }
        Uri resultUri = Uri.parse("content://"+authority+"/"+path+"/"+result);
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
