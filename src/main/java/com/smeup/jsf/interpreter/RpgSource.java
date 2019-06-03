package com.smeup.jsf.interpreter;

import java.io.Serializable;

public class RpgSource implements Serializable {

	private static final long serialVersionUID = 1L;

	private String content;
	private String rpgParmList;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRpgParmList() {
		return rpgParmList;
	}

	public void setRpgParmList(String rpgParmList) {
		this.rpgParmList = rpgParmList;
	}	
	
	public static String fibonacci() {
		return "﻿      * Calculates number of Fibonacci in an iterative way\n" + 
				"     D ppdat           S              8\n" + 
				"     D NBR             S              8  0\n" + 
				"     D RESULT          S              8  0 INZ(0)\n" + 
				"     D COUNT           S              8  0\n" + 
				"     D A               S              8  0 INZ(0)\n" + 
				"     D B               S              8  0 INZ(1)\n" + 
				"     C     *entry        plist\n" + 
				"     C                   parm                    ppdat                          I\n" + 
				"      *\n" + 
				"     C                   Eval      NBR    = %Dec(ppdat : 8 : 0)\n" + 
				"     C                   EXSR      FIB\n" + 
				"     C                   clear                   dsp              50\n" + 
				"     C                   eval      dsp= 'FIBONACCI OF: ' +  ppdat +\n" + 
				"     C                                 ' IS: ' + %CHAR(RESULT)\n" + 
				"     C                   dsply                   dsp\n" + 
				"     C                   seton                                        lr\n" + 
				"      *--------------------------------------------------------------*\n" + 
				"     C     FIB           BEGSR\n" + 
				"     C                   SELECT\n" + 
				"     C                   WHEN      NBR = 0\n" + 
				"     C                   EVAL      RESULT = 0\n" + 
				"     C                   WHEN      NBR = 1\n" + 
				"     C                   EVAL      RESULT = 1\n" + 
				"     C                   OTHER\n" + 
				"     C                   FOR       COUNT = 2 TO NBR\n" + 
				"     C                   EVAL      RESULT = A + B\n" + 
				"     C                   EVAL      A = B\n" + 
				"     C                   EVAL      B = RESULT\n" + 
				"     C                   ENDFOR\n" + 
				"     C                   ENDSL\n" + 
				"     C                   ENDSR\n" + 
				"      *--------------------------------------------------------------*\n" + 
				"";
	}
	
	public static String helloWorld() {
		return "﻿     D Msg             S             12\n" + 
				"     C                   Eval      Msg  = 'Hello World!'\n" + 
				"     C                   dsply                   Msg\n" + 
				"     C                   SETON                                          LR";
	}
	
}