package com.carpediem.randy.shanbay.common;

import android.app.Application;
import android.content.Context;

import com.carpediem.randy.shanbay.common.database.ArticleDbService;
import com.carpediem.randy.shanbay.common.database.WordDbService;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by randy on 15-9-10.
 *
 * 单例管理类
 * 所有的单例在这里管理
 */
public class ShanBayContext {
    // =============================== application =========================================
    private final static AtomicReference<Application> sApplication = new AtomicReference<Application>();

//    public static Context getApplicationContext() {
//        return
//    }
    public static void setsApplication(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("ShanBayContext setsApplication application is null");
        }
        if (sApplication.getAndSet(application) != null) {
            throw new IllegalArgumentException("Application can only be set once");
        }
    }
    public static Application getApplication() {
        return sApplication.get();
    }

    // =============================== ArticleDbService =============================
    private final static Singleton<ArticleDbService,Void> sArticleDbService = new Singleton<ArticleDbService, Void>() {
        @Override
        protected ArticleDbService create(Void aVoid) {
            return new ArticleDbService();
        }
    };

    public static ArticleDbService getArticleDbService() {
        return sArticleDbService.get(null);
    }

    // ================================ WordDbService ==============================
    private final static Singleton<WordDbService,Void> sWordDbService = new Singleton<WordDbService, Void>() {
        @Override
        protected WordDbService create(Void aVoid) {
            return new WordDbService();
        }
    };

    public static WordDbService getWordDbService() {
        return sWordDbService.get(null);
    }
}
