package com.cn.dsyg.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.FeatureDto;
import com.cn.dsyg.service.Dict01Service;

/**
 * @name Dict01ServiceImpl.java
 * @author Frank
 * @time 2014-12-16上午1:32:09
 * @version 1.0
 */
public class Dict01ServiceImpl implements Dict01Service {
	
	private Dict01Dao dict01Dao;
	
	@Override
	public List<FeatureDto> queryFeatureByFieldcode(String fieldcode,
			String lang) {
		List<FeatureDto> resultList = new ArrayList<FeatureDto>();
		//正常只有电线和套管才有特征，这里就按照fieldcode查询
		for(int i = 1; i <= 6; i++) {
			List<Dict01Dto> dictList = dict01Dao.queryDict01ByFieldcode(fieldcode + "_item0" + i, lang);
			String name = "";
			//ID名
			String codename = "code" + fieldcode + "_item0" + i;
			FeatureDto feature = new FeatureDto();
			feature.setCodename(codename);
			if(dictList != null && dictList.size() > 0) {
				//特征标题
				name = dictList.get(0).getMean();
				feature.setName(name);
				feature.setDictList(dictList);
				resultList.add(feature);
			}
		}
		return resultList;
	}
	
	@Override
	public Page queryDict01CountByPage(String fieldcode, String fieldname, String lang,
			Page page) {
		fieldname = StringUtil.replaceDatabaseKeyword_mysql(fieldname);
		//查询总记录数
		int totalCount = dict01Dao.queryDict01CountByPage(fieldcode, fieldname, lang);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<Dict01Dto> list = dict01Dao.queryDict01ByPage(fieldcode, fieldname, lang,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public Dict01Dto queryDict01ByID(String id) {
		return dict01Dao.queryDict01ByID(id);
	}

	@Override
	public List<Dict01Dto> queryDict01ByFieldcode(String fieldcode, String lang) {
		return dict01Dao.queryDict01ByFieldcode(fieldcode, lang);
	}
	
	@Override
	public List<Dict01Dto> queryGoodsNoOther(String lang) {
		List<Dict01Dto> list = dict01Dao.queryDict01ByFieldcode(Constants.DICT_GOODS_TYPE, lang);
		if(list != null && list.size() > 0) {
			for(int i = 0; i < list.size(); i++) {
				Dict01Dto dict = list.get(i);
				//列表中去掉
				if("其他".equals(dict.getFieldname())) {
					list.remove(i);
				}
			}
		}
		return list;
	}

	@Override
	public void deleteDict01(String id) {
		dict01Dao.deleteDict01(id);
	}

	@Override
	public void insertDict01(Dict01Dto dict01) {
		dict01Dao.insertDict01(dict01);
	}

	@Override
	public void updateDict01(Dict01Dto dict01) {
		dict01Dao.updateDict01(dict01);
	}

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}
}
