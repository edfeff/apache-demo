package com.wpp.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wpp.shiro.dao.ArticleDao;
import com.wpp.shiro.service.ArticleService;
import com.wpp.shiro.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addArticle(JSONObject jsonObject) {
        articleDao.addArticle(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject listArticle(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = articleDao.countArticle();
        List<JSONObject> list = articleDao.listArticle(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateArticle(JSONObject jsonObject) {
        articleDao.updateArticle(jsonObject);
        return CommonUtil.successJson();
    }
}
