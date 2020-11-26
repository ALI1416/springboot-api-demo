package com.demo.result;

import java.util.List;

import org.javatuples.Pair;

import lombok.Data;

@Data
public class BatchResult {

    private List<Pair<Boolean, Object>> list;

}
