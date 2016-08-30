package com.openhabbo.core.storage.dao.groups;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.game.groups.types.GroupData;
import com.openhabbo.api.game.groups.types.GroupType;
import com.openhabbo.api.game.groups.types.members.GroupAccessLevel;
import com.openhabbo.api.game.groups.types.members.GroupMember;
import com.openhabbo.api.storage.dao.groups.GroupDao;
import com.openhabbo.api.threading.EventScheduler;
import com.openhabbo.core.storage.ConnectionProvider;
import com.openhabbo.core.storage.Transaction;
import com.openhabbo.core.storage.dao.AbstractDao;
import com.openhabbo.core.storage.dao.DaoEvent;
import com.openhabbo.core.storage.dao.DaoEventScheduler;
import com.openhabbo.core.storage.dao.groups.beans.GroupDataBean;
import com.openhabbo.core.storage.dao.groups.beans.GroupMemberBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

@Singleton
public class CoreGroupDao extends AbstractDao implements GroupDao {
    private final Logger log = LogManager.getLogger(CoreGroupDao.class);

    @Inject
    public CoreGroupDao(EventScheduler eventScheduler, DaoEventScheduler daoEventScheduler, ConnectionProvider connectionProvider) {
        super(eventScheduler, daoEventScheduler, connectionProvider);
    }

    @Override
    public GroupData getDataById(int id) {
//        try (Transaction transaction = this.createTransaction("SELECT `name`, `description`, `badge`, `owner_id`, `room_id`, `type`, `colour1`, `colour2`, `members_deco`, `has_forum` FROM groups WHERE id = :id")) {
//            transaction.bindParam("id", id);
//
//            while (transaction.getResults().hasResults()) {
//                return new GroupDataBean(id,
//                        transaction.getResults().getString("name"), transaction.getResults().getString("description"),
//                        transaction.getResults().getString("badge"), transaction.getResults().getInteger("owner_id"),
//                        transaction.getResults().getInteger("room_id"), GroupType.valueOf(transaction.getResults().getString("type").toUpperCase()),
//                        transaction.getResults().getInteger("colour1"), transaction.getResults().getInteger("colour2"),
//                        transaction.getResults().getString("members_deco").equals("1"), transaction.getResults().getString("has_forum").equals("1"));
//            }
//        } catch (Exception e) {
//            log.error("Failed to execute transaction: GroupData.getDataById", e);
//        }

        return null;
    }

    @Override
    public Set<GroupMember> getMembersForGroup(int groupId) {
        final Set<GroupMember> groupMembers = Sets.newConcurrentHashSet();

//        try (Transaction transaction = this.createTransaction("SELECT id, player_id, access_level, date_joined FROM group_memberships WHERE group_id = :id")) {
//            transaction.bindParam("id", groupId);
//
//            while (transaction.getResults().hasResults()) {
//                groupMembers.add(new GroupMemberBean(
//                        transaction.getResults().getInteger("id"),
//                        transaction.getResults().getInteger("player_id"),
//                        groupId,
//                        GroupAccessLevel.valueOf(transaction.getResults().getString("access_level").toUpperCase()),
//                        transaction.getResults().getInteger("date_joined")));
//            }
//        } catch (Exception e) {
//            log.error("Failed to execute transaction: GroupData.getMembersForGroup", e);
//        }

        return groupMembers;
    }
}
