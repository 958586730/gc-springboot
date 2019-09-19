package com.yfc.gc.core.util.excel;

import com.yfc.gc.core.exception.NoParamException;
import com.yfc.gc.core.util.Assert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class Export<T> {

    private ExportBuild<T> exportInfo;

    public Export(ExportBuild<T> exportInfo){
        Assert.empty(exportInfo                     , "exportInfo is null!");
        Assert.empty(exportInfo.getExcelType()      , "excelType is null!");
        Assert.empty(exportInfo.getHeaderFunction() , "headerFunction is null!");
        Assert.empty(exportInfo.getValueFunction()  , "valueFunction is null!");
        Assert.empty(exportInfo.getOutputStream()   , "outputStream is null!");
        Assert.empty(exportInfo.getHeaders()        , "headers is null!");
        Assert.empty(exportInfo.getValues()         , "values is null!");
        this.exportInfo = exportInfo;
    }

    protected Workbook getWorkbook(){
        switch (exportInfo.getExcelType()){
            case XLS:
                return new HSSFWorkbook();
            case XLSX:
                return new HSSFWorkbook();
            default:
                throw new NoParamException("excelType is null!");
        }
    }

    public void build() throws IOException {
        Workbook wb = getWorkbook();
        Sheet sheet = wb.createSheet(Optional.ofNullable(exportInfo.getSheetName()).orElse(""));
        Row row = sheet.createRow(exportInfo.getY());

        int i = exportInfo.getX();
        for(String header : exportInfo.getHeaders()){
            exportInfo.getHeaderFunction().apply(row.createCell(i), header);
            i++;
        }

        IntAddOne rowNum = new IntAddOne(exportInfo.getY()+1);

        exportInfo.getValues()
                .log()
                .subscribe(val->{
                    Row valRow = sheet.createRow(rowNum.getI());
                    IntAddOne cellNum = new IntAddOne(exportInfo.getX());
                    exportInfo.getValueFunction().apply(()->{
                        Cell c = valRow.createCell(cellNum.getI());
                        cellNum.add();
                        return c;
                    }, val);
                    rowNum.add();
                });

        wb.write(exportInfo.getOutputStream());
    }

    @Getter
    @AllArgsConstructor
    class IntAddOne{
        int i;
        public void add(){
            i++;
        }
    }

}
