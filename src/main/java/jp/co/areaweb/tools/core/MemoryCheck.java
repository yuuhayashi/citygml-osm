package jp.co.areaweb.tools.core;

import java.text.DecimalFormat;

public class MemoryCheck {

	/**
	 * 
	 * 出力結果
	 * 		Java メモリ情報 : 合計=1,984KB、使用量=458KB (23.1%)、使用可能最大=65,088KB
	 * 
	 * JAVAのヒープサイズ変更方法
	 * 		java -Xms64m -Xmx512m Main
	 * 			-Xms初期ヒープサイズ
	 * 				Java仮想マシンへの初期メモリ割り当て量を指定します。デフォルトは2MBです。
	 * 			-Xmx最大ヒープサイズ
	 * 				Java仮想マシンへの最大メモリ割り当て量を指定します。デフォルトは64MBです。
	 * 
	 * @param args	strings
	 */
	public static void main(String[] args) {
		System.out.println(getMemoryInfo());
	}

	/**
	 * Java 仮想マシンのメモリ総容量、使用量、
	 * 使用を試みる最大メモリ容量の情報を返します。
	 * @return Java 仮想マシンのメモリ情報
	 */
	public static String getMemoryInfo() {
	    DecimalFormat f1 = new DecimalFormat("#,###KB");
	    DecimalFormat f2 = new DecimalFormat("##.#");
	    long free = Runtime.getRuntime().freeMemory() / 1024;
	    long total = Runtime.getRuntime().totalMemory() / 1024;
	    long max = Runtime.getRuntime().maxMemory() / 1024;
	    long used = total - free;
	    double ratio = (used * 100 / (double)total);
	    String info = 
	    "Java メモリ情報 : 合計=" + f1.format(total) + "、" + "使用量=" + f1.format(used) + " (" + f2.format(ratio) + "%)、" + "使用可能最大="+f1.format(max);
	    return info;
	}
}
