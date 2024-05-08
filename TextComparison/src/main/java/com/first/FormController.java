package com.first;


import javax.sound.midi.SysexMessage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class FormController{
	
	@GetMapping("/input")
	public String getForm() {
		//input.htmlに画面遷移
		return "input";
	}
	
	
	@PostMapping("/confirm")
	public String postForm(@RequestParam("val1") String val1, @RequestParam("val2") String val2, Model model) {
	
		//文字列を比較して、異なる部分を太字赤マーカー、合っている部分は黒字で表示
			StringBuilder result1 = new StringBuilder();
			StringBuilder result2 = new StringBuilder();
		// 行ごとの比較を行う
		String[] lines1 = val1.split("\n");
		String[] lines2 = val2.split("\n");
		Integer count_record ;
		
		//各配列の長さを取得し、長い方を配列の長さとして取得
		Integer length_lines1 = lines1.length;
		Integer length_lines2 = lines2.length;
		if(length_lines1>=length_lines2) {
			count_record = length_lines1;
		}else {
			count_record = length_lines2;
		}

		for (int i = 0; i < count_record; i++) {
			//配列の中に格納した各行を取り出す。
		    String each_lines1 = lines1[i];
		    String each_lines2 = lines2[i];
		    Integer current_collum = i; 
		    
		     
		        // 行が一致する場合
		        if (each_lines1.equals(each_lines2)) {
		            result1.append("<span style=\"color:#006;\">").append(lines1).append(" ").append("</span>");
		            result2.append("<span style=\"color:#006;\">").append(lines2).append(" ").append("</span>");
		        } else{//100未満0以上の時
		            //行が抜けていないかを確認
		        	for(int m=1 ; m<5 ;m++) {
		        		current_collum =+ m;
		        		//次の行を取得
		        		String next_lines1 = lines1[current_collum];
		        		String next_lines2 = lines2[current_collum];
		        		if(next_lines1.equals(next_lines2)) {
		        			for(Integer n=0 ; n<m ; n++) {
		        				// 挿入位置以降の要素をコピーして1つ後ろにずらす
		        				System.arraycopy(lines2, 0, lines2, 0 + 1, lines2.length - 0 - 1);
		        				// 指定された位置に値を挿入
		        				lines2[i+n] = "ここがずれています。";
		        			}
		        		}else {
		        			result1.append("<span style=\"color:#006; font-weight:bold; background-color:#ccccff;\">").append(lines1).append(" ").append("</span>");
				            result2.append("<span style=\"color:#006; font-weight:bold; background-color:#ccccff;\">").append(lines2).append(" ").append("</span>");
		        		}
		        	}  
		        } 
		       
	
		//フォームから送信されてきた値をModelに登録
		model.addAttribute("result1", result1);
		model.addAttribute("result2", result2);
		
		}
		//confirm.htmlに画面遷移
		return "confirm";
	}
}




    

