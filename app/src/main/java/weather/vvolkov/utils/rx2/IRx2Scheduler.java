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

public interface IRx2Scheduler {
    @NonNull
    Scheduler getIOScheduler();

    @NonNull
    Scheduler getMainThreadScheduler();

    @NonNull
    Scheduler getNewThreadScheduler();
}
