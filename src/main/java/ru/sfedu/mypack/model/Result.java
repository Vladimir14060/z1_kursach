package ru.sfedu.mypack.model;

import java.util.List;

public class Result<T> {

    private List<T> data;
    private EnumResult resultEnum;
    private String message;


    public Result(List<T> data, EnumResult resultEnum, String message) {
        this.resultEnum = resultEnum;
        this.message = message;
        this.data = data;
    }

    public void setResultEnum(EnumResult resultEnum) {
        this.resultEnum = resultEnum;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public EnumResult getResultEnum() {
        return resultEnum;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultEnum=" + resultEnum +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}

