package com.nt.common.utils.resp;

import com.nt.common.core.domain.resp.DataResp;
import com.nt.common.core.domain.resp.Resp;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局前端数据的返回工具（基于Mybatis Mapper反馈处理）
 * 此类为常用数据库返回结果的再处理
 * @see Resp
 * @see DataResp
 *
 * @author arvin
 * @date 2022/12/16
 */
@Slf4j
public class RespUtils {

	/**
	 * 查询数据
	 * @param data 数据内容
	 * @param <T>  数据类型
	 * @return 全局返回体
	 */
	public static <T> Resp queryData(T data) {
		if (data == null) {
			log.error("数据库资源不存在");
			return Resp.notFound();
		} else {
			return DataResp.succeed(data);
		}
	}

	/**
	 * 添加数据
	 * @param databaseResponses 数据库返回体
	 * @return 全局返回体
	 */
	public static Resp createData(Integer... databaseResponses) {
		boolean isIntegrity = isIntegrity(databaseResponses);

		if (!isIntegrity) {
			log.error("数据库操作错误");
			return Resp.serverError();
		}
		return Resp.created();
	}

	/**
	 * 编辑（更新）数据
	 * @param databaseResponses 数据库返回体
	 * @return 全局返回体
	 */
	public static Resp updateData(Integer... databaseResponses) {
		boolean isIntegrity = isIntegrity(databaseResponses);

		if (!isIntegrity) {
			log.error("数据库操作错误");
			return Resp.serverError();
		}
		return Resp.updated();
	}

	/**
	 * 删除数据
	 * @param databaseResponses 数据库返回体
	 * @return 全局返回体
	 */
	public static Resp deleteData(Integer... databaseResponses) {
		boolean isIntegrity = isIntegrity(databaseResponses);

		if (!isIntegrity) {
			log.error("数据库操作错误");
			return Resp.serverError();
		}
		return Resp.deleted();
	}

	/**
	 * 检查数据完整性
	 * @param databaseResponses 数据库返回体
	 * @return 数据完整性状态
	 */
	private static boolean isIntegrity(Integer... databaseResponses) {
		boolean isIntegrity = true;

		for (Integer dataIntegrity : databaseResponses) {
			isIntegrity &= (dataIntegrity != 0);
		}
		return isIntegrity;
	}

}
