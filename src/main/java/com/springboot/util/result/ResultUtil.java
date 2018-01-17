package com.springboot.util.result;

/**
 * 返回接口公共类
 * 
 * @author BPS
 * @time 2017年11月6日上午9:49:07
 *
 */
public class ResultUtil {

	/**
	 * 返回成功处理
	 * 
	 * @param res
	 * @param msg
	 * @return
	 */
	public static Result success(Integer res, String msg) {
		Result result = new Result();
		result.setRes(res);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 返回成功处理 包含obj
	 * 
	 * @param res
	 * @param msg
	 * @param obj
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Result successWithObj(Integer res, String msg, Object obj) throws IllegalArgumentException, IllegalAccessException {
		Result result = new Result();
		result.setRes(res);
		result.setMsg(msg);
		result.setData(obj);
		//result.setData(NullUtil.checkNull(obj));
		return result;
	}

	/**
	 * 返回错误处理
	 * 
	 * @param res
	 * @param msg
	 * @return
	 */
	public static Result error(Integer res, String msg) {
		Result result = new Result();
		result.setRes(res);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 返回错误处理接口 包含obj
	 * 
	 * @param res
	 * @param msg
	 * @param obj
	 * @return
	 */
	public static Result errorWithObj(Integer res, String msg, Object obj) {
		Result result = new Result();
		result.setRes(res);
		result.setMsg(msg);
		result.setData(obj);
		return result;
	}
}
