package com.h13.cardgame.jupiter.helper;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.cache.service.StorageCache;
import com.h13.cardgame.jupiter.CardType;
import com.h13.cardgame.jupiter.dao.StorageDAO;
import com.h13.cardgame.jupiter.exceptions.CaptainStorageIsFullException;
import com.h13.cardgame.jupiter.exceptions.EquipmentStorageIsFullException;
import com.h13.cardgame.jupiter.exceptions.SquadStorageIsFullException;
import com.h13.cardgame.jupiter.utils.LogWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-21
 * Time: 上午12:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class StorageHelper {
    private static Log LOG = LogFactory.getLog(StorageHelper.class);

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
        LOG.debug("loaded storage. " + storageCO);
        return storageCO;
    }

    /**
     * 添加到小队卡的仓库中
     *
     * @param uid
     * @param cid
     * @param cardId
     * @param cCardId
     * @param storageCO
     */
    public void addToSquadPackage(long uid, long cid, long cardId, long cCardId, StorageCO storageCO) {
        if (storageCO.getSCardData().containsKey(cardId + "")) {
            storageCO.getSCardData().get(cardId + "").add(cCardId + "");
        } else {
            ArrayList<String> ccList = new ArrayList<String>();
            ccList.add(cCardId + "");
            storageCO.getSCardData().put(cardId + "", ccList);
        }
        int current = generateStorageCurrentSizeFromData(storageCO, CardType.SQUAD);
        storageDAO.updateSCardData(cid, current, JSON.toJSONString(storageCO.getSCardData()));
        LogWriter.info(LogWriter.PACKAGE, "add squad package", uid, cid, cardId, cCardId);
    }

    /**
     * 添加到队长城市卡的仓库中
     *
     * @param uid
     * @param cid
     * @param cardId
     * @param captainCityCardId
     * @param storageCO
     */
    public void addToCaptainPackage(long uid, long cid, long cardId, long captainCityCardId, StorageCO storageCO) {
        if (storageCO.getCaptainCardData().containsKey(cardId + "")) {
            storageCO.getCaptainCardData().get(cardId + "").add(captainCityCardId + "");
        } else {
            ArrayList<String> ccList = new ArrayList<String>();
            ccList.add(captainCityCardId + "");
            storageCO.getCaptainCardData().put(cardId + "", ccList);
        }
        int current = generateStorageCurrentSizeFromData(storageCO, CardType.CAPTAIN);
        storageDAO.updateCaptainCardData(cid, current, JSON.toJSONString(storageCO.getCaptainCardData()));
        LogWriter.info(LogWriter.PACKAGE, "add captain package", uid, cid, cardId, captainCityCardId);
    }

    /**
     * 查看装备仓库是不是满了
     *
     * @param storage
     * @return
     */
    public boolean isEquipmentStorageFull(StorageCO storage) {
        return storage.getECurrent() >= storage.getEMax() ? true : false;
    }

    /**
     * 查看装备仓库是不是满了
     *
     * @param storage
     * @return
     */
    public boolean isCaptainStorageFull(StorageCO storage) {
        return storage.getCaptainCurrent() >= storage.getCaptainMax() ? true : false;
    }

    /**
     * 小队卡牌仓库是不是满了
     *
     * @param storage
     * @return
     */
    public boolean isSquadStorageFull(StorageCO storage) {
        return storage.getSCurrent() >= storage.getSMax() ? true : false;
    }

    public void addToEquipmentPackage(long uid, long cid, long cardId, StorageCO storageCO) {

        if (storageCO.getECardData().containsKey(cardId + "")) {
            int sum = new Integer(storageCO.getECardData().get(cardId + ""));
            sum++;
            storageCO.getECardData().put(cardId + "", sum + "");
        } else {
            storageCO.getECardData().put(cardId + "", "1");
        }
        int current = generateStorageCurrentSizeFromData(storageCO, CardType.EQUIPMENT);
        storageDAO.updateECardData(cid, current, JSON.toJSONString(storageCO.getECardData()));
        LogWriter.info(LogWriter.PACKAGE, "add equipment package", uid, cid, cardId);
    }


    /**
     * 创建一个storage
     *
     * @param cid
     */
    public StorageCO create(long cid) {
        CityLevelCO level = levelHelper.get(1);
        StorageCO storage = new StorageCO();
        storage.setCityId(cid);
        storage.setSMax(level.getSStorageSize());
        storage.setSCurrent(0);
        storage.setEMax(level.getEStorageSize());
        storage.setECurrent(0);
        storage.setCaptainMax(level.getCaptainStorageSize());
        storage.setCaptainCurrent(0);
        storageDAO.create(storage);
        return storage;
    }


    /**
     * 查看小队卡牌仓库信息
     *
     * @param storageCO
     * @return
     */
    public int getSquadStorageCurrentSize(StorageCO storageCO) {
        return storageCO.getSCurrent();
    }

    /**
     * 查看装备卡牌仓库信息
     *
     * @param storageCO
     * @return
     */
    public int getEquipmentStorageCurrentSize(StorageCO storageCO) {
        return storageCO.getECurrent();
    }


    /**
     * 查看装备队长城市卡仓库信息
     *
     * @param storageCO
     * @return
     */
    public int getCaptainStorageCurrentSize(StorageCO storageCO) {
        return storageCO.getCaptainCurrent();
    }

    public void cache(StorageCO storageCO) {
        storageCache.put(storageCO);
    }

    /**
     * 更新数据库中小队卡牌仓库信息
     *
     * @param cid
     * @param storage
     */
    public void updateSquadCardData(long cid, StorageCO storage) {
        int current = getSquadStorageCurrentSize(storage);
        storageDAO.updateSCardData(cid, current, JSON.toJSONString(storage.getSCardData()));
    }

    /**
     * 更新数据库中装备卡牌仓库信息
     *
     * @param cid
     * @param storageCO
     */
    public void updateEquipmentCardData(long cid, StorageCO storageCO) {
        int current = getEquipmentStorageCurrentSize(storageCO);
        storageDAO.updateECardData(cid, current, JSON.toJSONString(storageCO.getECardData()));
    }

    /**
     * 通过小队仓库中的数据生成卡牌中已经有数量（current）
     *
     * @return
     */
    private int generateStorageCurrentSizeFromData(StorageCO storageCO, CardType cardType) {
        switch (cardType) {
            case SQUAD:
                Map<String, List<String>> dataMap = storageCO.getSCardData();
                int i = 0;
                for (String key : dataMap.keySet()) {
                    i += dataMap.get(key).size();
                }
                return i;
            case EQUIPMENT:
                Map<String, String> dataMap2 = storageCO.getECardData();
                int i2 = 0;
                for (String key2 : dataMap2.keySet()) {
                    i2 += new Integer(dataMap2.get(key2));
                }
                return i2;
            case CAPTAIN:
                Map<String, List<String>> dataMap3 = storageCO.getCaptainCardData();
                int i3 = 0;
                for (String key3 : dataMap3.keySet()) {
                    i3 += new Integer(dataMap3.get(key3).size());
                }
                return i3;
        }
        return 0;
    }

    public void checkAllStorageIsFull(StorageCO storage) throws EquipmentStorageIsFullException, SquadStorageIsFullException, CaptainStorageIsFullException {
        if (isEquipmentStorageFull(storage)) {
            LOG.debug("equipment storage is full . cityId = " + storage.getCityId());
            throw new EquipmentStorageIsFullException("cid=" + storage.getCityId() + " equipment storage is full.");
        }
        if (isCaptainStorageFull(storage)) {
            LOG.debug("captain storage is full . cityId = " + storage.getCityId());
            throw new CaptainStorageIsFullException("cid=" + storage.getCityId() + " captain storage is full.");
        }
        if (isSquadStorageFull(storage)) {
            LOG.debug("squad storage is full . cityId = " + storage.getCityId());
            throw new SquadStorageIsFullException("cid=" + storage.getCityId() + " squad storage is full.");
        }
        LOG.debug("storage is not full . cityId = " + storage.getCityId());
    }


    /**
     * 检查captain仓库中是否有对应的城市卡
     *
     * @param storage
     * @return
     */
    public boolean haveTheCaptainCard(StorageCO storage, CaptainCityCardCO captainCityCard) {
        return storage.getCaptainCardData().get(captainCityCard.getCardId() + "").contains(captainCityCard.getId() + "");
    }

    /**
     * 从小队城市卡仓库中去掉这张卡
     *
     * @param storageCO
     * @param captainCityCardCO
     */
    public void removeCaptainCityCard(StorageCO storageCO, CaptainCityCardCO captainCityCardCO) {
        Map<String, List<String>> data = storageCO.getCaptainCardData();
        List<String> list = data.get(captainCityCardCO.getCardId() + "");
        list.remove(captainCityCardCO.getId() + "");
        LOG.debug("storage remove card. cityId=" + storageCO.getCityId() + " captainCityCardCO=" + captainCityCardCO.getId());
    }

    /**
     * 添加一张军备牌，存在军备仓库中
     *
     * @param city
     * @param card
     */
    public void addEquipmentCard(CityCO city, CardCO card) {
        // add to package
        StorageCO storageCO = getByCid(city.getId());
        addToEquipmentPackage(city.getUserId(), city.getId(), card.getId(), storageCO);
        cache(storageCO);
    }

}
