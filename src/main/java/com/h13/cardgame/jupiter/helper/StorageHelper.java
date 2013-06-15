package com.h13.cardgame.jupiter.helper;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.co.StorageCO;
import com.h13.cardgame.cache.service.StorageCache;
import com.h13.cardgame.jupiter.dao.StorageDAO;
import com.h13.cardgame.jupiter.utils.LogWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-21
 * Time: 上午12:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class StorageHelper {

    @Autowired
    StorageDAO storageDAO;

    @Autowired
    LevelHelper levelHelper;
    @Autowired
    StorageCache storageCache;

    public StorageCO getByCid(long cid) {
        StorageCO storageCO = storageCache.get(cid);
        if (storageCO == null) {
            storageCO = storageDAO.getByCid(cid);
            cache(storageCO);
        }
        return storageCO;
    }

    public void addToPackage(long uid, long cid, long cardId, long cCardId, StorageCO storageCO) {
        if (storageCO.getCardData().containsKey(cardId+"")) {
            storageCO.getCardData().get(cardId+"").add(cCardId + "");
        } else {
            ArrayList<String> ccList = new ArrayList<String>();
            ccList.add(cCardId + "");
            storageCO.getCardData().put(cardId+"", ccList);
        }
        int current = getStorageCurrentSize(storageCO);
        storageDAO.updateCardData(cid, current, JSON.toJSONString(storageCO.getCardData()));
        LogWriter.info(LogWriter.PACKAGE, "add package", uid, cid, cardId, cCardId);
    }


    /**
     * 创建一个storage
     *
     * @param cid
     */
    public StorageCO create(long cid) {
        LevelCO level = levelHelper.get(1);
        StorageCO storage = new StorageCO();
        storage.setCityId(cid);
        storage.setMax(level.getStorageSize());
        storage.setCurrent(0);
        storageDAO.create(storage);
        return storage;
    }


    /**
     * 查看当前背包已经使用了多少个位置
     *
     * @return
     */
    public int getStorageCurrentSize(StorageCO storageCO) {
        int i = 0;
        for (List<String> list : storageCO.getCardData().values()) {
            i += list.size();
        }
        return i;
    }

    public void cache(StorageCO storageCO) {
        storageCache.put(storageCO);
    }

    public void updateCardData(long cid,StorageCO storage) {
        int current = getStorageCurrentSize(storage);
        storageDAO.updateCardData(cid, current, JSON.toJSONString(storage.getCardData()));
    }
}
