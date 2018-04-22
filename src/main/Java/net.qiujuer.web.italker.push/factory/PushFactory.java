package net.qiujuer.web.italker.push.factory;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.base.PushModel;
import net.qiujuer.web.italker.push.bean.card.MessageCard;
import net.qiujuer.web.italker.push.bean.db.Group;
import net.qiujuer.web.italker.push.bean.db.Message;
import net.qiujuer.web.italker.push.bean.db.PushHistory;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;
import net.qiujuer.web.italker.push.utils.PushDispatcher;
import net.qiujuer.web.italker.push.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

public class PushFactory {

    public static void pushNewMessage(User sender, Message message) {
        if (sender == null || message == null)
            return;

        // 消息卡片用于发送
        MessageCard card = new MessageCard(message);
        // 要推送的字符串
        String entity = TextUtil.toJson(card);

        // 发送者
        PushDispatcher dispatcher = new PushDispatcher();

        if (message.getGroup() == null
                && Strings.isNullOrEmpty(message.getGroupId())) {
            // 给朋友发送消息

            User receiver = UserFactory.findById(message.getReceiverId());
            if (receiver == null)
                return;

            // 历史记录表字段建立
            PushHistory history = new PushHistory();
            // 普通消息类型
            history.setEntityType(PushModel.ENTITY_TYPE_MESSAGE);
            history.setEntity(entity);
            history.setReceiver(receiver);
            // 接收者当前的设备推送Id
            history.setReceiverPushId(receiver.getPushId());


            // 推送的真实Model
            PushModel pushModel = new PushModel();
            // 每一条历史记录都是独立的，可以单独的发送
            pushModel.add(history.getEntityType(), history.getEntity());

            // 把需要发送的数据，丢给发送者进行发送
            dispatcher.add(receiver, pushModel);

            // 保存到数据库
            Hib.queryOnly(session -> session.save(history));
        } else {

            Group group = message.getGroup();
            // 因为延迟加载情况可能为null，需要通过Id查询
            //if (group == null)
                //group = GroupFactory.findById(message.getGroupId());

            // 如果群真的没有，则返回
            if (group == null)
                return;

            /* 给群成员发送消息
            Set<GroupMember> members = GroupFactory.getMembers(group);
            if (members == null || members.size() == 0)
                return;

            // 过滤我自己
            members = members.stream()
                    .filter(groupMember -> !groupMember.getUserId()
                            .equalsIgnoreCase(sender.getId()))
                    .collect(Collectors.toSet());
            if (members.size() == 0)
                return;*/

            // 一个历史记录列表
            List<PushHistory> histories = new ArrayList<>();

            /*addGroupMembersPushModel(dispatcher, // 推送的发送者
                    histories, // 数据库要存储的列表
                    members,    // 所有的成员
                    entity, // 要发送的数据
                    PushModel.ENTITY_TYPE_MESSAGE); // 发送的类型

            // 保存到数据库的操作
            Hib.queryOnly(session -> {
                for (PushHistory history : histories) {
                    session.saveOrUpdate(history);
                }
            });*/
        }


        // 发送者进行真实的提交
        dispatcher.submit();

    }



    /**
     * 推送账户退出消息
     *
     * @param receiver 接收者
     * @param pushId   这个时刻的接收者的设备Id
     */
    public static void pushLogout(User receiver, String pushId) {
        // 历史记录表字段建立
        PushHistory history = new PushHistory();
        // 你被添加到群的类型
        history.setEntityType(PushModel.ENTITY_TYPE_LOGOUT);
        history.setEntity("Account logout!!!");
        history.setReceiver(receiver);
        history.setReceiverPushId(pushId);
        // 保存到历史记录表
        Hib.queryOnly(session -> session.save(history));

        // 发送者
        PushDispatcher dispatcher = new PushDispatcher();
        // 具体推送的内容
        PushModel pushModel = new PushModel()
                .add(history.getEntityType(), history.getEntity());

        // 添加并提交到第三方推送
        dispatcher.add(receiver, pushModel);
        dispatcher.submit();
    }

}
