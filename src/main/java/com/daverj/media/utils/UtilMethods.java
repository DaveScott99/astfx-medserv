package com.daverj.media.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UtilMethods {

    public <T> Page<T> convertListToPagination(Pageable pageable, List<T> list) {

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        if (start > list.size() || start < 0 || end < 0 || start > end) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        List<T> pagedList = list.subList(start, end);

        return new PageImpl<>(pagedList, pageable, list.size());

    }

}