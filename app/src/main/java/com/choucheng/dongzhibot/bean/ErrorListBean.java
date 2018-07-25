package com.choucheng.dongzhibot.bean;

import java.util.ArrayList;

/**
 * Created by admin on 2018/7/3.
 */

public class ErrorListBean extends BaseBean<ArrayList<ErrorListBean.ErrorListItem>>{

    public class ErrorListItem{
        public String id;
        public String mark;
        public String type_id;
        public String create_time;
        public String status;
//          "id": "1",
//                  "mark": "ATM机器上标签是否有滑落",
//                  "type_id": "1",
//                  "create_time": "1494967295",
//                  "status": "1"1
    }
}
