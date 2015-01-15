package com.studio.elephant.web.framework.time;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
/**
 * 定时任务管理器
 * @author file
 * @since 2014-12-26 上午11:58:47
 * @version 1.0
 */
public class TimingTask {
	
	private Logger logger = Logger.getLogger(TimingTask.class.getName());
	private ScheduledExecutorService  timingTaskService = null;
	//是否启动
	private boolean isStartUp = false;
	//一分钟的秒数
	private final long minToSecond = 60;
	//一小时的秒数
	private final long hourToSecond = minToSecond * 60;
	//一天的秒数
	private final long dayToSecond = 24 * hourToSecond;
	/**
	 * 启动定时任务管理器（单线程）
	 */
	public  void  startUp(){
		
		if(isStartUp){
			logger.error("定时器已启动！无需重新再启动！");
			return;
		}
		if(logger.isDebugEnabled())
			logger.debug("TimingTask----> startUp");
		
		timingTaskService = Executors.newSingleThreadScheduledExecutor();
		isStartUp = true;
	}
	
	/**
	 * 启动定时任务管理器（多线程）
	 * @param coreCount 线程数量
	 */
	public  void  startUp(int coreCount){
		
		if(isStartUp){
			logger.error("定时器已启动！无需重新再启动！");
			return;
		}
		
		if(coreCount < 1){
			throw new IllegalArgumentException("参数错误！线程数不能比1小！！！count："+coreCount);
		}
		if(logger.isDebugEnabled())
			logger.debug("TimingTask----> startUp---->count:"+coreCount);
		timingTaskService = Executors.newScheduledThreadPool(coreCount);
		isStartUp = true;
	}

	/**
	 * 停止定时任务管理器，未完成任务的会继续完成	
	 */
	public void shutdown(){
		
		if(isStartUp){
			timingTaskService.shutdown();
			isStartUp = false;
		}else{
			logger.error("定时器没有启动");
		}
	}
	
	/**
	 * 立即停止，并返回未完成任务
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Runnable> shutdownNow(){
		if(isStartUp){
			isStartUp = false;
			return timingTaskService.shutdownNow();
		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * 提交任务
	 * @param r
	 */
	public void submit(Runnable r){
		if(isStartUp)
			timingTaskService.submit(r);
	}
	
	/**
	 * 每天执行任务
	 * @param r
	 * @param hour
	 * @param min
	 * @param second
	 * @param delay  延时执行的天数
	 */
	public void everyDay(Runnable r,int hour,int min,int second,long delay){
		if(hour<0 || hour>23){
			throw new IllegalArgumentException("每天在的小时数值应该在0~23之间,数值错误！！！hour："+hour);
		}
		if(min<0 || min>59){
			throw new IllegalArgumentException("每天在的分钟数值应该在0~59之间,数值错误！！！min："+min);
		}
		if(second<0 || second>59){
			throw new IllegalArgumentException("每天在的秒数值应该在0~59之间,数值错误！！！second："+second);
		}
		Calendar calendar = Calendar.getInstance();
		int curHour = calendar.get(Calendar.HOUR_OF_DAY);
		int curMin = calendar.get(Calendar.MINUTE);
		int curSecond = calendar.get(Calendar.SECOND);
		
		long totalSecond = curHour * hourToSecond + curMin * minToSecond + curSecond;
		long _planSecond = hour* hourToSecond + min * minToSecond + second;
		
		long _delay = delay * dayToSecond;
		
		if(totalSecond> _planSecond){
			_delay += dayToSecond -(totalSecond - _planSecond);
		}else if(totalSecond<_planSecond){
			_delay += _planSecond-totalSecond;
		}
		if(logger.isDebugEnabled())
			logger.debug("延迟秒数："+_delay);
		schedule(r, _delay, dayToSecond,TimeUnit.SECONDS);
	}
	
	/**
	 * 每天执行任务
	 * @param r
	 * @param hour
	 * @param min
	 * @param delay
	 */
	public void everyDay(Runnable r,int hour,int min,long delay){
		everyDay(r, hour, min, 0, delay);
	}
	
	/**
	 * 每天执行任务
	 * @param r
	 * @param hour
	 * @param min
	 */
	public void everyDay(Runnable r,int hour,int min){
		everyDay(r, hour, min, 0);
	}
	/**
	 * 每天执行任务
	 * @param r
	 * @param hour
	 * @param delay
	 */
	public void everyDay(Runnable r,int hour,long delay){
		everyDay(r, hour, 0, delay);
	}
	/**
	 * 每天执行任务
	 * @param r
	 * @param hour
	 */
	public void everyDay(Runnable r,int hour){
		everyDay(r, hour, 0);
	}
	/**
	 * 每天执行任务
	 * @param r
	 * @param delay
	 */
	public void everyDay(Runnable r,long delay){
		everyDay(r, 0, delay);
	}
	
	/**
	 * 每天执行任务
	 * @param r
	 */
	public void everyDay(Runnable r){
		everyDay(r,0);
	}
		
	/**
	 * 每小时执行任务
	 * @param r
	 */
	public void everyHour(Runnable r){
		everyHour(r, 0,0);
	}
	/**
	 * 每小时执行任务
	 * @param r
	 * @param delay
	 */
	public void everyHour(Runnable r,long delay){
		everyHour(r, 0,delay);
	}
	/**
	 * 每小时执行任务
	 * @param r
	 * @param min
	 * @param delay
	 */
	public void everyHour(Runnable r,int min,long delay){
		everyHour(r, min, 0,delay);
	}
	/**
	 * 每小时执行任务
	 * @param r
	 * @param min
	 * @param second
	 * @param delay
	 */
	public void everyHour(Runnable r,int min,int second,long delay){
		if(min<0 || min>59){
			throw new IllegalArgumentException("每小时在的分钟数值应该在0~59之间,数值错误！！！min："+min);
		}
		if(second<0 || second>59){
			throw new IllegalArgumentException("每小时在的秒数值应该在0~59之间,数值错误！！！second："+second);
		}
		Calendar calendar = Calendar.getInstance();
		int curMin = calendar.get(Calendar.MINUTE);
		int curSecond = calendar.get(Calendar.SECOND);
		
		long totalSecond = curMin * minToSecond + curSecond;
		long _planSecond = min * minToSecond + second;
		
		long _delay = delay * hourToSecond;
		if(totalSecond> _planSecond){
			_delay += hourToSecond -(totalSecond - _planSecond);
		}else if(totalSecond<_planSecond){
			_delay += _planSecond-totalSecond;
		}
		if(logger.isDebugEnabled())
			logger.debug("延迟秒数："+_delay);
		schedule(r, _delay, hourToSecond,TimeUnit.SECONDS);
	}
	
	/**
	 * 每分钟执行任务
	 * @param r
	 */
	public void everyMinute(Runnable r){
		everyMinute(r, 0,0);
	}
	/**
	 * 每分钟执行任务
	 * @param r
	 * @param delay
	 */
	public void everyMinute(Runnable r,long delay){
		everyMinute(r, 0,delay);
	}
	/**
	 * 每分钟执行任务
	 * @param r
	 * @param second
	 * @param delay
	 */
	public void  everyMinute(Runnable r,int second,long delay){
		if(second<0 || second>59){
			throw new IllegalArgumentException("每分钟在的秒数值应该在0~59之间,数值错误！！！second："+second);
		}
		Calendar calendar = Calendar.getInstance();
		int curSecond = calendar.get(Calendar.SECOND);
		long _delay = delay * minToSecond;
		if(curSecond> second){
			_delay += minToSecond -(curSecond - second);
		}else if(curSecond<second){
			_delay += second-curSecond;
		}
		if(logger.isDebugEnabled())
			logger.debug("延迟秒数："+_delay);
		schedule(r, _delay, minToSecond,TimeUnit.SECONDS);
	}
	
	/**
	 * 每秒执行任务
	 * @param r 任务
	 */
	public void everySecond(Runnable r){
		everySecond(r,0);
	}
	/**
	 * 每秒执行任务
	 * @param r 任务
	 * @param delay 延时秒数
	 */
	public void everySecond(Runnable r,long delay){
		schedule(r, delay, 1,TimeUnit.SECONDS);
	}
	/**
	 * 周期性执行任务
	 * @param r 任务
	 * @param initialDelay 延迟数量
	 * @param period 周期数值
	 * @param unit  时间单位
	 * @return
	 */
	public  ScheduledFuture<?>  schedule(Runnable r,long initialDelay,long period,TimeUnit unit){
		if(isStartUp)
			return timingTaskService.scheduleAtFixedRate(r, initialDelay, period, unit);
		return null;
	}
	/**
	 * 周期性执行任务
	 * @param r 任务
	 * @param initialDelay 延迟秒数
	 * @param period     周期的数量（时间单位是秒）
	 */
	public ScheduledFuture<?> schedule(Runnable r,long initialDelay,long period){
		return schedule(r, initialDelay, period, TimeUnit.SECONDS);
	}
	
	/**
	 * 延时执行任务
	 * @param r 任务
	 * @param delay 延时数量
	 * @param unit  时间单位
	 */
	public void delay(Runnable r,long delay,TimeUnit unit){
		if(isStartUp)
			timingTaskService.schedule(r, delay, unit);
	}
	/**
	 * 延时执行任务
	 * @param r  任务
	 * @param delay 延迟秒数
	 */
	public void delay(Runnable r,long delay){
		delay(r, delay, TimeUnit.SECONDS);
	}
	
	
	private static final class TimingTaskHolder{
		public static final TimingTask timingTask = new TimingTask();
	}
	
	TimingTask(){
		
	}
	
	static TimingTask getInstance(){
		return TimingTaskHolder.timingTask;
	}
	
	
	
	
	
	
	
	

}
