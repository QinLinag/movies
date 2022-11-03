package com.cqupt.movies.common.map;

import java.util.HashMap;
import java.util.Map;

public class MovieTagsMap {
    public static Map<Integer, String> map;
    static {
        map=new HashMap<>();
        map.put(1, "动作");
        map.put(2, "喜剧");
        map.put(3, "奇幻");
        map.put(4,"犯罪" );
        map.put(5, "同性");
        map.put(6, "爱情");
        map.put(7, "情节");
        map.put(8,"冒险");
        map.put(9, "音乐");
        map.put(10, "科幻");
        map.put(11, "恐怖");
        map.put(12, "家庭");
        map.put(13,"历史");
        map.put(14,"惊悚");
        map.put(15, "悬疑");
        map.put(16, "战争");
        map.put(17,"古装");
    }
}
