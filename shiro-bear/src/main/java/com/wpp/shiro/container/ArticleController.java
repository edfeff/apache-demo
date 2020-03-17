package com.wpp.shiro.container;

import com.alibaba.fastjson.JSONObject;
import com.wpp.shiro.service.ArticleService;
import com.wpp.shiro.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/listArticle")
    public JSONObject listArticle(HttpServletRequest request) {
        return articleService.listArticle(CommonUtil.request2Json(request));
    }

    @PostMapping("/addArticle")
    public JSONObject addArticle(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "content");
        return articleService.addArticle(requestJson);
    }

    @PostMapping("/updateArticle")
    public JSONObject updateArticle(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "id,content");
        return articleService.updateArticle(requestJson);
    }
}
