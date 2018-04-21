package net.qiujuer.web.italker.push.bean.card;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class UserCard {
    //这是一个主键
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String phone;

    @Expose
    private String portraid;
    @Expose
    private  String desc;
    @Expose
    private int sex=0;

    @Expose
    //用户关注人的数量
    private int follows;
    //用户粉丝的数量
    @Expose
    private int following;
//我与当前User的关系状态是否已经关注了这个人
    @Expose
    private int isFollow;
    @Expose
    //用户信息最后的更新时间
    private LocalDateTime modifyAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPortraid() {
        return portraid;
    }

    public void setPortraid(String portraid) {
        this.portraid = portraid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public LocalDateTime getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(LocalDateTime modifyAt) {
        this.modifyAt = modifyAt;
    }
}
