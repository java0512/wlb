package com.hainet.his.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hainet.his.user.mapper.PatientMapper;
import com.hainet.his.user.service.PatientService;
import com.hainet.model.user.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

    @Override
    public List<Patient> findAllUserId(Long userId) {
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Patient> list = baseMapper.selectList(wrapper);
        //TODO
        return list;
    }

    @Override
    public Patient getPatientId(Long id) {
        //TODO
        Patient patient = baseMapper.selectById(id);
        return patient;
    }
}
