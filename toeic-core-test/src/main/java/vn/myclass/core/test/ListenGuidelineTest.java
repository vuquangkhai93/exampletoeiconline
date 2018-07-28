package vn.myclass.core.test;

import org.testng.annotations.Test;
import vn.myclass.core.dao.ListenGuidelineDao;
import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.ListenGuidelineDaoImpl;

public class ListenGuidelineTest {
    @Test
    public void testFindByProperties(){
        ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();
        Object[] result = listenGuidelineDao.findByProperty(null,null,null,null,0,2);
    }
}
