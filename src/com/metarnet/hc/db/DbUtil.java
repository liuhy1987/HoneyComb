/**
 * 
 */
package com.metarnet.hc.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.metarnet.hc.model.AlarmModel;
import com.metarnet.hc.model.AlmMsg;
import com.metarnet.hc.util.Constant;
import com.metarnet.hc.util.DateUtil;
import com.metarnet.hc.util.LogUtil;
import com.metarnet.hc.util.ObjectUtil;

/**
 * @author liuhy
 */
public class DbUtil {
	
	private SqlSessionFactory sqlSessionFactory = null;
	
	/***
	 * init database params
	 * @throws Exception 
	 */
	public DbUtil() throws Exception{
		InputStream input = null;
		try {
			input = new FileInputStream("conf/mybatisConfig.xml");
			if(sqlSessionFactory==null)
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
		} catch (IOException e) {
			throw new Exception("init db fail.");
		}
		
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				clearAlarmCache();
			}
		}, 0, Constant.FLICKPERIOD, TimeUnit.HOURS);
	}
	
	private SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
	private SqlSession getSqlSession(boolean autocommit) {
		return sqlSessionFactory.openSession(autocommit);
	}

	private void close(SqlSession sqlSession) {
		if (sqlSession != null)
			sqlSession.close();
	}
	
	public List<AlarmModel> queryCurrAlarms(String domain) {
		SqlSession sqlSession = null;
		try {
			LogUtil.getStoreLogger().info("query current alarm:"+domain);
			sqlSession = getSqlSession();
			QueryDAO qd = sqlSession.getMapper(QueryDAO.class);
			return qd.getCurrentAlarmsBySys(domain);
		} catch (Exception ex) {
			LogUtil.getStoreLogger().error("queryCurrAlarms fail", ex);
		} finally {
			close(sqlSession);
		}

		return new ArrayList<AlarmModel>();
	}

	/**
	 * need return store status
	 * @param data
	 * @return
	 */
	public DbStatus storeAlarms(AlmMsg data) {
		SqlSession sqlSession = null;
		DbStatus ret = new DbStatus();
		try {
			sqlSession = getSqlSession(false);
			AlarmDAO almdao = sqlSession.getMapper(AlarmDAO.class);
			
			List<AlarmModel> alms = data.getAlms();
			for(AlarmModel alm : alms){
				if(alm.getStatus().equals("insert")){
					// process flick alarm
					doFlickAnalysis(alm);
					//new alarm 
					int num = almdao.insertAlarm(ObjectUtil.alarmModel2Map(alm));
					almdao.insertAlarmCache(ObjectUtil.alarmModel2Map(alm));
					ret.setSum("insert", ret.getSum("insert")+num);
					LogUtil.getStoreLogger().debug("insert alarm "+alm.getAlarmid());
				}else if(alm.getStatus().equals("delete")){
					//clear alarm
					int num = almdao.updateClearAlarm(ObjectUtil.alarmModel2Map(alm));
					ret.setSum("delete", ret.getSum("delete")+num);
					LogUtil.getStoreLogger().debug("update clear alarm "+alm.getAlarmid());
				}
			}
			sqlSession.commit();
		} catch (Exception ex) {
			sqlSession.rollback();
			ret.setException(true);
			LogUtil.getStoreLogger().error("storeAlarms fail", ex);
		} finally {
			close(sqlSession);
		}
		return ret;
	}
	
	private void doFlickAnalysis(AlarmModel alm) {
		SqlSession sqlSession = null;
		try {
			sqlSession = getSqlSession();
			QueryDAO qd = sqlSession.getMapper(QueryDAO.class);
			
			Map<String, String> param = ObjectUtil.alarmModel2Map(alm);
			param.put("flicktime", DateUtil.getOffSetDate(alm.getAlarmnetime(), Constant.FLICKPERIOD));
			List<AlarmModel> alms = qd.getFlickAlarms(param);
			int flashtimes = 0;
			if(alms.size()>0){
				if(alms.get(0).getFlashtimes()!=null && !alms.get(0).getFlashtimes().equals("")){
					flashtimes = Integer.valueOf(alms.get(0).getFlashtimes()) + 1;
				}
			}
			alm.setFlashtimes(String.valueOf(flashtimes));
			LogUtil.getStoreLogger().info("set flashtime " + alm.getAlarmid());
		} catch (Exception ex) {
			LogUtil.getStoreLogger().error("doFlickAnalysis fail", ex);
		} finally {
			close(sqlSession);
		}
		
	}

	public void clearAlarms() {
		SqlSession sqlSession = null;
		try {
			sqlSession = getSqlSession(false);
			AlarmDAO almdao = sqlSession.getMapper(AlarmDAO.class);
			QueryDAO qd = sqlSession.getMapper(QueryDAO.class);
			
			//TODO need batch operation
			List<AlarmModel> clearalms = qd.queryClearAlarms();
			for(AlarmModel am : clearalms){
				// insert to hisalarm table
				almdao.insertHisAlarm(ObjectUtil.alarmModel2Map(am));
				// delete alarm from current alarm table
				almdao.deleteAlarm(ObjectUtil.alarmModel2Map(am));
				LogUtil.getStoreLogger().info("clear alarm "+am.getAlarmid());
			}
			
			sqlSession.commit();
		} catch (Exception ex) {
			sqlSession.rollback();
			LogUtil.getStoreLogger().error("clearAlarms fail", ex);
		} finally {
			close(sqlSession);
		}
	}
	
	private void clearAlarmCache() {
		SqlSession sqlSession = null;
		try {
			sqlSession = getSqlSession(false);
			AlarmDAO almdao = sqlSession.getMapper(AlarmDAO.class);
			Map<String, String> param = new HashMap<String, String>();
			String datestr = DateUtil.getOffSetDate(new Date(), Constant.FLICKPERIOD);
			param.put("starttime", datestr);
			int num = almdao.deleteAlarmCacheByTime(param);
			LogUtil.getStoreLogger().debug("clear "+num+" alarm cache before " + datestr);
			sqlSession.commit();
		} catch (Exception ex) {
			sqlSession.rollback();
			LogUtil.getStoreLogger().error("clearAlarmCache fail", ex);
		} finally {
			close(sqlSession);
		}
	}
	
}
