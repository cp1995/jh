package com.cp.dd.common.util;

import com.cp.dd.common.entity.sys.SysFilterWords;
import com.cp.dd.common.mapper.sys.SysFilterWordsMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 * @author chengp
 */
public class SensitiveWordsUtils {
    /**
     * 获取文字中包含的敏感字符
     *
     * @param sysFilterWordsMapper 铭感次数据库操作类
     * @param text             需要检查的文本
     * @param matchType        匹配类型(0-最小匹配,非0-最大匹配)
     * @return 返回敏感词列表
     * @version 1.0
     */
    public static List<String> getMatchedSensitiveWords(SysFilterWordsMapper sysFilterWordsMapper, String text, int matchType) {
        if (StringUtils.isBlank(text)){
            return new ArrayList<>();
        }

        List<SysFilterWords> sysFilterWords = sysFilterWordsMapper.selectAllByStatus(1);
        Set<String> sysFilterWordSet = new HashSet<String>();
        for (SysFilterWords sysFilterWord : sysFilterWords) {
            sysFilterWordSet.add(sysFilterWord.getWord());
        }
        Map sensitiveWordMap = initSensitiveWords(sysFilterWordSet);
        List<String> matchedSensitiveWords = new ArrayList<String>();
        for (int i = 0; i < text.length(); i++) {
            //判断是否包含敏感字符
            int length = checkSensitiveWords(sensitiveWordMap,text, i, matchType);
            //存在,加入list中
            if (length > 0) {
                if(!matchedSensitiveWords.contains(text.substring(i, i + length))){
                    matchedSensitiveWords.add(text.substring(i, i + length));
                }
                //减1的原因，是因为for会自增
                i = i + length - 1;
            }
        }

        return matchedSensitiveWords;
    }

    /**
     * 检查文字中是否包含敏感字符
     *
     * @param text       需要检查的文本
     * @param beginIndex 开始位置
     * @param matchType  匹配类型(0-最小匹配,非0-最大匹配)
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    private static int checkSensitiveWords(Map sensitiveWordMap,String text, int beginIndex, int matchType) {
        //敏感词结束标识位：用于敏感词只有1位的情况
        boolean flag = false;
        //匹配标识数默认为0
        int matchFlag = 0;
        char word = 0;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < text.length(); i++) {
            word = text.charAt(i);
            //获取指定key
            nowMap = (Map) nowMap.get(word);
            //存在，则判断是否为最后一个
            if (nowMap != null) {
                //找到相应key，匹配标识+1
                matchFlag++;
                //如果为最后一个匹配规则,结束循环，返回匹配标识数
                if ("1".equals(nowMap.get("isEnd"))) {
                    //结束标志位为true
                    flag = true;
                    //最小规则，直接返回,最大规则还需继续查找
                    if (0 == matchType) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
        if (!flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }

    public static Map initSensitiveWords(Collection<String> keyWordSet) {
        //初始化敏感词容器，减少扩容操作
        Map wordMap = new HashMap(keyWordSet.size());
        Map nowMap = null;
        Map newWorMap = null;
        for (String key : keyWordSet) {
            //初始化nowMap
            nowMap = wordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);
                Map tempMap = (Map) nowMap.get(keyChar);

                //如果存在该key，直接赋值
                if (tempMap != null) {
                    nowMap = tempMap;
                } else {     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String, String>();
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    //最后一个
                    nowMap.put("isEnd", "1");
                }
            }
        }
        return wordMap;
    }
    public static String getSensitiveString(List<String> sensitives){
        String aa = "";
        if(sensitives!=null&&sensitives.size()>0){
            aa+="不能包含敏感词:";
            for(int i=0;i<sensitives.size();i++){
                aa+=sensitives.get(i)+"、";
            }
            aa = aa.substring(0,aa.length()-1);
        }
        return aa;
    }
}
