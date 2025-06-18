package com.back;

public class PersonService {
    private final int version;

    public PersonService(){
        this(1);
    }

    public PersonService(int version) {
        this.version = version;
    }

    public long count(){
        return 3;
    }
}