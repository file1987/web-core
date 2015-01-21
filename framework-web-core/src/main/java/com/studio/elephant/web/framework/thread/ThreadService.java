package com.studio.elephant.web.framework.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import com.studio.elephant.web.framework.AbstractService;
import com.studio.elephant.web.framework.ITask;
import com.studio.elephant.web.framework.ServiceStatus;
/**
 * 多线程服务（属于基础服务，其他服务可以通过继承或组合方式获得这个服务的特性）<br/>
 * @author file
 * @since 2015-1-13 下午4:00:32
 * @version 1.0
 */
public class ThreadService extends AbstractService {
	
	private static final Logger logger = Logger.getLogger(ThreadService.class);
	
	private ExecutorService executorService;
	private int coreSize = 1;
	
	public ThreadService(int coreSize) {
		super();
		this.coreSize = coreSize;
	}
	
	public ThreadService(){
	}

	
	
	@Override
	protected void startuping() {
		executorService = Executors.newFixedThreadPool(coreSize);
	}

	@Override
	protected void shutdowning() {
		executorService.shutdown();
	}
	/**
	 * 执行任务
	 * @param task 任务
	 */
	public void sumbit(final ITask task){
		if(task==null){
			logger.error("任务不能为Null",new NullPointerException());
			return;
		}
		if(getStatus()!=ServiceStatus.Running){
			logger.error("ThreadService 没在运行哦~~~ 不能添加任务哦");
			return;
		}
		executorService.submit(new Runnable() {
			
			public void run() {
			  task.execute();					
			}
		});
	}
}
