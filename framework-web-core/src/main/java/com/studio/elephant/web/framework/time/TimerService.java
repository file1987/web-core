package com.studio.elephant.web.framework.time;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.studio.elephant.web.framework.AbstractService;
import com.studio.elephant.web.framework.ITask;
import com.studio.elephant.web.framework.ServiceStatus;
/**
 * 定时任务服务（属于基础服务，其他服务可以通过继承或组合方式获得这个服务的特性）<br/>
 * @author file
 * @since 2015-1-13 下午3:28:29
 * @version 1.0
 */
public class TimerService extends AbstractService {
	
	
	private static final Logger logger = Logger.getLogger(TimerService.class);
	/**
	 * 定时任务
	 */
	private TimingTask timingTask;
	
	
	@Override
	protected void startuping() {
		if(logger.isDebugEnabled())
			logger.debug("定时任务启动");
		timingTask = new TimingTask();
		timingTask.startUp();
	}

	@Override
	protected void shutdowning() {
		if(logger.isDebugEnabled())
			logger.debug("定时任务停止");
		timingTask.shutdown();
	}
	
	/**
	 * 每天执行任务
	 * @param task 任务
	 * @param hour 小时数执行（0-23）
	 */
	public void sumbitEveryDayTask(final ITask task,final int hour){
		if(task==null){
			logger.error("定时任务不能为Null",new NullPointerException());
			return;
		}
		if(getStatus()!=ServiceStatus.Running){
			logger.error("TimerService 没在运行哦~~~ 不能添加定时任务哦");
			return;
		}
				
		timingTask.everyDay(new Runnable() {
			
			public void run() {
				task.execute();
			}
		}, hour, 0, 0, 0);
	}
	/**
	 * 延迟执行的任务
	 * @param task 任务
	 * @param delay 延迟数量
	 * @param unit  时间单位
	 */
	public void sumbitDelayTask(final ITask task,final long delay,final TimeUnit unit){
		if(task==null){
			logger.error("定时任务不能为Null",new NullPointerException());
			return;
		}
		if(getStatus()!=ServiceStatus.Running){
			logger.error("TimerService 没在运行哦~~~ 不能添加定时任务哦");
			return;
		}
		
		timingTask.delay(new Runnable() {
			
			public void run() {
				task.execute();
			}
		}, delay,unit);
	}
	/**
	 * 每小时执行的任务
	 * @param task 任务
	 * @param min   分钟数执行（0-59）
	 */
	public void sumbitEveryHourTask(final ITask task,final int min){
		if(task==null){
			logger.error("定时任务不能为Null",new NullPointerException());
			return;
		}
		if(getStatus()!=ServiceStatus.Running){
			logger.error("TimerService 没在运行哦~~~ 不能添加定时任务哦");
			return;
		}
		
		timingTask.everyHour(new Runnable() {
			
			public void run() {
				task.execute();
			}
		}, min, 0, 0);
	}
	/**
	 * 每分钟任务
	 * @param task 任务
	 * @param second 秒数执行（0-59）
	 */
	public void sumbitEveryMinuteTask(final ITask task,final int second){
		if(task==null){
			logger.error("定时任务不能为Null",new NullPointerException());
			return;
		}
		if(getStatus()!=ServiceStatus.Running){
			logger.error("TimerService 没在运行哦~~~ 不能添加定时任务哦");
			return;
		}
		timingTask.everyMinute(new Runnable() {
			
			public void run() {
				task.execute();
			}
		}, second, 0);
	}
	/**
	 * 每秒任务
	 * @param task 任务
	 */
	public void sumbitEverySecondTask(final ITask task){
		if(task==null){
			logger.error("定时任务不能为Null",new NullPointerException());
			return;
		}
		if(getStatus()!=ServiceStatus.Running){
			logger.error("TimerService 没在运行哦~~~ 不能添加定时任务哦");
			return;
		}
		timingTask.everySecond(new Runnable() {
			
			public void run() {
				task.execute();
			}
		});
	}
	/**
	 * 若被继承，必须重写该方法
	 */
	public String getCode() {
		return "TimerService";
	}
	
	
	
	
}
