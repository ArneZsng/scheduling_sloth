package de.nak.scheduling_sloth.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hndrkmkt on 06.11.14.
 */
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Audience extends SchedulingObject {
    /** The identifier. */
    protected Long id;

    /** The name of the audience. */
    protected String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    @Column(length = 100, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /** The courses of this century. */
    protected Set<Course> courses;

    public abstract Integer retrieveAudienceSize();

    @OneToMany(fetch = FetchType.LAZY)
    public Set<Course> getCourses() {
        return courses;
    }
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
