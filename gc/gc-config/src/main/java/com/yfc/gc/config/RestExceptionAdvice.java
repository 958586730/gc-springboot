package com.yfc.gc.config;

import com.yfc.gc.bean.admin.SysUserDetails;
import com.yfc.gc.core.exception.BaseRuntimeException;
import com.yfc.gc.core.exception.SystemExeption;
import com.yfc.gc.core.thread.ThreadData;
import com.yfc.gc.core.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口异常拦截类
 * @author WangYX
 *
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionAdvice {

    private final static List<FilterException> EXCEPTION = new ArrayList<>();

    static{
        EXCEPTION.add((ex) -> (ex instanceof DataAccessException ? "数据库操作错误" : null));
        EXCEPTION.add((ex) -> (ex instanceof NullPointerException 			? "对象未初始化或者不存在" : null));
        EXCEPTION.add((ex) -> (ex instanceof IOException                    ? "数据输入输出错误" : null));
        EXCEPTION.add((ex) -> (ex instanceof ClassNotFoundException 		? "指定的类不存在" : null));
        EXCEPTION.add((ex) -> (ex instanceof ArithmeticException 			? "数学运算异常" : null));
        EXCEPTION.add((ex) -> (ex instanceof ArrayIndexOutOfBoundsException ? "数组越界异常" : null));
        EXCEPTION.add((ex) -> (ex instanceof IllegalArgumentException 		? "方法参数错误" : null));
        EXCEPTION.add((ex) -> (ex instanceof ClassCastException 			? "类型转化错误" : null));
        EXCEPTION.add((ex) -> (ex instanceof SecurityException 				? "违背安全原则错误" : null));
        EXCEPTION.add((ex) -> (ex instanceof SQLException                   ? "数据库操作异常" : null));
        EXCEPTION.add((ex) -> (ex instanceof NoSuchMethodException 			? "方法未找到" : null));
        EXCEPTION.add((ex) -> (ex instanceof BadSqlGrammarException ? "SQL语句发生错误" : null));
        EXCEPTION.add((ex) -> (ex instanceof HttpMessageNotReadableException ? "请求参数错误" : null));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Exception exp(Exception ex) {
        if(ex instanceof BaseRuntimeException){
            return ex;
        }
        log.error("---------------------------error befor-------------------------------");
        log.error("异常描述\t\t:{}", ex.getMessage());
        log.error("当前操作人\t\t:{}", Context.getSysUser().map(SysUserDetails::getUsername).orElse("null"));
        log.error("基础参数\t\t:{}", JSONUtil.toJson(ThreadData.cloneData()));
        log.error("当前线程名称\t:{}", Thread.currentThread().getName());
        log.error(ex.getMessage(), ex);
        log.error("---------------------------error after-------------------------------");

        BaseRuntimeException iem = EXCEPTION.stream().filter(fe-> fe.getIEMException(ex) != null).findFirst().map(fe->fe.getIEMException(ex)).orElse(null);
        if(iem == null) {
            iem = new SystemExeption(ex.getMessage());
        }
        return iem;
    }

    interface FilterException{

        String instanceOf(Exception ex);

        default BaseRuntimeException getIEMException(Exception ex){
            if(ex instanceof BaseRuntimeException)
                return (BaseRuntimeException) ex;
            String code = instanceOf(ex);
            return code == null ? null : new SystemExeption(ex, code);
        }
    }
}
