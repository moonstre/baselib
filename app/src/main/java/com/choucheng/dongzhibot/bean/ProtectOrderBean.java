package com.choucheng.dongzhibot.bean;

import java.util.ArrayList;

/**
 * Created by admin on 2018/6/13.
 */

public class ProtectOrderBean extends BaseBean<ProtectOrderBean.ProtectOrder>{
    public  class ProtectOrder{

        public ArrayList<ProtectOrderItem> yunwei_lists;
        public ProtectOrderPaging paging;
        public  class ProtectOrderItem{
            public String xunjianid;
            public String merchant_id;
            public String yunwei_id;
            public String yunwei_status;
            public String yunwei_over;
            public String odd_number;
            public String d_id;
            public String start_time;
            public String end_time;
            public String is_question;
            public String x_status;
            public String status;
            public MerchantInfo merchant_info;
            public String yunwei_info;


            public class MerchantInfo{
                public String name;
                public String address;
            }

//  "xunjianid": "1",        巡检的id
//                "merchant_id": "1",   商户id
//                "yunwei_id": "114",     维护人员的id
//                "yunwei_status": "0",  0未审核1审核中 2审核通过 3审核未通过
//                "yunwei_over": "0",  0未接受1已接受2拒绝接受3维护中4完成
//                "odd_number": "15081923049696",   巡检单号
//                "d_id": "1",    设备id
//                "start_time": "1439999868",
//                        "end_time": "1440000463",
//                        "is_question": "1",  1有问题 2没问题
//                "x_status": "2",   1巡检中 2 巡检结束
//                "status": "1", 1有效2 无效3删除
//                "merchant_info": {
//                "address": "天府大道",  商户地址
//                "name": "家乐福超市"   商户名字
//            }
        }
        public class ProtectOrderPaging{
            public String page;
            public String totalcount;
            public String numberofpage;
//             "page": "1",
//                     "totalcount": "1",
//                     "numberofpage": "10"
        }
    }
}
