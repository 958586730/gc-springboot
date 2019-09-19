package ${targetPackage};

import ${targetInterfacePackage}.I${tableClass.shortClassName}Service;
import ${tableClass.fullClassName};
import com.yfc.gc.core.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import ${targetReadPackage}.${tableClass.shortClassName}${mapperReadSuffix};
import ${targetWriterPackage}.${tableClass.shortClassName}${mapperWriterSuffix};

@Service
public class ${tableClass.shortClassName}ServiceImpl extends BaseServiceImpl<${tableClass.shortClassName}> implements I${tableClass.shortClassName}Service {

    public ${tableClass.shortClassName}${mapperWriterSuffix} getWriterMapper(){
        return (${tableClass.shortClassName}${mapperWriterSuffix}) writerBaseMapper;
    }

    public ${tableClass.shortClassName}${mapperReadSuffix} getReadMapper(){
        return (${tableClass.shortClassName}${mapperReadSuffix}) readBaseMapper;
    }
}
