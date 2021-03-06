package com.example.jdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class PaixuRegexDemo {
	public Map<String, Integer> map1 = new HashMap<String, Integer>();

	public static void main(String arg[]) {
		String sz[];
		Integer num[];
		final int MAXNUM = 10; //统计的单词出现最多的前n个的个数

		sz = new String[MAXNUM + 1];
		num = new Integer[MAXNUM + 1];
		PaixuRegexDemo pipei = new PaixuRegexDemo();
		int account = 1;
		//Vector<String> ve1=new Vector<String>();
		try {
			pipei.daoru();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("英文单词的出现情况如下:");
		int g_run = 0;

		for (g_run = 0; g_run < MAXNUM + 1; g_run++) {
			account = 1;
			for (Map.Entry<String, Integer> it : pipei.map1.entrySet()) {
				if (account == 1) {
					sz[g_run] = it.getKey();
					num[g_run] = it.getValue();
					account = 2;
				}
				if (account == 0) {
					account = 1;
					continue;
				}
				if (num[g_run] < it.getValue()) {
					sz[g_run] = it.getKey();
					num[g_run] = it.getValue();
				}
				//System.out.println("英文单词: "+it.getKey()+" 该英文单词出现次数: "+it.getValue());
			}
			pipei.map1.remove(sz[g_run]);
		}
		int g_count = 1;
		String tx1 = new String();
		for (int i = 0; i < g_run; i++) {
			if (sz[i] == null)
				continue;
			if (sz[i].equals(""))
				continue;
			tx1 += "出现次数第" + (g_count) + "多的单词为:" + sz[i] + "\t\t\t出现次数: " + num[i] + "\r\n";
			System.out.println("出现次数第" + (g_count) + "多的单词为:" + sz[i] + "\t\t\t出现次数: " + num[i]);
			g_count++;
		}
		try {
			pipei.daochu(tx1);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}


	}

	public void daoru() throws IOException {
		File a = new File("C:/Users/dw/Desktop/text.txt");
		FileInputStream b = new FileInputStream(a);
		InputStreamReader c = new InputStreamReader(b, "UTF-8");
		String string2 = new String();
		while (c.ready()) {
			char string1 = (char) c.read();
			if (!isWord(string1)) {
				if (map1.containsKey(string2)) {
					Integer num1 = map1.get(string2) + 1;
					map1.put(string2, num1);
				} else {
					Integer num1 = 1;
					map1.put(string2, num1);
				}
				string2 = "";
			} else {
				string2 += string1;
			}
		}
		if (!string2.isEmpty()) {
			if (map1.containsKey(string2)) {
				Integer num1 = map1.get(string2) + 1;
				map1.put(string2, num1);
			} else {
				Integer num1 = 1;
				map1.put(string2, num1);
			}
			string2 = "";
		}
		c.close();
		b.close();
	}

	public void daochu(String txt) throws IOException {
		File fi = new File("tongji.txt");
		FileOutputStream fop = new FileOutputStream(fi);
		OutputStreamWriter ops = new OutputStreamWriter(fop, "UTF-8");
		ops.append(txt);
		ops.close();
		fop.close();
	}

	public boolean isWord(char a) {
		if (a <= 'z' && a >= 'a' || a <= 'Z' && a >= 'A')
			return true;
		return false;
	}

}
