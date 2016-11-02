package com.maelook.daoBean;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.maelook.Bean.singleRecord;

import com.maelook.daoBean.singleRecordDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig singleRecordDaoConfig;

    private final singleRecordDao singleRecordDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        singleRecordDaoConfig = daoConfigMap.get(singleRecordDao.class).clone();
        singleRecordDaoConfig.initIdentityScope(type);

        singleRecordDao = new singleRecordDao(singleRecordDaoConfig, this);

        registerDao(singleRecord.class, singleRecordDao);
    }
    
    public void clear() {
        singleRecordDaoConfig.clearIdentityScope();
    }

    public singleRecordDao getSingleRecordDao() {
        return singleRecordDao;
    }

}