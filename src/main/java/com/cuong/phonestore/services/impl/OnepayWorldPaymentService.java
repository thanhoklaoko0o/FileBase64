package com.cuong.phonestore.services.impl;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class OnepayWorldPaymentService {
	
	// static final String SECURE_SECRET = "your-secure-hash-secret";
    static final String SECURE_SECRET = "6D0870CDE5F24F34F3915FB0045120DB";
    static final char[] HEX_TABLE = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static byte[] decodeHexArray = new byte[103];

    static {
        int i = 0;
        for (byte b : new byte[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'}) {
            decodeHexArray[b] = (byte) i++;
        }
        decodeHexArray['a'] = decodeHexArray['A'];
        decodeHexArray['b'] = decodeHexArray['B'];
        decodeHexArray['c'] = decodeHexArray['C'];
        decodeHexArray['d'] = decodeHexArray['D'];
        decodeHexArray['e'] = decodeHexArray['E'];
        decodeHexArray['f'] = decodeHexArray['F'];
    }

    @SuppressWarnings("unchecked")
	public String hashAllFields(@SuppressWarnings("rawtypes") Map fields) {
        @SuppressWarnings("rawtypes")
		List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);
        StringBuffer buf = new StringBuffer();
        @SuppressWarnings("rawtypes")
		Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0) && fieldName.indexOf("vpc_") == 0) {
                buf.append(fieldName + "=" + fieldValue);
                if (itr.hasNext()) {
                    buf.append('&');
                }
            }
        }
        byte[] mac = null;
        try {
            byte[] b = decodeHexa(SECURE_SECRET.getBytes());
            SecretKey key = new SecretKeySpec(b, "HMACSHA256");
            Mac m = Mac.getInstance("HMACSHA256");
            m.init(key);
            m.update(buf.toString().getBytes("UTF-8"));
            mac = m.doFinal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String hashValue = hex(mac);
        return hashValue;

    }

    public static byte[] decodeHexa(byte[] data) throws Exception {
        if (data == null) {
            return null;
        }
        if (data.length % 2 != 0) {
            throw new Exception("Invalid data length:" + data.length);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte b1, b2;
        int i = 0;
        while (i < data.length) {
            b1 = decodeHexArray[data[i++]];
            b2 = decodeHexArray[data[i++]];
            out.write((b1 << 4) | b2);
        }
        out.flush();
        out.close();
        return out.toByteArray();
    }

    static String hex(byte[] input) {
        StringBuffer sb = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	public void appendQueryFields(StringBuffer buf, @SuppressWarnings("rawtypes") Map fields) {

        @SuppressWarnings("rawtypes")
		List fieldNames = new ArrayList(fields.keySet());
        @SuppressWarnings("rawtypes")
		Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // append the URL parameters
                buf.append(URLEncoder.encode(fieldName));
                buf.append('=');
                buf.append(URLEncoder.encode(fieldValue));
            }
            if (itr.hasNext()) {
                buf.append('&');
            }

        }

    }
    
    public String getResponseDescription(String vResponseCode) {
        Map<String, String> map_result = new HashMap<String, String>();
        map_result.put("0", "Giao dịch thành công");
        map_result.put("1", "Ngân hàng từ chối giao dịch");
        map_result.put("3", "Mã đơn vị không tồn tại");
        map_result.put("4", "Không đúng access code");
        map_result.put("5", "Số tiền không hợp lệ");
        map_result.put("6", "Mã tiền tệ không tồn tại");
        map_result.put("7", "Lỗi không xác định");
        map_result.put("8", "Số thẻ không đúng");
        map_result.put("9", "Tên chủ thẻ không đúng");
        map_result.put("10", "Thẻ hết hạn/Thẻ bị khóa");
        map_result.put("11", "Thẻ chưa đăng ký sử dụng dịch vụ");
        map_result.put("12", "Ngày phát hành/Hết hạn không đúng");
        map_result.put("13", "Vượt quá hạn mức thanh toán");
        map_result.put("21", "Số tiền không đủ để thanh toán");
        map_result.put("99", "Người sủ dụng hủy giao dịch");
        
        String result = "";
        result = map_result.get(vResponseCode);
        if (result != null) return result;
        else return "No Value Returned";
    }

    public static String null2unknown(String in) {
        if (in == null || in.length() == 0) {
            return "No Value Returned";
        } else {
            return in;
        }
    }
}
