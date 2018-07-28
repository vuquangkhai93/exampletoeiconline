package vn.myclass.core.service;

import vn.myclass.core.dto.ListenGuidelineDTO;

import java.util.List;

public interface ListenGuidelineService {
    Object[] findListenGuidelineByProperties(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
