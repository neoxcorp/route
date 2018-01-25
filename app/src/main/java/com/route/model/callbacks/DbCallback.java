package com.route.model.callbacks;

import java.util.List;

public interface DbCallback<T> {

    void showListOfData(List<T> list);

}
