/*
 * Created on 2005/07/01
 *
 * 日本語文字列特有の問題に対応する機能を集めたクラス
 */
package osm.surveyor.sql;

/**
 * @author y_hayashi
 *
 */
public abstract class JapaneseString {
	
	/**
	 * 文字列のトリミングを行う．通常のjava.lang.String.trim()の機能に加えて、
	 * 全角スペースもトリム対象の文字に加える。
	 * @param str トリムする文字列
	 * @return トリム後の文字列（別オブジェクト）
	 */
	public static String trim(String str) {
		str = str.trim();
		char[] charArray = str.toCharArray();
		if (charArray.length > 0) {
			if (java.lang.Character.isWhitespace(charArray[0])) {
				return trim(new String(charArray, 1, charArray.length - 1));
			}
			if (java.lang.Character.isWhitespace(charArray[charArray.length - 1])) {
				return trim(new String(charArray, 0, charArray.length - 1));
			}
		}
		return str;
	}
}
