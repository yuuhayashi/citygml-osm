/*
 * Created on 2005/06/09
 *
 */
package jp.co.areaweb.tools.core;

/**
 * @author y_hayashi
 *
 *	消費税に関するメソッドを集めたクラス
 *	端数は四捨五入する
 */
public abstract class Tax {
	public static int taxRates = 5;	// 消費税率。パーセンテージで示す。
										// 税率が変更されたときにはこの値を直接変更する。
	/**
	 * 総額に対する本体の金額を求める。
	 * @param totalValue	long
	 * @return 結果
	 */
	public static long calcBody(long totalValue) {
		return Math.round((new Long(totalValue)).doubleValue() * 100 / (100 + taxRates));
	}

	/**
	 * 総額に対する消費税額を求める。
	 * @param totalValue	long
	 * @return 結果
	 */
	public static long calcTax(long totalValue) {
		return totalValue - calcBody(totalValue) ;
	}


	/**
	 * 本体価格に対する消費税額を求める。
	 * @param bodyValue	long
	 * @return 結果
	 */
	public static long toTax(long bodyValue) {
		return Math.round((new Long(bodyValue)).doubleValue() * taxRates / 100);
	}

	/**
	 * 本体価格に対する総額を求める。
	 * @param bodyValue	long
	 * @return 結果
	 */
	public static long toTotal(long bodyValue) {
		return bodyValue + toTax(bodyValue);
	}
}
