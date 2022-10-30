package com.cqupt.movies.common.enume;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum MovieTagsEnum {
    ACTIVE(1,"动作"),
    COMEDY(2,"喜剧"),
    IMAGIC(3,"奇幻"),
    COMMIT(4,"犯罪"),
    SAMEGENDER(5,"同性"),
    LOVE(6,"爱情"),
    PLOT(7,"剧情"),
    RISK(8,"冒险"),
    MUSIC(9,"音乐"),
    COMIC(10,"动漫"),
    SCIENCE(11,"科幻"),
    TERRIBLE(12,"恐怖"),
    FAMILY(13,"家庭"),
    HISTORY(14,"历史"),
    PANIC(15,"惊悚"),
    SUSPENSE(16,"悬疑"),
    WAR(17,"战争"),
    ANCIENT(18,"古装")
    ;


    private Integer code;
    private String tag;


    //用于映射
    public static Map<Integer,String> getMap(){
        Map<Integer,String> map=new HashMap<>();
        map.put(ACTIVE.code, ACTIVE.tag);
        map.put(COMEDY.code, COMEDY.tag);
        map.put(IMAGIC.code, IMAGIC.tag);
        map.put(COMMIT.code, COMMIT.tag);
        map.put(SAMEGENDER.code, SAMEGENDER.tag);
        map.put(LOVE.code, LOVE.tag);
        map.put(PLOT.code, PLOT.tag);
        map.put(RISK.code, RISK.tag);
        map.put(COMIC.code, COMIC.tag);
        map.put(SCIENCE.code, SCIENCE.tag);
        map.put(TERRIBLE.code, TERRIBLE.tag);
        map.put(FAMILY.code, FAMILY.tag);
        map.put(HISTORY.code, HISTORY.tag);
        map.put(PANIC.code, PANIC.tag);
        map.put(SUSPENSE.code, SUSPENSE.tag);
        map.put(WAR.code, WAR.tag);
        map.put(ANCIENT.code, ANCIENT.tag);
        return map;
    }


    public Integer getCode() {
        return code;
    }

    public String getTag() {
        return tag;
    }



    MovieTagsEnum(Integer code,String tag){

    }
}
