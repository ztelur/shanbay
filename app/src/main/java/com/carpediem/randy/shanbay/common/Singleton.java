package com.carpediem.randy.shanbay.common;

/**
 * Created by randy on 15-9-10.
 * 单例抽象类
 */
public abstract class Singleton<T,P> {
    private volatile T mInstance;

    /**
     * 创建实例
     * @param p
     * @return
     */
    protected abstract T create(P p);

    public final T get(P p) {
        if (mInstance == null) {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = create(p);
                }
            }
        }
        return mInstance;
    }
}
