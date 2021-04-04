package osm.surveyor.citygml;

/**
 * @code{
 * <core:cityObjectMember>
 * </core:cityObjectMember>
 * 
 * ＜trkpt lat="35.32123832" lon="139.56965631">
 *		<ele>47.20000076293945</ele>
 *		<time>2012-06-15T03:00:29Z</time>
 *		<magvar></magvar>
 *		<speed></speed>
 *	＜/trkpt>
 * }
 *
 */
public class TagCityObjectMember implements Cloneable {

	public TagCityObjectMember() {
	}
	
    public TagCityObjectMember(Double lat, Double lon) {
    }

    @Override
	public TagCityObjectMember clone() { //基本的にはpublic修飾子を付け、自分自身の型を返り値とする
    	TagCityObjectMember b = null;
		
		// ObjectクラスのcloneメソッドはCloneNotSupportedExceptionを投げる可能性があるので、try-catch文で記述(呼び出し元に投げても良い)
		try {
			//親クラスのcloneメソッドを呼び出す(親クラスの型で返ってくるので、自分自身の型でのキャストを忘れないようにする)
			b =(TagCityObjectMember)super.clone();
			//親クラスのcloneメソッドで深いコピー(複製先のクラス型変数と複製元のクラス型変数で指しているインスタンスの中身が違うコピー)がなされていないクラス型変数をその変数のcloneメソッドで複製し、複製先のクラス型変数に代入
			/*
			b.lat = this.lat;
			b.lon = this.lon;
			b.eleStr = (this.eleStr == null ? null : this.eleStr.toString());
			b.time = (Date) this.time.clone();
			b.magvarStr = (this.magvarStr==null ? null : this.magvarStr.toString());
			b.speedStr = (this.speedStr == null ? null : this.speedStr.toString());
			*/
		} catch (Exception e){
			e.printStackTrace();
		}
		return b;
	}
    
    public String toString() {
    	String ret = "<core:cityObjectMember/";
    	ret += ">";
    	System.out.println(ret);
    	return ret;
    }

    public String printinfo() {
    	String ret = "member";
    	System.out.println(ret);
    	return ret;
    }
}
