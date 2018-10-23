package com.banary.mode.async;

/**
 * @Description
 * @Author eden
 * @Date 2018/7/21 下午4:27
 */
public class DemoAsyncTask extends AsyncTask<String> {

    @Override
    protected String doInBackground(Object... params) {

        String message = (String) params[0];
        int sequence = (Integer) params[1];
        System.out.println("doInBackground" + message);
        return "message " + message + ":" + message;
    }
}
