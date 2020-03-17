package com.wpp.shiro.util;

import com.alibaba.fastjson.JSONObject;
import com.wpp.shiro.config.exception.CommonJsonException;
import com.wpp.shiro.util.constants.Constants;
import com.wpp.shiro.util.constants.ErrorEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class CommonUtil {
    public static JSONObject successJson() {
        return successJson(new JSONObject());
    }

    public static JSONObject successJson(JSONObject info) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("info", info);
        return resultJson;
    }


    public static JSONObject successPage(JSONObject requestJson, List<JSONObject> list, int totalCount) {
        int pageRow = requestJson.getIntValue("pageRow");

        int totalPage = getPageCounts(pageRow, totalCount);

        JSONObject result = successJson();
        JSONObject info = new JSONObject();
        info.put("list", list);
        info.put("totalCount", totalCount);
        info.put("totalPage", totalPage);
        result.put("info", info);
        return result;

    }

    public static JSONObject successPage(List<JSONObject> list) {
        JSONObject result = successJson();
        JSONObject info = new JSONObject();
        info.put("list", list);
        result.put("info", info);
        return result;
    }

    /**
     * 获取总页数
     *
     * @param pageRow   每页行数
     * @param itemCount 结果的总条数
     */
    private static int getPageCounts(int pageRow, int itemCount) {
        if (itemCount == 0) {
            return 1;
        }
        return itemCount % pageRow > 0 ?
                itemCount / pageRow + 1 :
                itemCount / pageRow;
    }

    /**
     * 分页查询之前的处理参数
     * 没有传pageRow参数时,默认每页10条.
     */
    public static void fillPageParam(final JSONObject paramObject) {
        fillPageParam(paramObject, 10);
    }

    public static void fillPageParam(final JSONObject paramObject, int defaultPageRow) {
        int pageNum = paramObject.getIntValue("pageNum");
        int pageRow = paramObject.getIntValue("pageRow");

        pageNum = pageNum == 0 ? 1 : pageNum;
        pageRow = pageRow == 0 ? defaultPageRow : pageRow;

        paramObject.put("offSet", (pageNum - 1) * pageRow);
        paramObject.put("pageRow", pageRow);
        paramObject.put("pageNum", pageNum);

        //删除此参数,防止前端传了这个参数,pageHelper分页插件检测到之后,拦截导致SQL错误
        paramObject.remove("pageSize");
    }

    public static JSONObject request2Json(HttpServletRequest request) {
        JSONObject requestJson = new JSONObject();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] pv = request.getParameterValues(paramName);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pv.length; i++) {
                if (pv[i].length() > 0) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(pv[i]);
                }
            }
            requestJson.put(paramName, sb.toString());
        }
        return requestJson;
    }

    public static void hasAllRequired(JSONObject jsonObject, String requiredColumns) {
        if (!StringTools.isNullOrEmpty(requiredColumns)) {
            //验证字段非空
            String[] columns = requiredColumns.split(",");
            String missCol = "";
            for (String column : columns) {
                Object val = jsonObject.get(column.trim());
                if (StringTools.isNullOrEmpty(val)) {
                    missCol += column + "  ";
                }
            }
            if (!StringTools.isNullOrEmpty(missCol)) {
                jsonObject.clear();
                jsonObject.put("code", ErrorEnum.E_90003.getErrorCode());
                jsonObject.put("msg", "缺少必填参数:" + missCol.trim());
                jsonObject.put("info", new JSONObject());
                throw new CommonJsonException(jsonObject);
            }
        }


    }

    public static JSONObject errJson(ErrorEnum errorEnum) {
        return null;
    }
}
