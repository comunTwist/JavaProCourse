package com.gmail.agentup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
@NamedQuery(name = "Group.findAll", query = "SELECT c FROM Group c")

public class Group {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(mappedBy = "groups", cascade = CascadeType.ALL)
    List<Student> students = new ArrayList<>();

    public Group(String title) {
        this.title = title;
    }

    public Group() {
    }

    public void addStudent(Student student) {
        if (!students.contains(student))
            students.add(student);
        if (!student.groups.contains(this))
            student.groups.add(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
