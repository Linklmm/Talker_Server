package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/***
 * 用户关系的model
 * 用户用户直接进行好友关系的实现
 *
 * */
@Entity
@Table(name="USER_FOLLOW")
public class UserFollow {

    //这是一个主键
    @Id
    @PrimaryKeyJoinColumn
    //主键生成存储的类型为UUID
    @GeneratedValue(generator = "uuid")
    //把UUid的生成器定义为Uuid2，uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;
    //定义一个发起人，你关注某人，这里就是你
    //多对1  你可以关注很多人，你的每一次关注都是一条记录
    //你可以创建很多个关注的信息，所以是多对1：这里的多对一是User对应多个USerFollow
    //optional 不可选，必须存储，一条记录一定要有一个“你”
    @ManyToOne(optional = false)
    //定义关联的表字段名为originId,对应的是User.id
    //定义的是数据库中的存储字段
    @JoinColumn(name = "originId")
    private User origin;
    //把这个列提取到我们的modle中，不允许为空，不允许更新，插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String originId;

    //定义关注的目标，你关注的人
    //也是多对一，你可以被很多人关注，每次关注都是一条记录
    //所以就是多个UserFollow对应一个User的情况
    @ManyToOne(optional = false)
    //定义关联的表字段名为targetId,对应的是User.id
    @JoinColumn(name = "targetId")
    private User target;
    //把这个列提取到我们的modle中，不允许为null，不允许更新，插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String targetId;


    //别名，也就是对target的备注名
    @Column
    private String alias;

    //定义为创建时间戳，在创建时久已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt=LocalDateTime.now();

    //定义为更新时间戳，在创建时久已经写入
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt=LocalDateTime.now();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
