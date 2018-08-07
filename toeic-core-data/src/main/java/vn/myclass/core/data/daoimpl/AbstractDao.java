package vn.myclass.core.data.daoimpl;

import org.hibernate.*;
import vn.myclass.core.common.constant.CoreConstant;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private Class<T> persistenceClass;

    public AbstractDao(){
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName(){
        return persistenceClass.getSimpleName();
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public T findById(ID id) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            result = (T) session.get(persistenceClass, id);
            if(result == null){
                throw new ObjectNotFoundException("NOT FOUND"+id,null);
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }

        return result;
    }

//   public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit) {
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object totalItem = 0;
        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i=0;
        for (Map.Entry item : property.entrySet()){
            params[i] = (String) item.getKey();
            values[i] = item.getValue();
            i++;
        }
        try{
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(this.getPersistenceClassName());
            if (property.size()>0){
                for (int i1 =0; i1 < params.length; i1++ ){
                    if (i1==0){
                        sql1.append(" where ").append(params[i1]).append(" = :"+params[i1]+" ");
                    }else{
                        sql1.append(" and ").append(params[i1]).append(" = :"+params[i1]+" ");
                    }
                }
            }
            if(sortExpression != null && sortDirection != null){
                sql1.append("order by ").append(sortDirection);
                sql1.append(" " + (sortDirection.equalsIgnoreCase(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query1 = session.createQuery(sql1.toString());
            if (property.size()>0){
                for (int i2 = 0; i2 < params.length; i2++){
                    query1.setParameter(params[i2], values[i2]);
                }
            }
            if (offset != null && offset >= 0){
                query1.setFirstResult(offset);
            }
            if (limit != null && limit > 0){
                query1.setMaxResults(limit);
            }
            list = query1.list();

            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(this.getPersistenceClassName());
            if (property.size()>0){
                for (int k =0; k < params.length; k++ ){
                    if (k==0){
                        sql2.append(" where ").append(params[k]).append(" = :"+params[k]+" ");
                    }else{
                        sql2.append(" and ").append(params[k]).append(" = :"+params[k]+" ");
                    }
                }
            }
            Query query2 = session.createQuery(sql2.toString());
            if (property.size()>0){
                for (int k1 = 0; k1 < params.length; k1++){
                    query2.setParameter(params[k1], values[k1]);
                }
            }
            totalItem = query2.list().get(0);

            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            for(ID item : ids){
                T t = (T) session.get(persistenceClass, item);
                session.delete(t);
                count++;
            }

            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return count;
    }

}
