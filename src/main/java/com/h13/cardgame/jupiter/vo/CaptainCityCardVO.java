package com.h13.cardgame.jupiter.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-11
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class CaptainCityCardVO {
    private long id;
    private long cardId;
    private String name;
    private String icon;
    private int level;
    private int levelExp;
    private int title;
    private int titleExp;
    private SkillVO skill;

    public SkillVO getSkill() {
        return skill;
    }

    public void setSkill(SkillVO skill) {
        this.skill = skill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelExp() {
        return levelExp;
    }

    public void setLevelExp(int levelExp) {
        this.levelExp = levelExp;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getTitleExp() {
        return titleExp;
    }

    public void setTitleExp(int titleExp) {
        this.titleExp = titleExp;
    }

    @Override
    public String toString() {
        return "GeneralCityCardVO{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
