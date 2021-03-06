package com.example.rlagk.ks_project001.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.rlagk.ks_project001.Activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DBHelperUtils extends SQLiteOpenHelper{

    public static final String TAG = "DBHelperUtils";

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager.db";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE_URI_LIST = "uri_list";

    private static volatile DBHelperUtils sInstance;

    public DBHelperUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelperUtils getInstance(){
        if (sInstance == null){
            sInstance = new DBHelperUtils(MainActivity.getInstance());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"Create DB TABLE!");
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT," + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_IMAGE_URI_LIST + " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * CRUD 함수
     */

    // 새로운 Contact 함수 추가
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getId());
        values.put(KEY_DATE, String.valueOf(contact.getDate()));
        values.put(KEY_TITLE, contact.getTitle());
        values.put(KEY_DESCRIPTION, contact.getDescription());
        values.put(KEY_IMAGE_URI_LIST, contact.getImageUriList() == null ? null : contact.getImageUriList().toString());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    public List<Contact> getContacts(int maxCount) {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " LIMIT " + maxCount;
        Log.d(TAG,"selectQuery ::: " + selectQuery );
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            Log.d(TAG,"cursor ::: " + cursor);
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getLong(0));
                contact.setDate(cursor.getString(1));
                contact.setTitle(cursor.getString(2));
                contact.setDescription(cursor.getString(3));
                contact.setImageUriList(cursor.getString(4) == null ? null : Uri.parse(cursor.getString(4)));
                // Adding contact to list
                Log.d(TAG,"contact ::: " + contact);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public List<Contact> getContacts(String date) {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " WHERE " + KEY_DATE + " = " + date.toString();
        Log.d(TAG,"selectQuery ::: " + selectQuery );
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            Log.d(TAG,"cursor ::: " + cursor.toString());
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getLong(0));
                contact.setDate(cursor.getString(1));
                contact.setTitle(cursor.getString(2));
                contact.setDescription(cursor.getString(3));
                contact.setImageUriList(Uri.parse(cursor.getString(4) == null ? "" : cursor.getString(4)));
                // Adding contact to list
                Log.d(TAG,"contact ::: " + contact.toString());
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // id 에 해당하는 Contact 객체 가져오기
    public Contact getContact(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_DATE, KEY_TITLE, KEY_DESCRIPTION, KEY_IMAGE_URI_LIST }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                Uri.parse(cursor.getString(4) == null ? "" : cursor.getString(4)));
        // return contact
        return contact;
    }


    // 모든 Contact 정보 가져오기
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getLong(0));
                contact.setDate(cursor.getString(1));
                contact.setTitle(cursor.getString(2));
                contact.setDescription(cursor.getString(3));
                contact.setImageUriList(Uri.parse(cursor.getString(4)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    //Contact 정보 업데이트
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getId());
        values.put(KEY_TITLE, contact.getTitle());
        values.put(KEY_DATE, contact.getDate());
        values.put(KEY_DESCRIPTION, contact.getDescription());
        values.put(KEY_IMAGE_URI_LIST, contact.getImageUriList().toString());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }


    // Contact 정보 삭제하기
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }

    // Contact 정보 숫자
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int getDatabaseVersion(){
        return DATABASE_VERSION;
    }
}

