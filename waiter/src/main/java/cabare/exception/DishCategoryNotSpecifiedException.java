package cabare.exception;

public class DishCategoryNotSpecifiedException extends ApplicationException {
    private static String msg = "dish category id is not specified";

    public DishCategoryNotSpecifiedException() {
        super(msg);
    }

    public DishCategoryNotSpecifiedException(String message){
        super(msg + "; " + message);
    }
}
