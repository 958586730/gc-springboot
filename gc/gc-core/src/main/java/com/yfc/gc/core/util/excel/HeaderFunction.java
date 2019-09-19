package com.yfc.gc.core.util.excel;

import org.apache.poi.ss.usermodel.Cell;

public interface HeaderFunction {

    void apply(Cell cell, String headerName);

}
