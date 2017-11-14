package org.hongbo.Yangcl.service;

/**
 * 数据服务层接口
 * Created with IntelliJ IDEA.
 * User: Yangcl
 * Date: 13-8-23
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 */

import org.hongbo.Yangcl.entity.User;

import java.util.List;
public interface BaseService
{
    /**
     * 调阅该实体类的全部数据
     * @param cla 实体类
     * @param <T>  实体对象
     * @return 实体对象集合
     */
    public <T> List<T> find(Class<T> cla);

    /**
     * 更新数据集合
     * @param list  实体对象集合
     * @param <T>  实体对象
     */
    public <T> void saveOrUpdate(List<T> list);

    /**
     * 删除数据集合
     * @param list  实体对象集合
     * @param <T>  实体对象
     */
    public <T> void delete(List<T> list);

    public <T> void saveInfo(List<T> list);

    public <T> void updateInfo(List<T> list);


}
