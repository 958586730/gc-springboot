package com.yfc.gc.core.util.recursive;

import com.yfc.gc.core.exception.NoParamException;
import com.yfc.gc.core.util.ObjectUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 递归工具类
 * @param <T>
 */
@Getter
public class RecursiveBuilder<T> {

    /**
     * 源数据
     */
    private List<T> sources;

    /**
     * 映射子对象
     */
    private Function<T, List<T>> mapChild;

    /**
     * 过滤器
     */
    private Predicate<T> filter;

    /**
     * 循环
     */
    private Consumer<? super T> forEach;

    /**
     * 最大获取次数
     */
    private long maxSize;

    /**
     * 执行结果集
     */
    private List<T> result;

    public RecursiveBuilder(){
        this.result = new LinkedList<>();
        maxSize = Long.MAX_VALUE;
    }

    public RecursiveBuilder(T source){
        this();
        this.sources = Arrays.asList(source);
    }
    public RecursiveBuilder(List<T> sources){
        this();
        this.sources = sources;
    }

    public RecursiveBuilder<T> mapChild(Function<T, List<T>> mapChild){
        this.mapChild = mapChild;
        return this;
    }

    public RecursiveBuilder<T> filter(Predicate<T> filter){
        this.filter = filter;
        return this;
    }

    public RecursiveBuilder<T> forEach(Consumer<? super T> forEach){
        this.forEach = forEach;
        return this;
    }

    public RecursiveBuilder<T> limit(long maxSize){
        this.maxSize = maxSize;
        return this;
    }

    public Stream<T> build(){
        if(this.mapChild == null){
            throw new NoParamException("no param mapChild ");
        }

        forEach(this.sources);
        return this.result.stream();
    }


    protected boolean forEach(List<T> sources){
        if(ObjectUtil.empty(sources)){
            return true;
        }
        for(T source: sources){
            if(one(source)){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }

    protected boolean one(T source){

        if(filter != null){
            if(filter.test(source)){
                executeForEach(source);
            }
        }else{
            executeForEach(source);
        }
        if(result.size() >= maxSize){
            return false;
        }
        return forEach(mapChild.apply(source));
    }

    private void executeForEach(T source){
        result.add(source);
        if(forEach != null){
            forEach.accept(source);
        }
    }
}
