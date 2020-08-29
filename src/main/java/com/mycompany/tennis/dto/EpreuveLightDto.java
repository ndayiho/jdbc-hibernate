package com.mycompany.tennis.dto;

import com.mycompany.tennis.entity.Epreuve;

public class EpreuveLightDto extends Epreuve {

    private Long id;

    private Short year;

    private char type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }


}
