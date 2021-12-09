package ru.sfedu.mypack.model.history;

import java.util.Date;
import java.util.Objects;

import ru.sfedu.mypack.model.enums.EnumResult;

public class HistoryContent {
    private String className;
    private Date createDate;
    private String actor;
    private String methodName;
    private Object object;
    private EnumResult result;

    public HistoryContent(){
    }
    public HistoryContent(String className, Date createDate, String actor, String methodName, Object object, EnumResult result) {
        this.className = className;
        this.createDate = createDate;
        this.actor = actor;
        this.methodName = methodName;
        this.object = object;
        this.result = result;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public EnumResult getResult() {
        return result;
    }

    public void setResult(EnumResult result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryContent that = (HistoryContent) o;
        return Objects.equals(className, that.className) && Objects.equals(createDate, that.createDate) && Objects.equals(actor, that.actor) && Objects.equals(methodName, that.methodName) && Objects.equals(object, that.object) && result == that.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, createDate, actor, methodName, object, result);
    }

    @Override
    public String toString() {
        return "HistoryContent{" +
                "className='" + className + '\'' +
                ", createDate=" + createDate + '\'' +
                ", actor='" + actor + '\'' +
                ", methodName='" + methodName + '\'' +
                ", object=" + object + '\'' +
                ", result=" + result +
                '}';
    }
}
