package com.example.rabbitmq.rabbitmq;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hcq
 * @date 2019/12/30 15:41
 */
public class Controller {
    public static void main(String[] args) throws Exception {
        List<String> list = readFile(new File("d://yibin_order2.txt"));
        for (String order : list) {
            readRow(order);
        }
    }

    static List<String> readFile(File file) throws Exception {
        List<String> orders = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF-8"));
        String tempString;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            if (tempString != null && !"".equals(tempString)) {
                orders.add(tempString);
            }
        }
        reader.close();
        return orders;
    }

    public static void readRow(String orderInfo) {
        //String orderInfo="UnifiedOrderVo(version=1.0.0, agentNo=1, payTypeCode=wxMicro, terminalType=OTHER, cashierNo=null, merchantNo=e83b9075e03043978a88943eeda6b86e, sign=9176638DDE714A87DF2BD5E2B7D489D1, body=厦门市湖里区维銮食品经营部-消费付款, outTradeNo=191231457349321650d8544e9c928757, amount=375.50, description=null, currency=null, timePaid=20191231064218, timeExpire=null, subject=厦门市湖里区维銮食品经营部-消费付款, limitPay=, callbackUrl=null, notifyUrl=null, isRaw=null, subAppid=null, subOpenid=null, wxSubMchId=null, mobileAppId=null, authCode=134624883463427937, accessType=null, orderId=122019123106421799214995302, systemCode=10003, orderAmount=375.50, realAmount=375.50, realIncome=375.50, merPocketAmt=0, changeAmt=0, userPocketId=null, discountAmt=0, merLoginId=3a7aaa29893846cc9a49d8031020aecf, merchantLoginNo=3a7aaa29893846cc9a49d8031020aecf, spbillCreateIp=139.199.141.41, requestStr=null, responseStr=null, remark=3a7aaa29893846cc9a49d8031020aecf-191231457349321650d8544e9c928757, systemNum=null, paychannelUserId=null, orderPayCode=573575, snNo=null, downstreamSystemCode=20001, discountAmount=0, userPocketAmount=0, userPocketNo=null, customerIP=null, ip=110.87.73.59, qrCodeNo=null)";
        String orderId = getAttr("orderId=", orderInfo, ",");
        String merchantNo = getAttr("merchantNo=", orderInfo, ",");
        String merchantName = getAttr("body=", orderInfo, "-");
        String merchantLoginId = getAttr("merLoginId=", orderInfo, ",");
        String orderDate = getAttr("timePaid=", orderInfo, ",");
        String order_amount = getAttr("orderAmount=", orderInfo, ",");
        String realAmount = getAttr("realAmount=", orderInfo, ",");
        String realIncome = getAttr("realIncome=", orderInfo, ",");
        String orderPayCode = getAttr("orderPayCode=", orderInfo, ",");
        String pay_way_code = getAttr("payTypeCode=", orderInfo, ",");
        //支付方式
        String pay_type_code;
        String mchId = "1487697832";
        if (pay_way_code.startsWith("alipay")) {
            mchId = "2088821906393984";
            pay_type_code = "alipay";
            throw new RuntimeException("【" + orderId + "】支付类型错误");
        } else if (pay_way_code.startsWith("unionpay")) {
            pay_type_code = "unionpay";
        } else if (pay_way_code.startsWith("wx")) {
            mchId = "1451574002";
            pay_type_code = "weixin";
        } else {
            throw new RuntimeException("【" + orderId + "】支付类型错误");
        }
        //支付产品关联id
        String relationId = "8c0329c918324d02b131308e3165402f";
        String downstreamSystemCode = "20001";
        String transaction_id = getAttr("outTradeNo=", orderInfo, ",");
        String mer_out_trade_no = getAttr("orderId=", orderInfo, ",");
        System.out.print(orderId + ",");
         /*printSql(orderId,merchantNo,merchantName,merchantLoginId,orderDate,
                 order_amount,realAmount,realIncome,orderPayCode,pay_type_code,pay_way_code,
                 relationId,downstreamSystemCode,
                 mchId,transaction_id,mer_out_trade_no);*/
    }

    static String getAttr(String attr, String orderInfo, String suffix) {
        int prefix = orderInfo.indexOf(attr);
        orderInfo = orderInfo.substring(prefix + attr.length());
        prefix = orderInfo.indexOf(suffix);
        orderInfo = orderInfo.substring(0, prefix);
        // System.out.println(orderInfo);
        return orderInfo;
    }

    /**
     * 拼装sql
     */
    static void printSql(String orderId, String merchantNo, String merchantName,
                         String merchantLoginId, String orderDate, String order_amount, String realAmount,
                         String realIncome, String orderPayCode, String pay_way_code, String pay_type_code,
                         String relationId, String downstreamSystemCode,
                         String mchId, String transaction_id, String mer_out_trade_no) {
        String insertSql = "INSERT INTO pay_order_today " +
                "(`id`,`merchant_id`,`merchant_name`,`mer_login_id`,`bank_payment_service_id`,`order_time`,`pay_success_time`,`pay_status`,`order_amount`,`real_amount`,`real_income`,`mer_pocket_amount`,`user_pocket_id`,`change_amount`,`compensation_fee`,`discount_amount`,`merchant_pay_product_item_relation_id`,`pay_code`,`pay_way_code`,`pay_type_code`,`pay_pipe_code`,`settle_cycle_code`,`user_payment_type`,`pay_pipe_cost`,`bank_profit`,`agent_profit`,`merchant_trade_fee`,`settle_amount`,`pay_channel_userid`,`transaction_id`,`mer_out_trade_no`,`mchid`,`verify_status`,`verify_result`,`verify_time`,`recon_status`,`recon_time`,`settle_status`,`settle_time`,`settle_record_id`,`notify_status`,`notify_time`,`notify_url`,`create_time`,`creator`,`edit_time`,`editor`,`remark`,`ext1`,`ext2`,`ext3`)VALUES " +
                "('%s','%s','%s','%s','prpay833d9a0488f91500ybbank40018','%s','%s'," +
                "'O','%s','%s','%s','0.00',NULL,'0.00','0.00','0.00','%s','%s'," +
                "'%s','%s','02','T1','%s','0.00','0.00','0.00','0.00','0.00'," +
                "NULL,'" + transaction_id + "','" + mer_out_trade_no + "','" + mchId + "'," +
                "'D',NULL,NULL,'P',NULL,'P',NULL,NULL,'D',NULL,NULL,'%s','wz','%s','wz',NULL,'0.00','0','%s');";
        String user_payment_type = "OTHERS";
        insertSql = String.format(insertSql, orderId, merchantNo, merchantName,
                merchantLoginId, orderDate, orderDate, order_amount, realAmount, realIncome, relationId, orderPayCode,
                pay_way_code, pay_type_code, user_payment_type,
                orderDate, orderDate, downstreamSystemCode);
        System.out.println(insertSql + "\n\n\n");
    }
}
