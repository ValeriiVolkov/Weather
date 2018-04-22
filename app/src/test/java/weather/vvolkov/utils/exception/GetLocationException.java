package weather.vvolkov.utils.exception;

public class GetLocationException extends IllegalStateException {
    public GetLocationException() {
    }

    public GetLocationException(String s) {
        super(s);
    }
}
