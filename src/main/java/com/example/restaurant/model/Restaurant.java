package com.example.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List <Dish> menu;

    @Column(name = "creationDay")
    private LocalDate creationDay;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
        creationDay = LocalDate.now();
    }

    public Restaurant(Integer id, String name, LocalDate creationDay) {
        super(id, name);
        this.creationDay = creationDay;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public LocalDate getCreationDay() {
        return creationDay;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public void setCreationDay(LocalDate creationDay) {
        this.creationDay = creationDay;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name" + name +
                "id=" + id +
                '}';
    }
}
