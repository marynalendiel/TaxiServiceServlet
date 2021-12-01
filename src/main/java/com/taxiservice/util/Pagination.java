package com.taxiservice.util;

import com.taxiservice.model.entity.Entity;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Class that allows to paginate considering the input data
 *
 * @author Maryna Lendiel
 */
public class Pagination {

    public static void paginate(List<? extends Entity> list, HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        int numberOfRecords = list.size();
        int numberOfPages = (int)Math.ceil(numberOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("page", page);
        request.setAttribute("list", getPages(list, page, recordsPerPage));

    }

    private static List<? extends Entity> getPages(List<? extends Entity> list, int currentPage, int recordsPerPage) {
        if(recordsPerPage <= 0 || currentPage <= 0) {
            throw new IllegalArgumentException("invalid page size: " + recordsPerPage);
        }

        int startPage = (currentPage - 1) * recordsPerPage;
        if(list == null || list.size() <= startPage){
            return Collections.emptyList();
        }

        return list.subList(startPage, Math.min(startPage + recordsPerPage, list.size()));
    }
}
