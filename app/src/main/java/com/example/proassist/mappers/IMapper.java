package com.example.proassist.mappers;

import org.json.JSONException;

public interface IMapper<T, S> {

    public T map(S string) throws JSONException;

}
