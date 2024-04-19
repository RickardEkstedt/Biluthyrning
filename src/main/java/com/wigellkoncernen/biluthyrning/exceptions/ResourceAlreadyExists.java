package com.wigellkoncernen.biluthyrning.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExists extends RuntimeException {

    private String name;
    private String field;
    private Object value;

    public ResourceAlreadyExists(String name, String field, Object value) {
        super(String.format("%s with %s: %s already exists", name, field, value));
        this.name = name;
        this.field = field;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
