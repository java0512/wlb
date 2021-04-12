package com.hainet.his.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hainet.his.mapper.DictMapper;
import com.hainet.model.cmn.Dict;
import com.hainet.model.cmn.vo.DictVo;
import org.springframework.beans.BeanUtils;

public class DictListener extends AnalysisEventListener<DictVo> {

    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictVo dictVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
