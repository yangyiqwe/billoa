package com.example.billoa.sys.controller;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Year;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class YearControllerTest {

    @Autowired
    private YearController yearController;

    @Test
    void getYearALl(){
        Result<List<Year>> list = yearController.list();
        System.out.println(list);

    }

}
