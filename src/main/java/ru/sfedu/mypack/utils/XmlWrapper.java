package ru.sfedu.mypack.utils;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "list")
public class XmlWrapper<T> {

    @ElementList(inline = true, required = false)
    private List<T> list;

    public XmlWrapper() {
    }

    public XmlWrapper(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
