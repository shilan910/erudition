package com.erudition.util.ansj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 获取暂停词
 * @author sl
 *
 */
public class StopWord {

	public static Set<String> getStopWord(){
		Set<String> set=new HashSet<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("stopword.txt"));
			String temp=null;
			while (( temp = reader.readLine()) != null) {
				temp = temp.trim();
				set.add(temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return set;
	}
	public static void main(String[] args) {
		Set<String> set=getStopWord();
		System.out.println(set);
	}
}
