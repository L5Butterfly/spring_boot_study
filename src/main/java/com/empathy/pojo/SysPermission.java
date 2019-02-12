package com.empathy.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_permission")
public class SysPermission {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型：
0：顶级根权限
1：菜单,间接代表就是 isParent=true
2：普通链接（按钮，link等）
     */
    private Integer type;

    /**
     * 访问url地址
     */
    private String url;

    /**
     * 权限代码字符串
     */
    private String percode;

    /**
     * 父结点id
为0代表根节点
     */
    private Integer parentid;

    /**
     * 父结点id列表串
     */
    private String parentids;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 是否可用,1：可用，0不可用
     */
    private Integer available;

    /**
     * 当前资源描述
     */
    private String description;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源类型：
0：顶级根权限
1：菜单,间接代表就是 isParent=true
2：普通链接（按钮，link等）
     *
     * @return type - 资源类型：
0：顶级根权限
1：菜单,间接代表就是 isParent=true
2：普通链接（按钮，link等）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置资源类型：
0：顶级根权限
1：菜单,间接代表就是 isParent=true
2：普通链接（按钮，link等）
     *
     * @param type 资源类型：
0：顶级根权限
1：菜单,间接代表就是 isParent=true
2：普通链接（按钮，link等）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取访问url地址
     *
     * @return url - 访问url地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置访问url地址
     *
     * @param url 访问url地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取权限代码字符串
     *
     * @return percode - 权限代码字符串
     */
    public String getPercode() {
        return percode;
    }

    /**
     * 设置权限代码字符串
     *
     * @param percode 权限代码字符串
     */
    public void setPercode(String percode) {
        this.percode = percode;
    }

    /**
     * 获取父结点id
为0代表根节点
     *
     * @return parentid - 父结点id
为0代表根节点
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 设置父结点id
为0代表根节点
     *
     * @param parentid 父结点id
为0代表根节点
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取父结点id列表串
     *
     * @return parentids - 父结点id列表串
     */
    public String getParentids() {
        return parentids;
    }

    /**
     * 设置父结点id列表串
     *
     * @param parentids 父结点id列表串
     */
    public void setParentids(String parentids) {
        this.parentids = parentids;
    }

    /**
     * 获取排序号
     *
     * @return sort - 排序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序号
     *
     * @param sort 排序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取是否可用,1：可用，0不可用
     *
     * @return available - 是否可用,1：可用，0不可用
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 设置是否可用,1：可用，0不可用
     *
     * @param available 是否可用,1：可用，0不可用
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * 获取当前资源描述
     *
     * @return description - 当前资源描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置当前资源描述
     *
     * @param description 当前资源描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}