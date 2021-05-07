package jp.co.areaweb.tools.core;

/**
 * @author y_hayashi
 * @version 2010/02/07 'Exception'を削除
 * @since	 2005/06/02
 */
public abstract class MakeString
{
	/**
	 * intData数値を、ketaで指定された文字数の文字列に変換する。
	 * 数値が指定の桁に満たない場合は、０が埋め込まれます．
	 * 数値が指定の桁数を超えている場合には、例外を発生させます．
	 * 基数は１０です。
	 * @param intData	数値
	 * @param keta	生成する桁数
	 * @return	数値を表現する文字列
	 */
	public static String valueOf(int intData, int keta) {
		return valueOf(new Long(intData), keta);
	}
	
	/**
	 * longData数値を、ketaで指定された文字数の文字列に変換する。
	 * 数値が指定の桁に満たない場合は、０が埋め込まれます．
	 * 数値が指定の桁数を超えている場合には、例外を発生させます．
	 * 基数は１０です。
	 * @param longData	数値
	 * @param keta	生成する桁数
	 * @return	数値を表現する文字列
	 */
	public static String valueOf(long longData, int keta) {
		return valueOf(new Long(longData), keta);
	}
	
	/**
	 * longData数値を、ketaで指定された文字数の文字列に変換する。
	 * 数値が指定の桁に満たない場合は、０が埋め込まれます．
	 * 数値が指定の桁数を超えている場合には、例外を発生させます．
	 * 基数は１０です。
	 * @param longData	数値
	 * @param keta	生成する桁数
	 * @return	数値を表現する文字列
	 */
	public static String valueOf(Long longData, int keta) {
		int hugou = 0;
		long longValue = longData.longValue();
		if (longValue < 0) {
			hugou = 1;
			longValue = longValue * (-1);
		}

		String dataStr = (new Long(longValue)).toString();
		int loop = keta - hugou - dataStr.length();
		char[] pri = new char[loop];
		for (int i = 0; i < loop; i++) {
			pri[i] = '0';
		}
		return (hugou == 1 ? "-" : "") + (new String(pri)) + dataStr;
	}
}
