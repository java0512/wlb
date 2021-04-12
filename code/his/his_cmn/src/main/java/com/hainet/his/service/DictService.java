package com.hainet.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hainet.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {

    void importDictData(MultipartFile file);

    void exportDictData(HttpServletResponse response);

    List<Dict> findByDictCode(String dictCode);

    List<Dict> findChildData(Long id);

    String getDictName(String dictCode, String value);
}
