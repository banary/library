package com.banary.mode.async;

import java.util.concurrent.*;

/**
 * @Description
 * @Author eden
 * @Date 2018/7/21 下午3:02
 */
public abstract class AsyncTask<T> {

    private volatile Executor executor;

    private static final ExecutorService DEFUALT_EXECUTOR;

    static {
        DEFUALT_EXECUTOR = new ThreadPoolExecutor(1, 1, 8, TimeUnit.HOURS, new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, "AsyncTaskDefaultWorker");

                        // 后台线程
                        t.setDaemon(true);
                        return t;
                    }
                }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if(!executor.isShutdown()){
                    try {
                        executor.getQueue().put(r);
                    }catch (InterruptedException e){
                    }
                }
            }
        });
    }

    public AsyncTask() {
        this(DEFUALT_EXECUTOR);
    }

    public AsyncTask(Executor executor) {
        this.executor = executor;
    }

    /**
     * 子类实现耗时较短的任务
     * @param params
     */
    protected void onPreExecute(Object ... params){
    }

    /**
     * 子类实现。用于实现同步任务执行结束后所需执行的操作
     * @param result
     */
    protected void onPostExecute(T result){
    }

    /**
     * 异常处理
     * @param e
     */
    protected void onExecutionError(Exception e){
        e.printStackTrace();
    }

    /**
     * 子类实现，后台执行耗时较长的任务
     * @param params
     * @return
     */
    protected abstract T doInBackground(Object... params);

    protected Future<T> dispatch(final Object... params){
        onPreExecute(params);

        Callable<T> callable = new Callable<T>() {
            @Override
            public T call() throws Exception {
                return doInBackground(params);
            }
        };

        FutureTask<T> ft = new FutureTask<T>(callable){
            @Override
            protected void done() {
                try {
                    onPostExecute(this.get());
                }catch (InterruptedException e){
                    onExecutionError(e);
                }catch (ExecutionException e){
                    onExecutionError(e);
                }
            }
        };

        executor.execute(ft);
        return ft;
    }

}




