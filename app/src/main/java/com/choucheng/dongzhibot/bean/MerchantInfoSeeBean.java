package com.choucheng.dongzhibot.bean;

import java.util.ArrayList;

/**
 * Created by admin on 2018/6/12.
 */

public class MerchantInfoSeeBean extends BaseBean< ArrayList<MerchantInfoSeeBean.MerchantInfoSeeData>>{
//    {"status":{"msg":"","code":"0"},"data":[{"id":"1","is_set":"0","add_time":"1527440696","name":"123"}]}
    public class MerchantInfoSeeData{
        public String id;
        public String is_set;
        public String add_time;
        public String name;
    }
}
