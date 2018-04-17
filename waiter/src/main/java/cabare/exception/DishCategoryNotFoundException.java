package cabare.exception;

public class DishCategoryNotFoundException extends ApplicationException {
    private static String msg = "dish category is not found";

    public DishCategoryNotFoundException(){
        super(msg);
    }

    public DishCategoryNotFoundException(String message){
        super(msg + "; " + message);
    }
}
