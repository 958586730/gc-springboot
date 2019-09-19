package com.yfc.gc.core.util.excel;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

@Getter
@Slf4j
public class ExportBuild<T> {

    private OutputStream outputStream;

    private Collection<String> headers;

    private Flux<T> values;

    private String sheetName;

    private ExcelType excelType;

    private HeaderFunction headerFunction;

    private ValueFunction<T> valueFunction;

    private int x;

    private int y;

    public ExportBuild(){
        headerFunction = ((cell, headerName) -> {
            cell.setCellValue(headerName);
        });
        sheetName = "sheet1";
        excelType = ExcelType.XLS;
    }

    public static <T> ExportBuild<T> newInstance(Collection<String> headers, Flux<T> values){
        ExportBuild<T> build = new ExportBuild<>();
        build.addHeaders(headers);
        build.addValues(values);
        return build;
    }

    public void execute() throws IOException {
        new Export<>(this).build();
    }

    public ExportBuild<T> addValueFunction(ValueFunction<T> valueFunction){
        this.valueFunction = valueFunction;
        return this;
    }

    public ExportBuild<T> addHeaderFunction(HeaderFunction headerFunction){
        this.headerFunction = headerFunction;
        return this;
    }

    public ExportBuild<T> addMargin(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public ExportBuild<T> addSheetName(String sheetName){
        this.sheetName = sheetName;
        return this;
    }

    public ExportBuild<T> addExcelType(ExcelType excelType){
        this.excelType = excelType;
        return this;
    }

    public ExportBuild<T> addValues(Flux<T> values){
        this.values = values;
        return this;
    }

    public ExportBuild<T> addHeaders(Collection<String> headers){
        this.headers = headers;
        return this;
    }

    public ExportBuild<T> addOutputStream(OutputStream outputStream){
        this.outputStream = outputStream;
        return this;
    }

    public enum ExcelType{
        XLS, XLSX
    }
}
