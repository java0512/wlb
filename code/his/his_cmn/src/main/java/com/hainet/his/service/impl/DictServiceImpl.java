package com.hainet.his.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hainet.his.listener.DictListener;
import com.hainet.his.mapper.DictMapper;
import com.hainet.his.service.DictService;
import com.hainet.model.cmn.Dict;
import com.hainet.model.cmn.vo.DictVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportDictData(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "dict";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<Dict> list = baseMapper.selectList(null);
        List<DictVo> dictVoList = new ArrayList<>();
        for (Dict dict : list) {
            DictVo dictVo = new DictVo();
            BeanUtils.copyProperties(dict, dictVo);
            dictVoList.add(dictVo);
        }
        //写
        try {
            EasyExcel.write(response.getOutputStream(), DictVo.class).sheet("dict").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Dict> findByDictCode(String dictCode) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_code", dictCode);
        List<Dict> dicts = baseMapper.selectList(wrapper);
        return dicts;
    }

    // 查询列表，然后判断是否有子列表1
    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Dict> list = baseMapper.selectList(wrapper);
        for (Dict dict : list) {
            Long childId = dict.getId();
            boolean isChild = isChildren(childId);
            if (isChild) {
                dict.setHasChildren(isChild);
            }
        }
        return list;
    }

    private boolean isChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }

    @Override
    public String getDictName(String dictCode, String value) {

        QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>();
        if (StringUtils.isEmpty(dictCode)) {
            wrapper.eq("value", value);
        } else {
            wrapper.eq("value", value).eq("dict_code", dictCode);
        }
        Dict dict = baseMapper.selectOne(wrapper);
        String name = null;
        if (dict != null) {
            name = dict.getName();
        }
        return name;
    }
}
