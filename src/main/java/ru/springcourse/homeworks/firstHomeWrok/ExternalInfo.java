package ru.springcourse.homeworks.firstHomeWrok;


public class ExternalInfo {
    Integer id;
    String info;

    public ExternalInfo(Integer id, String info) {
        this.id = id;
        this.info = info;
    }

    @Override
    public String toString() {
        return "ExternalInfo{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
