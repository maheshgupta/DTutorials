package service.core;


public interface ServiceResponseListener<T> {

    void onSuccess(T response);

    void onError(String errMessage);

}
