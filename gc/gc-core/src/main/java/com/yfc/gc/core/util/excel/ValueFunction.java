package com.yfc.gc.core.util.excel;

public interface ValueFunction<T> {

    void apply(CellFunction cell, T value);

}
