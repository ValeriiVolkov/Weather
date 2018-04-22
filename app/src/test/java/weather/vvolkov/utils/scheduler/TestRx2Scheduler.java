/*
 * Copyright (c) New Cloud Technologies, Ltd., 2013-2018
 *
 * You can not use the contents of the file in any way without New Cloud Technologies, Ltd. written permission.
 * To obtain such a permit, you should contact New Cloud Technologies, Ltd. at http://ncloudtech.com/contact.html
 *
 */

package weather.vvolkov.utils.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import weather.vvolkov.utils.rx2.IRx2Scheduler;

public class TestRx2Scheduler implements IRx2Scheduler {
    @NonNull
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler getMainThreadScheduler() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler getNewThreadScheduler() {
        return Schedulers.trampoline();
    }
}
