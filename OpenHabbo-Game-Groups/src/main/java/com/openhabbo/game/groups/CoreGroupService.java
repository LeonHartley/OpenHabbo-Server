package com.openhabbo.game.groups;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.openhabbo.api.game.groups.GroupFactory;
import com.openhabbo.api.game.groups.GroupService;
import com.openhabbo.api.game.groups.types.Group;
import com.openhabbo.api.storage.dao.groups.GroupDao;
import com.openhabbo.game.groups.types.GroupInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class CoreGroupService implements GroupService {
    private final Logger log = LogManager.getLogger(CoreGroupService.class);

    private final GroupFactory groupFactory;
    private final GroupCache groupCache;
    private final GroupDao groupDao;

    @Inject
    public CoreGroupService(GroupFactory groupFactory, GroupDao groupDao, GroupCache groupCache) {
        this.groupFactory = groupFactory;
        this.groupDao = groupDao;
        this.groupCache = groupCache;
    }

    @Override
    public Group getGroupById(int id) {
        if (this.groupCache.contains(id)) {
            return this.groupCache.get(id);
        }

        Group group = this.groupFactory.create(this.groupDao.getDataById(id));

        this.groupCache.put(id, (GroupInstance) group);
        return group;
    }

    @Override
    public Group getGroupByRoomId(int id) {
        return null;
    }
}
