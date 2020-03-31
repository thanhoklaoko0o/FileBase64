package com.cuong.phonestore.utils;

public class Constants {
    
	public static final String COMMON_HYPHEN = "-";
    public static final String EMPTY_STRING = "";
	public static final String EQUAL_COMPARE = "=";
	public static final String GREATER_COMPARE = ">";
	public static final String LESS_COMPARE = "<";
	public static final String GREATER_OR_EQUAL_COMPARE = ">=";
	public static final String LESS_OR_EQUAL_COMPARE = "<=";

	public static final String DATE_FORMAT_MMddyyyy = "MM/dd/yyyy";
	public static final String DATE_FORMAT_yyyyMMdd = "yyyy/MM/dd";
	public static final String DATE_FORMAT_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_FOR_FILE_NAME = "yyyyMMdd-HHmm";

	public static final String PROP_ROOT_FOLDER = "G:\\"; //root.storage.folder
	public static final String SUFFIX_IMAGE_JPG = ".jpg";
	public static final String SUFFIX_IMAGE_PNG = ".png";
	
	public static final String CRITERIAL_LIKE = "AND (%s LIKE '%%%s%%') ";
	public static final String CRITERIAL_COMPARE = "AND (%s %s '%s') ";
	public static final String CRITERIAL_DATE_COMPARE = "AND (DATE(%s) %s '%s') ";
	public static final String CRITERIAL_BETWEEN = "AND (UPPER(%s) BETWEEN %s AND %s) ";
	public static final String SORT_STRING_AS_NUMBER = "length(%s) %s, %s %s";
	public static final String SORT_STRING_WITH_UTF_8 = "%s COLLATE utf8_persian_ci %s";
	public static final String SORT_NUMBER = "%s %s";
	
	public final static String SQL_SEARCH_PHONE = "SELECT   CHITIETSANPHAM.GiaBan,TenMau, MAUSAC.MaMau,SANPHAM.MaSP as MaSP,TenSP,XuatXu,\r\n" + 
			"               SANPHAM.AnhSP as AnhSP,HANG.MaHang as MaHang, TenHang, NgayMoBan\r\n" + 
			"               FROM         CHITIETDONHANG INNER JOIN\r\n" + 
			"                CHITIETSANPHAM ON CHITIETDONHANG.MaSP = CHITIETSANPHAM.MaSP INNER JOIN\r\n" + 
			"				MAUSAC ON CHITIETSANPHAM.MaMau = MAUSAC.MaMau INNER JOIN\r\n" + 
			"                SANPHAM ON CHITIETDONHANG.MaSP = SANPHAM.MaSP AND CHITIETSANPHAM.MaSP = SANPHAM.MaSP inner join\r\n" + 
			"				HANG on SANPHAM.MaHang = HANG.MaHang WHERE 1=1 ";
}
