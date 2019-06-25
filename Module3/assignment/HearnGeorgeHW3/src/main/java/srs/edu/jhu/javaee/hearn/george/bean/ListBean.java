package srs.edu.jhu.javaee.hearn.george.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ListBean<T> {

  private List<T> list;

  public ListBean() {
  }

  public ListBean(List<T> list) {
    this.list = list;
  }

  public List<T> getList() {
    return this.list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public ListBean list(List<T> list) {
    this.list = list;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ListBean)) {
            return false;
        }
        ListBean listBean = (ListBean) o;
        return Objects.equals(list, listBean.list);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(list);
  }

  @Override
  public String toString() {
    return "{" +
      " list='" + getList() + "'" +
      "}";
  }

 
}