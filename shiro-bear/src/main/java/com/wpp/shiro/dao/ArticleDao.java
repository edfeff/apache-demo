package com.wpp.shiro.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author wpp
 */
public interface ArticleDao {
    int addArticle(JSONObject jsonObject);

    int countArticle();

    List<JSONObject> listArticle(JSONObject jsonObject);

    int updateArticle(JSONObject jsonObject);
}
