package com.github.cchao.touchnews.javaBean;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class UserID {

        /**
         * errNum : 0
         * retMsg : success
         * retData : {"sex":"M","birthday":"1987-04-20","address":"湖北省孝感市汉川市"}
         */

        private int errNum;
        private String retMsg;
        /**
         * sex : M
         * birthday : 1987-04-20
         * address : 湖北省孝感市汉川市
         */

        private RetDataBean retData;

        public int getErrNum ( ) { return errNum;}

        public void setErrNum ( int errNum ) { this.errNum = errNum;}

        public String getRetMsg ( ) { return retMsg;}

        public void setRetMsg ( String retMsg ) { this.retMsg = retMsg;}

        public RetDataBean getRetData ( ) { return retData;}

        public void setRetData ( RetDataBean retData ) { this.retData = retData;}

        public static class RetDataBean {
                private String sex;
                private String birthday;
                private String address;

                public String getSex ( ) { return sex;}

                public void setSex ( String sex ) { this.sex = sex;}

                public String getBirthday ( ) { return birthday;}

                public void setBirthday ( String birthday ) { this.birthday = birthday;}

                public String getAddress ( ) { return address;}

                public void setAddress ( String address ) { this.address = address;}
        }
}
