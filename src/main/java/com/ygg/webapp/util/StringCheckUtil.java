package com.ygg.webapp.util;

public class StringCheckUtil {

    public static float StringCheckUtil(String str1,String str2){
           float result=getSimilarityRatio(str1,str2);
           return result;
    }

    public static int compare(String str, String target){
	        int d[][];              // 矩阵
	        int n = str.length();
	        int m = target.length();
	        int i;                  // 遍历str的
	        int j;                  // 遍历target的
	        char ch1;               // str的
	        char ch2;               // target的
	        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
	        if (n == 0) { return m; }
	        if (m == 0) { return n; }
	        d = new int[n + 1][m + 1];
	        for (i = 0; i <= n; i++){   // 初始化第一列
	            d[i][0] = i;
	        }
	        for (j = 0; j <= m; j++){   // 初始化第一行
	            d[0][j] = j;
	        }
	        for (i = 1; i <= n; i++){   // 遍历str
	            ch1 = str.charAt(i - 1);
	            // 去匹配target
	            for (j = 1; j <= m; j++){
	                ch2 = target.charAt(j - 1);
	                if (ch1 == ch2 || ch1 == ch2+32 || ch1+32 == ch2){
	                    temp = 0;
	                } else{
	                    temp = 1;
	                }
	                // 左边+1,上边+1, 左上角+temp取最小
	                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
	            }
	        }
	        return d[n][m];
	    }

    public static int min(int one, int two, int three){
	        return (one = one < two ? one : two) < three ? one : three;
	    }

	    /**
	     * 获取两字符串的相似度
	     */

	    public static float getSimilarityRatio(String str, String target){
	        return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());
	    }


	
	
	
	/**相似度比较的另一种方法，但是字符串长度太长了不好用
	public static void main(String[] args) {
		String strA = "我是湖南人ssssssssssssss";
		String strB = "我是湖南人fddfdfdf";
		double result=SimilarDegree(strA, strB);
		if(result>=0.7){
			System.out.println("相似度很高！" +similarityResult(result)+result);
		}else{
			System.out.println("相似度不高"+similarityResult(result)+result);
		}
		System.out.println();
	}
	public static String similarityResult(double resule){
		return  NumberFormat.getPercentInstance(new Locale( "en ", "US ")).format(resule);
	}
	public static double SimilarDegree(String strA, String strB){
		String newStrA = removeSign(strA);
		String newStrB = removeSign(strB);
		int temp = Math.max(newStrA.length(), newStrB.length());
		int temp2 = longestCommonSubstring(newStrA, newStrB).length();
		return temp2 * 1.0 / temp;
	}

	private static String removeSign(String str) {
		StringBuffer sb = new StringBuffer();
		for (char item : str.toCharArray())
			if (charReg(item)){
				System.out.println("--"+item);
				sb.append(item);
			}
		return sb.toString();
	}

	private static boolean charReg(char charValue) {
		return (charValue >= 0x4E00 && charValue <= 0X9FA5)
				|| (charValue >= 'a' && charValue <= 'z')
				|| (charValue >= 'A' && charValue <= 'Z')
				|| (charValue >= '0' && charValue <= '9');
	}

	private static String longestCommonSubstring(String strA, String strB) {
		char[] chars_strA = strA.toCharArray();
		char[] chars_strB = strB.toCharArray();
		int m = chars_strA.length;
		int n = chars_strB.length;
		int[][] matrix = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (chars_strA[i - 1] == chars_strB[j - 1])
				matrix[i][j] = matrix[i - 1][j - 1] + 1;
				else
				matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
			}
		}
		char[] result = new char[matrix[n][n]];
		int currentIndex = result.length - 1;
		while (matrix[n][n] != 0) {
			if (matrix[n] == matrix[n - 1])
				n--;
			else if (matrix[n][n] == matrix[m - 1][n])
				m--;
			else {
				result[currentIndex] = chars_strA[m - 1];
				currentIndex--;
				n--;
				m--;
			}
		}
		return new String(result);
	}
	*/

}
