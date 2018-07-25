package com.choucheng.dongzhibot.bean;

/**
 * Created by admin on 2018/7/17.
 */

public class ProtectBeanOrderInfo extends BaseBean<ProtectBeanOrderInfo.ProtectBeanOrderInfoData>{

    public class ProtectBeanOrderInfoData{
        public String merchant_no;
        public String link_name;
        public String link_phone;
        public String address;
        public String name;
        public String pos_no;
        public String service_mark;
        public String odd_number;
    }

//     "merchant_no": "423", //商户编号
//             "link_name": "探戈",
//             "link_phone": "13277777777",
//             "address": "高新区管委会",
//             "name": "123",
//             "pos_no": "123123",
//             "service_mark": "服务需求描述"  //服务描述
//             "odd_number": "15081923049696"   //巡检单号
}
