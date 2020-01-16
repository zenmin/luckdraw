package com.zm.cj.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Describle This Class Is
 * @Author ZengMin
 * @Date 2019/12/28 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("Entity")
public class Persons implements Serializable {

    public static final String PERSON = "PERSON:ALL";     // 人员缓存key

    public static final String ND = "PERSON:ND";     // 内定

    @Excel(name = "工号")
    private String no;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "手机号")
    private String phone;

}
