package weather.vvolkov.utils.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class RxEditText extends AppCompatEditText {
    @NonNull
    private PublishSubject<String> editTextSubject = PublishSubject.create();

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RxEditText(Context context) {
        super(context);
    }

    public RxEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RxEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (TextUtils.isEmpty(text)) return;

        editTextSubject.onNext(text.toString());
    }

    public void setOnRxTextChangeListener(RxEditTextChangeListener listener, int debounceMillis) {
        if (compositeDisposable.size() > 0) {
            compositeDisposable.dispose();
        }
        final Disposable disposable = editTextSubject.debounce(debounceMillis, MILLISECONDS, mainThread())
                .doOnNext(listener::onTextChanged)
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public interface RxEditTextChangeListener {
        void onTextChanged(String text);
    }
}
