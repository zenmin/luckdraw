package com.zm.cj.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.zm.cj.entity.CommonException;
import com.zm.cj.entity.Persons;
import com.zm.cj.entity.ResponseEntity;
import com.zm.cj.util.RedisUtil;
import org.apache.commons.collections4.comparators.ReverseComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @Describle This Class Is
 * @Author ZengMin
 * @Date 2019/12/28 11:08
 */
@RestController
@RequestMapping("/api/opt")
public class AllOptController {

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("/add")
    public ResponseEntity addPerson(Persons persons) {
        this.validateField(persons.getName(), persons.getPhone());
        // 查询手机号是否存在
        boolean mapExists = redisUtil.getMapExists(Persons.PERSON, persons.getPhone());
        if (mapExists) {
            throw new CommonException(999, "手机号已存在，无需重复添加！");
        } else {
            Map<String, Object> map = Maps.newHashMap();
            map.put(persons.getPhone(), persons);
            redisUtil.saveMap(Persons.PERSON, map);
        }
        return ResponseEntity.success(true);
    }

    @PostMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.success(redisUtil.getMap(Persons.PERSON).values());
    }

    @PostMapping("/getByPhones")
    public ResponseEntity getByPhone(@RequestParam(required = true) String phones) {
        List<String> strings = Arrays.asList(phones.split(","));
        return ResponseEntity.success(redisUtil.getMapMultiKey(Persons.PERSON, strings));
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam(required = true) MultipartFile file) {
        ImportParams importParams = new ImportParams();
        try {
            List<Persons> objectList = ExcelImportUtil.importExcel(file.getInputStream(), Persons.class, importParams);
            Map<String, Object> map = Maps.newHashMap();
            objectList.stream().forEach(o -> {
                String phone = o.getPhone();
                if(StringUtils.isNotBlank(phone)){
                    map.put(phone, o);
                }
            });
            Map exists = redisUtil.getMap(Persons.PERSON);
            if (exists.size() > 0) {
                Boolean delete = redisUtil.delete(Persons.PERSON);
                if (delete) {
                    redisUtil.saveMap(Persons.PERSON, map);
                }
            } else {
                redisUtil.saveMap(Persons.PERSON, map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.success(true);
    }

    @PostMapping("/getNd")
    public ResponseEntity getNd() {
        return ResponseEntity.success(redisUtil.getMap(Persons.ND).values());
    }

    @PostMapping("/setNd")
    public ResponseEntity setNd(String phone) {
        Object map = redisUtil.getMap(Persons.PERSON, phone);
        if (Objects.nonNull(map)) {
            Persons persons = (Persons) map;
            redisUtil.saveMap(Persons.ND, ImmutableMap.of(persons.getPhone(), persons));
        } else {
            throw new CommonException(999, "手机号不存在！");
        }
        return this.getNd();
    }

    @PostMapping("/delNd")
    public ResponseEntity delNd(String phone) {
        redisUtil.deleteMap(Persons.ND, phone);
        return ResponseEntity.success(true);
    }


    /**
     * 验证字段
     *
     * @param args
     */
    public void validateField(String... args) {
        List<String> list = Arrays.asList(args);
        list.forEach(o -> {
            if (StringUtils.isBlank(o)) {
                throw new CommonException(999, "请填写必填项！");
            }
        });
    }


}
