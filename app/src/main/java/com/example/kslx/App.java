package com.example.kslx;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.kslx.db.DaoMaster;
import com.example.kslx.db.DaoSession;

public class App extends Application {
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        //当前程序一运行，onCreate就先执行
        super.onCreate();
        initDB();
    }

    private void initDB() {
        // 参数1 上下文 参数2.数据库名字
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "person");
        SQLiteDatabase db = helper.getWritableDatabase();
        mDaoSession = new DaoMaster(db).newSession();
    }

    //对外提供DaoSession对象
    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
