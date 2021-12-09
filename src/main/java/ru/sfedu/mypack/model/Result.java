package ru.sfedu.mypack.model;

import ru.sfedu.mypack.model.enums.EnumResult;


public class Result<T> {

    private T data;
    private EnumResult resultEnum;
    private String message;

    public Result(T data, EnumResult resultEnum, String message) {
        this.data = data;
        this.resultEnum = resultEnum;
        this.message = message;
    }

    public Result(EnumResult resultEnum, T data) {
        this.data = data;
        this.resultEnum = resultEnum;
    }

    public Result(EnumResult resultEnum) {
        this.resultEnum = resultEnum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public EnumResult getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(EnumResult resultEnum) {
        this.resultEnum = resultEnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data + '\'' +
                ", resultEnum=" + resultEnum + '\'' +
                ", message='" + message +
                '}';
    }
}

