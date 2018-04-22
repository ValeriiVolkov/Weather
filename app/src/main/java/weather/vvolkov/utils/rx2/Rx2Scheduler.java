/*
 * Copyright (c) New Cloud Technologies, Ltd., 2013-2018
 *
 * You can not use the contents of the file in any way without New Cloud Technologies, Ltd. written permission.
 * To obtain such a permit, you should contact New Cloud Technologies, Ltd. at http://ncloudtech.com/contact.html
 *
 */

package weather.vvolkov.utils.rx2;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Rx2Scheduler implements IRx2Scheduler {
    @NonNull
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public Scheduler getNewThreadScheduler() {
        return Schedulers.newThread();
    }
}
